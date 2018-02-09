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
	@Value("${welcome.message:test}")
	private String message = "Hello World";
	@Autowired
	private Execute executor;
	@Autowired
	AccountDatabase database;
	@Autowired
	MessageReceiver receiver;

	public Execute getExecutor() {
		return executor;
	}

	public void setExecutor(Execute executor) {
		this.executor = executor;
	}

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}
	
	@RequestMapping("/hello")
	public void helloworld() {
		System.out.println("Hello world");
	}
	
	@RequestMapping("/test")
	public void queue() {
		executor.hello();
	}
		
	@RequestMapping("/submit")
	public void submit() {
		Account account1 = new Account();
		account1.setAccountnumber(1000);
		account1.setHistory(null);
		account1.setCreditscore(600);
		account1.setSavingsbalance(500);
		account1.setCurrentbalance(1000);

		Account account2 = new Account();
		account2.setAccountnumber(1001);
		account2.setHistory(null);
		account2.setCreditscore(500);
		account2.setSavingsbalance(300);
		account2.setCurrentbalance(600);
		
		Account account3 = new Account();
		account3.setAccountnumber(1002);
		account3.setHistory(null);
		account3.setCreditscore(700);
		account3.setSavingsbalance(500);
		account3.setCurrentbalance(300);
		
		Account account4 = new Account();
		account4.setAccountnumber(1003);
		account4.setHistory(null);
		account4.setCreditscore(800);
		account4.setSavingsbalance(1500);
		account4.setCurrentbalance(3300);
		
		database.createAccount(account1);
		database.createAccount(account2);
		database.createAccount(account3);
		database.createAccount(account4);
		
		//executor.submit("1000abcd");
	
		//executor.submit("1001abcd");
		
		//executor.submit("1002abcd");
		
		//executor.submit("1003abcd");
	}
		
	
	@RequestMapping("/queue")
	public void activemq() {
		String[] lol= {"lol"};
		try {
			receiver.main(lol);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	}
