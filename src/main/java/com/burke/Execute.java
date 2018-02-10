package com.burke;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Scope(value ="singleton")
@Component
public class Execute {
	private ExecutorService executor = Executors.newFixedThreadPool(4);
	@Autowired
	AccountDatabase database;
	
	
	public void submit(Account account) {
		
		
		account.getCurrentbalance();
		account.getHistory();
		account.getSavingsbalance();
		database.createAccount(account);
		String accountmessage=account.getMessage();
		String accountstring=accountmessage.substring(0,4);
		Integer accountnumber=Integer.parseInt(accountstring);
		String message=accountmessage.substring(5);
		executor.submit(new ATMWorkerThread(accountnumber));
		System.out.println("Task submitted");
	}

}
