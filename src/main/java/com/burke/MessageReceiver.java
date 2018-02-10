package com.burke;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value="singleton")
@Component
public class MessageReceiver {

	// URL of the JMS server
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	// default broker URL is : tcp://localhost:61616"

	// Name of the queue we will receive messages from
	private static String subject = "JCG_QUEUE";
	
	@Autowired
	private Execute execute;
	

	public void main() throws JMSException {
		// Getting JMS connection from the server
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// Creating session for seding messages
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);

		// Getting the queue 'JCG_QUEUE'
		Destination destination = session.createQueue(subject);

		// MessageConsumer is used for receiving (consuming) messages
		MessageConsumer consumer = session.createConsumer(destination);

		boolean bool=true;
		
		while(bool==true) {
		// Here we receive the message.
		Message message = consumer.receive();

		// We will be using TestMessage in our example. MessageProducer sent us a TextMessage
		// so we must cast to it to get access to its .getText() method.
		if (message instanceof ObjectMessage) {
			ObjectMessage obj = (ObjectMessage) message;
			
			Account textMessage = (Account) obj.getObject();
			//System.out.println("Received message '" + textMessage.getText() + "'");
		Account msg = textMessage;
			
			execute.submit(msg);
		}
		
		}
		connection.close();
	}
}