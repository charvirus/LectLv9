package controller;

import java.util.ArrayList;

import model.RPG_Item;
import model.RPG_Player;

public class RPG_Inventory {
	ArrayList<RPG_Item> itemList = new ArrayList<>();

	public void inventoryMenu() {
		while (true) {
			System.out.println("============ [인벤메뉴] =============");
			System.out.println("[1.착용] [2.판매] [0.뒤로가기]");
			int sel = MainGame.scan.nextInt();
			if (sel == 0) {
				break;
			}
			if (sel == 1) {
				equipMenu();
			}
			if (sel == 2) {
				sellMenu();
			}
		}
	}

	public void equipMenu() {
		RPG_Guild guild = RPG_Player.getGuild();
		guild.printAllUnitStatus();
		System.out.println("아이템 착용할 길드원을 선택하세요.");
		int selUnit = MainGame.scan.nextInt() - 1;
		while (true) {

			guild.printUnitStatus(selUnit);
			guild.printUnitItem(selUnit);
			printItemList();
			System.out.println("착용할 아이템 번호를 입력하세요 [0. 뒤로가기]");
			int selEquip = MainGame.scan.nextInt();
			if (selEquip == 0) {
				break;
			}
			selEquip--;
			if (itemList.get(selEquip).getKind() == RPG_Item.getWeapon()) {
				if (RPG_Player.getGuildUnit(selUnit).getWeapon() != null) {
					itemList.add(RPG_Player.getGuildUnit(selUnit).getWeapon());
				}
				RPG_Player.getGuildUnit(selUnit).setWeapon(itemList.get(selEquip));
			} else if (itemList.get(selEquip).getKind() == RPG_Item.getArmor()) {
				if (RPG_Player.getGuildUnit(selUnit).getArmor() != null) {
					itemList.add(RPG_Player.getGuildUnit(selUnit).getArmor());
				}
				RPG_Player.getGuildUnit(selUnit).setArmor(itemList.get(selEquip));
			} else if (itemList.get(selEquip).getKind() == RPG_Item.getRing()) {
				if (RPG_Player.getGuildUnit(selUnit).getRing() != null) {
					itemList.add(RPG_Player.getGuildUnit(selUnit).getRing());
				}
				RPG_Player.getGuildUnit(selUnit).setRing(itemList.get(selEquip));
			}
			itemList.remove(selEquip);
		}
	}

	public void printItemList() {
		System.out.println("============ [아이템리스트] ==============");
		for (int i = 0; i < itemList.size(); i++) {
			System.out.print(" [ " + (i + 1) + "번 ] ");
			System.out.print(" [ 이름 : " + itemList.get(i).getName() + " ] ");
			System.out.print(" [ 능력 : " + itemList.get(i).getPower() + " ] ");
			System.out.print(" [ 가격 : " + itemList.get(i).getPrice() + " ] ");
			System.out.println();
		}
	}

	public void sellMenu() {
		while (true) {
			printItemList();
			System.out.println(" [ 골드 : " + RPG_Player.getPlayerMoney() + " ] ");
			System.out.println(" 판매할 아이템 번호를 입력하세요. (50 % 세금) [0. 뒤로가기]");
			int selSell = MainGame.scan.nextInt();

			if (selSell == 0) {
				break;
			}
			System.out.println(itemList.get(selSell - 1).getName() + "을 판매합니다.");

			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			int playerMoney = RPG_Player.getPlayerMoney();

			RPG_Player.setPlayerMoney(playerMoney + itemList.get(selSell - 1).getPrice() / 2);
			itemList.remove(selSell - 1);
		}
	}

	public void addItem(RPG_Item item) {
		itemList.add(item);
	}

	public ArrayList<RPG_Item> getitemList() {
		return itemList;
	}
}
