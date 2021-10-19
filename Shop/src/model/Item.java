package model;

public class Item {
	private String name;
	private int price;
	private String category;
	private int cnt;
	
	public Item(String na, int pr, String cate, int cnt) {
		this.name = na;
		this.price = pr;
		this.category = cate;
		this.cnt = cnt;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return this.price;
	}

	public void setCate(String cate) {
		this.category = cate;
	}

	public String getCate() {
		return this.category;
	}
	
	public void addCnt() {
		this.cnt++;
	}
	public void subCnt() {
		this.cnt--;
	}
	
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	public int getCnt() {
		return this.cnt;
	}
	
	public void print() {
		System.out.println("[" + this.name + "]" + "[" + this.price + "]" + "[" + this.category + "]");
	}
	
	public void printAll() {
		System.out.println("[" + this.name + "]" + "[" + this.price + "]" + "[" + this.category + "]"+ "[" + this.cnt + "]");
	}
}
