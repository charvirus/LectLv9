package controller;

import models.Bank;

public class Main {

	public static void main(String[] args) {
		Bank.instance.setBrand("GREEN");
		BankManager.instance.run();
	}

}
