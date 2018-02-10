package com.burke;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Scope(value="singleton")
@Component
public class AccountDatabase {
static Map<Integer, Account> database= new ConcurrentHashMap<Integer, Account>();

public void createAccount(Account account) {
	database.put(account.getAccountnumber(), account);
}

public static void updateAccount(Account account) {
	database.put(account.getAccountnumber(), account);
}

public static Account accessAccount(int accountnumber) {
	Account account=database.get(accountnumber);
	return account;
}

public void deleteAccount(int accountnumber) {
	database.remove(accountnumber);
}

}
