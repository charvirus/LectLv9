package models;

import java.util.ArrayList;

public class User {
	private long code;
	private String id;
	private String pw;
	private String name;
	private ArrayList<Account> accs = null;

	public User(long code, String id, String pw, String name) {
		this.code = code;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.accs = new ArrayList<>();
	}

	public long getCode() {
		return this.code;
	}

	public String getId() {
		return this.id;
	}

	public String getPw() {
		return this.pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAccsSize() {
		return this.accs.size();
	}
	
	public ArrayList<Account> getAccs(){
		return this.accs;
	}
	
	public Account getAccount(int index) {
		return this.accs.get(index);
	}
	
	public void setAccount(int index, Account e) {
		this.accs.set(index, e);
	}
	
	public void addAcc(Account newAcc) {
		this.accs.add(newAcc);
	}
}
