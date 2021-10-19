package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import models.Account;
import models.Bank;
import models.User;

public class FileManager {
	private String userFileName = "users.txt";
	private File userFile = new File(userFileName);

	private String accFileName = "accs.txt";
	private File accFile = new File(accFileName);

	private FileWriter fw = null;
	private FileReader fr = null;
	private BufferedReader br = null;

	public static FileManager instance = new FileManager();
	private UserManager um = UserManager.instance;
	private AccountManager am = AccountManager.instance;

	public void save() {
		String data = makeUsersData();
		try {
			fw = new FileWriter(userFile);
			fw.write(data);
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		data = makeAccsData();
		try {
			fw = new FileWriter(accFile);
			fw.write(data);
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void load() {
		System.out.println(">>>");
		um.clearUsers();
		am.clearAccs();

		try {
			fr = new FileReader(userFile);
			br = new BufferedReader(fr);

			String data = br.readLine();
			Bank.instance.setBrand(data);

			data = br.readLine();
			while (data != null) {
				String info[] = data.split("/");

				int code = Integer.parseInt(info[0]);
				String id = info[1];
				String pw = info[2];
				String name = info[3];

				um.addUser(new User(code, id, pw, name));
				data = br.readLine();
			}
			fr.close();
			br.close();
		} catch (Exception e) {
		}

		try {
			fr = new FileReader(accFile);
			br = new BufferedReader(fr);

			String data = br.readLine();
			while (data != null) {
				String info[] = data.split("/");
				boolean rep = Boolean.parseBoolean(info[0]);
				int userCode = Integer.parseInt(info[1]);
				int accNum = Integer.parseInt(info[2]);
				int money = Integer.parseInt(info[3]);

				Account newAcc = new Account(rep, userCode, accNum, money);
				am.addAccount(newAcc);
				data = br.readLine();
			}
			for (int i = 0; i < am.getAccsSize(); i++) {
				for (int j = 0; j < um.getUserSize(); j++) {
					if (am.getAccount(i).getUserCode() == um.getUser(j).getCode()) {
						um.addAcc(j, am.getAccount(i));
					}
				}
			}

			fr.close();
			br.close();
		} catch (Exception e) {
		}

	}

	private String makeUsersData() {
		String data = Bank.instance.getBrand() + "\n";
		for (int i = 0; i < um.getUserSize(); i++) {
			data += um.getUser(i).getCode() + "/";
			data += um.getUser(i).getId() + "/";
			data += um.getUser(i).getPw() + "/";
			data += um.getUser(i).getName() + "\n";
		}
		data.substring(0, data.length() - 1);
		return data;
	}

	private String makeAccsData() {
		String data = "";
		for (int i = 0; i < am.getAccsSize(); i++) {
			data += am.getAccount(i).getRep() + "/";
			data += am.getAccount(i).getUserCode() + "/";
			data += am.getAccount(i).getAccNum() + "/";
			data += am.getAccount(i).getMoney() + "\n";
		}
		data.substring(0, data.length() - 1);
		return data;
	}
}
