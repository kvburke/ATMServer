package com.burke;

import java.util.Map;
import javax.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Scope(value ="singleton")
@Controller
public class WelcomeController {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	// inject via application.properties
	
	@Autowired
	MessageReceiver receiver;

					
	@RequestMapping("/queue")
	public void activemq() throws JMSException {	
			receiver.main();
	}
	
	}
