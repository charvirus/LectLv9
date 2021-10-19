package controller;

import java.util.Scanner;

import models.Bank;

public class BankManager {
	public static Scanner sc = new Scanner(System.in);

	public static BankManager instance = new BankManager();

	private UserManager um = UserManager.instance;
	private AccountManager am = AccountManager.instance;
	private FileManager fm = FileManager.instance;

	private String bar = "----------------------\n";

	public void run() {
		fm.load();
		boolean isRun = true;
		while (isRun) {
			System.out.println(bar + "<<" + Bank.instance.getBrand() + " BANK ATM>>");
			printAllData();
			printMenu();
			isRun = selectMenu();
		}
		fm.save();
	}

	private boolean selectMenu() {
		System.out.print("메뉴 : ");
		String input = sc.next();
		try {
			int sel = Integer.parseInt(input);

			if (Bank.log == -1) {
				if (sel == 1) {
					Bank.log = login();
				} else if (sel == 2) {
					um.joinUser();
				} else if (sel == 3) {
					um.withdrawalUser();
				} else if (sel == 4) {
					fm.save();
					return false;
				}
			} else {
				if (um.getUserAccSize(Bank.log) > 0) {
					if (sel == 1)
						am.withdrawal(Bank.log);
					else if (sel == 2)
						am.deposit(Bank.log);
					else if (sel == 3)
						am.inquery(Bank.log);
					else if (sel == 4)
						am.transfer(Bank.log);
					else if (sel == 6)
						am.closeAcc(Bank.log);
					else if (sel == 7)
						am.changeReqAcc(Bank.log);
				} else {
					am.createAcc(Bank.log);
				}
				if (sel == 5) {
					am.createAcc(Bank.log);
				} else if (sel == 8) {
					Bank.log = -1;
				}
			}
		} catch (Exception e) {
			System.out.println("잘못된 입력");
		}
		return true;
	}

	private int login() {
		System.out.print("id : ");
		String id = sc.next();
		System.out.print("pw : ");
		String pw = sc.next();

		return um.checkLog(id, pw);
	}

	private void printMenu() {
		if (Bank.log == -1) {
			System.out.println("1. 로그인\n2. 회원가입\n3. 탈퇴\n4. 종료");
		} else {
			System.out.println(um.getUser(Bank.log).getName() + "로그인");
			System.out
					.println("1. 출급\n2. 입금\n3. 현재 계좌\n4. 이체\n5. 계좌 생성\n6. 계좌 삭제\n7. 계좌 변경\n8. 로그아웃");
		}
	}

	private void printAllData() {
		System.out.print(bar + um.getAllData() + bar);
	}
}
