package models;

public class Account {
	public static final int MAX = 3;

	private boolean rep;
	private long userCode;
	private int accNum;
	private int money;

	public Account(long userCode, int accNum) {
		this.userCode = userCode;
		this.accNum = accNum;
	}

	public Account(boolean rep, long userCode, int accNum) {
		this.rep = rep;
		this.userCode = userCode;
		this.accNum = accNum;
		this.money = 1000;
	}

	public Account(boolean rep, long userCode, int accNum, int money) {
		this.rep = rep;
		this.userCode = userCode;
		this.accNum = accNum;
		this.money = money;
	}

	public boolean getRep() {
		return this.rep;
	}

	public void setRep(boolean rep) {
		this.rep = rep;
	}

	public long getUserCode() {
		return this.userCode;
	}

	public int getAccNum() {
		return this.accNum;
	}

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
