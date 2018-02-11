package com.burke;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;

public class ATMWorkerThread implements Runnable {
public ATMWorkerThread(int accountnumber) {

	Account account=database.accessAccount(accountnumber);
	queue.add(account);
	
}


@Autowired
private static AccountDatabase database;
private static Queue<Account> queue = new LinkedList<Account>();
private static Lock lock1 = new ReentrantLock();
private static Lock lock2 = new ReentrantLock();
private static Lock lock3 = new ReentrantLock();
private static Lock lock4 = new ReentrantLock();

@Override
public void run() {
	
	System.out.println("Running thread");
	Account account=queue.poll();
	System.out.println(account);
	lock1.lock();
	history(account);
	currentbalance(account);
	lock1.unlock();
	lock2.lock();
	deposit(account);
	lock2.unlock();
	lock3.lock();
	withdraw(account);
	lock3.unlock();
	lock4.lock();
	history(account);
	currentbalance(account);
	lock4.unlock();
	
	
}

private void history(Account account) {

	String lastaccessed=account.getHistory();
	System.out.println("Account last accessed "+lastaccessed);
	Date date= new Date();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	String updatehistory= dateFormat.format(date);
	account.setHistory(updatehistory);
	database.updateAccount(account);
	
}

public void deposit(Account account) {
	System.out.println("Depositing $"+account.getDeposit());
	double currentbalance=account.getCurrentbalance()+account.getDeposit();
	account.setCurrentbalance(currentbalance);
	AccountDatabase.updateAccount(account);
}

public void withdraw(Account account) {
	System.out.println("Withdrawing $"+account.getWithdraw());
	double currentbalance=account.getCurrentbalance()-account.getWithdraw();
	//System.out.println("The current balance in method is"+currentbalance);
	account.setCurrentbalance(currentbalance);
	AccountDatabase.updateAccount(account);
}
 
private void currentbalance(Account account) {
	System.out.println("Current balance is "+account.getCurrentbalance());
	
}



}
