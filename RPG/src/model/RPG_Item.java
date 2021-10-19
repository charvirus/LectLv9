package model;

public class RPG_Item {
	private static final int WEAPON = 1;
	private static final int ARMOR = 2;
	private static final int RING = 3;
	private int kind;
	private String name;
	private int power;
	private int price;

	public void setItem(int k, String n, int p, int pr) {
		this.kind = k;
		this.name = n;
		this.power = p;
		this.price = pr;
	}

	public static int getWeapon() {
		return WEAPON;
	}

	public static int getArmor() {
		return ARMOR;
	}

	public static int getRing() {
		return RING;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
