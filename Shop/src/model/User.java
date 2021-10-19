package model;

public class User {
	private String id;
	private int money;

	public User(String id, int mo) {
		this.id = id;
		this.money = mo;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getMoney() {
		return this.money;
	}

	public void print() {
		System.out.println("[" + this.id + "] 금액" + this.money);
	}
}
