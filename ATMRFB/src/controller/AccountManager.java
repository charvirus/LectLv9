package controller;

import java.util.ArrayList;
import java.util.Random;

import models.Account;

public class AccountManager {
	public static AccountManager instance = new AccountManager();

	private ArrayList<Account> accs = null;

	private UserManager um = UserManager.instance;

	private AccountManager() {
		accs = new ArrayList<>();
	}

	public void createAcc(int log) {
		System.out.print("새 계정을 생성 \n 1)예 2) 아니오");
		String input = BankManager.sc.next();

		try {
			int sel = Integer.parseInt(input);

			if (sel == 1) {
				int userAccSize = um.getUserAccSize(log);
				long userCode = um.getUser(log).getCode();

				if (userAccSize < Account.MAX) {
					Account newAcc = null;
					if (userAccSize > 0) {
						newAcc = new Account(userCode, randomAccNum());
					} else {
						newAcc = new Account(true, userCode, randomAccNum());
					}
					accs.add(newAcc);
					um.addAcc(log, newAcc);
				} else {
					System.out.println("더이상 계정을 생성하실수 없습니다.");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private int randomAccNum() {
		Random rn = new Random();

		while (true) {
			int rNum = rn.nextInt(89999) + 10000;

			boolean check = false;
			for (Account acc : accs) {
				if (acc.getAccNum() == rNum) {
					check = true;
				}
			}
			if (!check) {
				return rNum;
			}
		}
	}

	public int getReqAccIndex(int log) {
		int idx = -1;
		for (int i = 0; i < this.um.getUserAccSize(log); i++) {
			if (this.um.getAccount(log, i).getRep()) {
				idx = i;
			}
		}
		return idx;
	}

	public void withdrawal(int log) {
		System.out.print("출금할 금액 : ");
		String input = BankManager.sc.next();

		int idx = getReqAccIndex(log);

		try {
			int money = Integer.parseInt(input);
			int balance = this.um.getAccount(log, idx).getMoney();
			if (money <= balance) {
				balance -= money;
				this.um.getAccount(log, idx).setMoney(balance);
			}
		} catch (Exception e) {
			System.out.println("출금 실패");
		}
	}

	public void deposit(int log) {
		System.out.print("입금할 금액 : ");
		String input = BankManager.sc.next();

		int idx = getReqAccIndex(log);

		try {
			int money = Integer.parseInt(input);
			int balance = this.um.getAccount(log, idx).getMoney();
			balance += money;
			this.um.getAccount(log, idx).setMoney(balance);

		} catch (Exception e) {
			System.out.println("입금 실패");
		}
	}

	public void inquery(int log) {
		System.out.println(">>");
		for (Account acc : accs) {
			if (um.getUser(log).getCode() == acc.getUserCode()) {
				System.out.println(acc.getUserCode() + " : " + acc.getAccNum() + " : " + acc.getMoney() + "원");
			}
		}
		System.out.println("<<");
	}

	public void transfer(int log) {
		int repIdx = getReqAccIndex(log);
		int repNum = um.getAccount(log, repIdx).getAccNum();

		System.out.print("계좌 번호 : ");
		String input1 = BankManager.sc.next();
		System.out.print("이체할 금액 : ");
		String input2 = BankManager.sc.next();

		try {
			int num = Integer.parseInt(input1);
			int money = Integer.parseInt(input2);

			int idx = -1;
			for (int i = 0; i < this.accs.size(); i++) {
				if (this.accs.get(i).getAccNum() == num) {
					idx = i;
				}
			}

			if (num != repNum && idx != -1) {
				int balance = this.accs.get(idx).getMoney();
				balance += money;
				this.accs.get(idx).setMoney(balance);

				balance = um.getAccount(log, repIdx).getMoney();
				balance -= money;
				um.getAccount(log, repIdx).setMoney(balance);
			} else {
				System.out.println("허용되지 않습니다.");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void closeAcc(int log) {
		int repIdx = getReqAccIndex(log);
		int repNum = um.getAccount(log, repIdx).getAccNum();

		System.out.print(um.getAccData(log));
		System.out.print("삭제할 계좌를 선택 : ");
		String input = BankManager.sc.next();

		try {
			int delNum = Integer.parseInt(input);

			Account delAcc = null;
			for (Account acc : accs) {
				if (delNum == acc.getAccNum()) {
					delAcc = acc;
				}
			}
			int money = delAcc.getMoney();

			if (um.getUserAccSize(log) > 1) {
				um.removeAcc(log, delAcc);
				this.accs.remove(delAcc);

				if (delNum == repNum) {
					um.getAccount(log, 0).setRep(true);
				}
				int balance = um.getAccount(log, getReqAccIndex(log)).getMoney();

				um.getAccount(log, getReqAccIndex(log)).setMoney(balance);
			} else {
				System.out.println("최소 1개 이상의 계좌가 필요합니다.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void changeReqAcc(int log) {
		System.out.println(um.getAccData(log));
		System.out.printf("선택 1~%d : ", um.getUserAccSize(log));
		String input = BankManager.sc.next();

		try {
			int idx = Integer.parseInt(input) - 1;

			if (idx >= 0 && um.getUserAccSize(log) > idx) {
				um.getAccount(log, getReqAccIndex(log)).setRep(false);
				um.getAccount(log, idx).setRep(true);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public int getAccsSize() {
		return this.accs.size();
	}

	public Account getAccount(int idx) {
		return this.accs.get(idx);
	}

	public void addAccount(Account e) {
		this.accs.add(e);
	}

	public void clearAccs() {
		this.accs.clear();
	}
}
