package controller;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import model.User;
public class UserManager {
	private Random ran = new Random();
	public static Scanner scan = new Scanner(System.in);
	private Vector<User> userList = new Vector<>();
	public int userLog = -1;
	public boolean adminLog = false;

	public UserManager() {
		init();
	}

	private void init() {
		String[] a = { "김", "박", "이", "최", "정", "오" };
		String[] b = { "철", "병", "만", "여", "아", "영" };
		String[] c = { "수", "욱", "수", "정", "름", "희" };
		for (int i = 0; i < 10; i++) {
			int r = ran.nextInt(a.length);
			String name = a[r];
			r = ran.nextInt(b.length);
			name += b[r];
			r = ran.nextInt(c.length);
			name += c[r];
			User temp = new User(name, ran.nextInt(10000));
			userList.add(temp);
		}
		userList.add(new User("관리자", 100000));
	}

	public void join() {
		System.out.println("[가입] id 를 입력하세요.");
		String id = scan.next();
		User temp = new User(id, 1000);
		userList.add(temp);
		System.out.println("[메세지] " + temp.getId() + "님 가입을 축하드립니다.");
	}

	public void withdraw() {
		System.out.println("[탈퇴] id 를 입력하세요.");
		String id = scan.next();
		int idx = -1;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getId().equals(id)) {
				idx = i;
			}
		}
		if (idx == -1) {
			System.out.println("존재하지 않은 아이디입니다.");
		} else {
			userList.remove(idx);
			System.out.println("탈퇴 되었습니다.");
		}
	}

	public boolean login() {
		userLog = -1;
		System.out.println("[로그인] id 를 입력하세요.");
		String id = scan.next();
		for (int i = 0; i < userList.size(); i++) {
			if (id.equals(userList.get(i).getId())) {
				if ("관리자".equals(userList.get(i).getId())) {
					userLog = i;
					adminLog = true;
					break;
				} else {
					userLog = i;
					break;
				}
			}
		}
		if (userLog == -1) {
			System.out.println("[메세지] 존재하지 않는 아이디 입니다.");
		} else {
			System.out.println("[메세지] " + userList.get(userLog).getId() + "님 로그인");
			return true;
		}
		return false;
	}

	public void logout() {
		if (userLog != -1) {
			System.out.println("[메세지] " + userList.get(userLog).getId() + "님 로그아웃");
		}
		userLog = -1;
		adminLog = false;
	}

	public void printUser() {
		for (int i = 0; i < userList.size(); i++) {
			System.out.print("[" + i + "]");
			userList.get(i).print();
		}
	}

	public Vector<User> getUserList() {
		return userList;
	}
}