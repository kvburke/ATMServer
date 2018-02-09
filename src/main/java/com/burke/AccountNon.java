package com.burke;






import org.springframework.stereotype.Component;

@Component
public class AccountNon  {

private int accountnumber;
private String username;
private String password;
private String message;
private String history;
private int creditscore;
private double savingsbalance;
private double currentbalance;

public int getAccountnumber() {
	return accountnumber;
}
public void setAccountnumber(int accountnumber) {
	this.accountnumber = accountnumber;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getHistory() {
	return history;
}
public void setHistory(String history) {
	this.history = history;
}
public int getCreditscore() {
	return creditscore;
}
public void setCreditscore(int creditscore) {
	this.creditscore = creditscore;
}
public double getSavingsbalance() {
	return savingsbalance;
}
public void setSavingsbalance(double savingsbalance) {
	this.savingsbalance = savingsbalance;
}
public double getCurrentbalance() {
	return currentbalance;
}
public void setCurrentbalance(double currentbalance) {
	this.currentbalance = currentbalance;
}






}
