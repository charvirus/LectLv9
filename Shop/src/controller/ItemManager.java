package controller;

import java.util.Vector;

import model.Cart;
import model.Item;
import model.User;


public class ItemManager {
	Vector<String> category = new Vector<>();
	Vector<Item> itemList = new Vector<>();
	Vector<Cart> jangList = new Vector<>();
	Vector<Cart> log = new Vector<>();

	public ItemManager() {
		init();
	}

	void init() {
		category.add("과자");
		category.add("생선");
		category.add("육류");
		category.add("음료수");

		Item temp = new Item("새우깡", 1000, category.get(0), 5);
		itemList.add(temp);
		temp = new Item("고등어", 2000, category.get(1), 5);
		itemList.add(temp);
		temp = new Item("칸쵸", 3600, category.get(0), 5);
		itemList.add(temp);
		temp = new Item("소고기", 6500, category.get(2), 5);
		itemList.add(temp);
		temp = new Item("콜라", 500, category.get(3), 5);
		itemList.add(temp);
		temp = new Item("새우", 1800, category.get(1), 5);
		itemList.add(temp);
	}

	public void adminprintJang() {
		Vector<Item> tempItemList = itemList;
		for (int i = 0; i < tempItemList.size() - 1; i++) {
			for (int j = 0; j < tempItemList.size() - 1 - i; j++) {
				if (5 - tempItemList.get(j).getCnt() * tempItemList.get(j).getPrice() < 5
						- tempItemList.get(j + 1).getCnt() * tempItemList.get(j + 1).getPrice()) {
					Item temp = tempItemList.get(j);
					tempItemList.set(j, tempItemList.get(j + 1));
					tempItemList.set(j + 1, temp);
				}
			}

		}

		for (int i = 0; i < tempItemList.size(); i++) {
			String str = "";
			str += tempItemList.get(i).getCate() + ":";
			str += tempItemList.get(i).getName() + ":";
			str += tempItemList.get(i).getCnt() * tempItemList.get(i).getPrice();
			System.out.println(str);
		}
		System.out.println("-------------------");
		for (int i = 0; i < log.size(); i++) {
			log.get(i).printLog();
		}
	}

	public void printJang(User u) {
		for (int i = 0; i < jangList.size(); i++) {
			if (u.getId().equals(jangList.get(i).getUserId())) {
				System.out.print("[" + i + "] ");
				jangList.get(i).print();
			}
		}
	}

	public void printCategory() {
		for (int i = 0; i < category.size(); i++) {
			System.out.println("[" + i + "] " + category.get(i));
		}
	}

	public void printItemList() {
		for (int i = 0; i < itemList.size(); i++) {
			System.out.print("[" + i + "]");
			itemList.get(i).print();
		}
	}

	public void printItemList(int caID) {
		int n = 0;
		for (int i = 0; i < itemList.size(); i++) {
			if (category.get(caID).equals(itemList.get(i).getCate())) {
				System.out.print("[" + n + "]");
				itemList.get(i).print();
				n++;
			}
		}
	}

	public int itemListTotal(User u) {
		int sum = 0;
		for (int i = 0; i < jangList.size(); i++) {
			if (u.getId().equals(jangList.get(i).getUserId())) {
				sum += jangList.get(i).getPrice();
			}
		}
		return sum;
	}

	public int itemListCnt(User u) {
		int cnt = 0;
		for (int i = 0; i < jangList.size(); i++) {
			if (u.getId().equals(jangList.get(i).getUserId())) {
				cnt++;
			}
		}
		return cnt;
	}

	public void addItem() {
		System.out.println("[아이템추가] 아이템 이름을 입력하세요. ");
		String name = UserManager.scan.next();
		System.out.println("[아이템추가] 가격을 입력하세요. ");
		int price = UserManager.scan.nextInt();
		printCategory();
		System.out.println("[아이템추가] 카테고리를 입력하세요.");
		int sel = UserManager.scan.nextInt();
		System.out.println("[아이템추가] 수량을 입력하세요.");
		int cnt = UserManager.scan.nextInt();
		Item temp = new Item(name, price, category.get(sel), cnt);
		itemList.add(temp);
	}

	public void delItem() {
		printItemList();
		System.out.println("[아이템 삭제] 삭제할 아이템 번호를 입력하세요. ");
		int sel = UserManager.scan.nextInt();
		if (sel >= 0 && sel < itemList.size()) {
			itemList.remove(sel);
		} else {
			System.out.println("유효한 번호를 입력하세요.");
		}
	}

	public void addCategory() {
		System.out.println("[카테고리추가] 카테고리 이름을 입력하세요. ");
		String name = UserManager.scan.next();
		category.add(name);
	}

	public void delCategory() {
		printCategory();
		System.out.println("[아이템 삭제] 삭제할 카테고리 번호를 입력하세요. ");
		int sel = UserManager.scan.nextInt();
		if (sel >= 0 && sel < category.size()) {
			category.remove(sel);
		} else {
			System.out.println("유효한 번호를 입력하세요.");
		}
	}

	public void addCart(String usId, int caID, int itemID) {
		int n = 0;
		Cart temp = new Cart();
		temp.setUserId(usId);
		for (int i = 0; i < itemList.size(); i++) {
			if (category.get(caID).equals(itemList.get(i).getCate())) {
				if (itemID == n) {
					temp.setItemName(itemList.get(i).getName());
					temp.setPrice(itemList.get(i).getPrice());
				}
				n++;
			}
		}
		jangList.add(temp);
	}

	public void delJangItem(int inp) {
		jangList.remove(inp);
	}

	public void delUserJangItem(User user) {
		for (int i = 0; i < jangList.size(); i++) {
			if (user.getId().equals(jangList.get(i).getUserId())) {
				for (int j = 0; j < itemList.size(); j++) {
					if (itemList.get(j).getName().equals(jangList.get(i).getItemName())) {
						itemList.get(j).subCnt();
					}
				}
			}
		}
		for (int i = 0; i < jangList.size(); i++) {
			log.add(jangList.get(i));
		}
		for (int i = jangList.size() - 1; i >= 0; i--) {
			if (user.getId().equals(jangList.get(i).getUserId())) {
				jangList.remove(i);
			}
		}

	}

	public int getItemCnt(int caID, int itID) {
		int cnt = 0;
		int n = 0;
		for (int i = 0; i < itemList.size(); i++) {
			if (category.get(caID).equals(itemList.get(i).getCate())) {
				if (itID == n) {
					cnt = itemList.get(i).getCnt();
				}
				n++;
			}
		}
		return cnt;
	}
}
