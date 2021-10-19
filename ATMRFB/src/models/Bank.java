package models;

public class Bank {
	private String brand = "";
	public static Bank instance = new Bank();

	public static int log = -1;

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
