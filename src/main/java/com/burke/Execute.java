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
	
	public void hello() {
		System.out.println("Hello");
	}
	
	public void submit(Account account) {
		
		System.out.println(account.getAccountnumber());
		System.out.println(account.getCreditscore());
		account.getCurrentbalance();
		account.getHistory();
		account.getSavingsbalance();
		database.createAccount(account);
		String accountmessage=account.getMessage();
		String accountstring=accountmessage.substring(0,4);
		System.out.println("account string is"+accountstring);
		Integer accountnumber=Integer.parseInt(accountstring);
		String message=accountmessage.substring(5);
		System.out.println("account number is"+accountnumber);
		executor.submit(new ATMWorkerThread(accountnumber));
		System.out.println("Task submitted");
	}

}
