package controller;

import java.util.ArrayList;

import model.RPG_Player;
import model.RPG_Unit;

public class RPG_Guild {
	final int PARTY_SIZE = 4;
	ArrayList<RPG_Unit> guildList = new ArrayList<>();
	RPG_Unit[] partyList;

	public void setGuild() {

		for (int i = 0; i < PARTY_SIZE; i++) {
			String[] n1 = { "박", "이", "김", "최", "유", "지", "오" };
			String[] n2 = { "명", "기", "종", "민", "재", "석", "광" };
			String[] n3 = { "수", "자", "민", "수", "석", "민", "철" };

			String name = n1[MainGame.ran.nextInt(n1.length)];
			name += n2[MainGame.ran.nextInt(n2.length)];
			name += n3[MainGame.ran.nextInt(n3.length)];
			int ran = MainGame.ran.nextInt(8) + 2;
			int hp = ran * 11;
			int atk = ran + 1;
			int def = ran / 2 + 1;
			RPG_Unit temp = new RPG_Unit(name, 1, hp, atk, def, 0);
			guildList.add(temp);
		}
		for (int i = 0; i < PARTY_SIZE; i++) {
			guildList.get(i).setParty(true);
		}
		partyList = new RPG_Unit[PARTY_SIZE];
		int n = 0;
		for (int i = 0; i < guildList.size(); i++) {
			if (guildList.get(i).isParty()) {
				partyList[n] = guildList.get(i);
				n++;
			}
		}
	}

	public RPG_Unit getGuildUnit(int num) {
		return guildList.get(num);
	}

	public void printAllUnitStatus() {
		System.out.println("==================================");
		System.out.println("[골드 : " + (RPG_Player.getPlayerMoney()) + "]");
		System.out.println("============= [길드원] =============");
		for (int i = 0; i < guildList.size(); i++) {
			System.out.print("[" + (i + 1) + "번]");
			System.out.print(" [이름 : " + guildList.get(i).getName() + "]");
			System.out.print(" [레벨 : " + guildList.get(i).getLevel() + "]");
			if (this.guildList.get(i).getRing() != null) {
				System.out.print(" [체력 : " + (guildList.get(i).getHp() + guildList.get(i).getRing().getPower()));
				System.out.println(" / " + (guildList.get(i).getMaxHp() + guildList.get(i).getRing().getPower()) + "]");
			} else {
				System.out.print(" [체력 : " + guildList.get(i).getHp());
				System.out.println(" / " + guildList.get(i).getMaxHp() + "]");
			}
			if (this.guildList.get(i).getWeapon() != null) {
				System.out
						.print("[공격력 : " + (guildList.get(i).getAtk() + guildList.get(i).getWeapon().getPower()) + "]");
			} else {
				System.out.print("[공격력 : " + guildList.get(i).getAtk() + "]");
			}
			if (this.guildList.get(i).getArmor() != null) {
				System.out
						.print(" [방어력 : " + (guildList.get(i).getDef() + guildList.get(i).getArmor().getPower()) + "]");
			} else {
				System.out.print(" [방어력 : " + guildList.get(i).getDef() + "]");
			}
			System.out.println(" [파티중 : " + guildList.get(i).isParty() + "]");
			System.out.println();
		}
		System.out.println("==================================");
	}

	public void printUnitStatus(int num) {
		guildList.get(num).printStatus();
	}

	public void printUnitItem(int num) {
		guildList.get(num).printEquipedItem();
	}

	public void buyUnit() {
		if (RPG_Player.getPlayerMoney() < 5000) {
			return;
		}
		String[] n1 = { "박", "이", "김", "최", "유", "지", "오" };
		String[] n2 = { "명", "기", "종", "민", "재", "석", "광" };
		String[] n3 = { "수", "자", "민", "수", "석", "민", "철" };

		String name = n1[MainGame.ran.nextInt(n1.length)];
		name += n2[MainGame.ran.nextInt(n2.length)];
		name += n3[MainGame.ran.nextInt(n3.length)];
		int ran = MainGame.ran.nextInt(8) + 2;
		int hp = ran * 11;
		int atk = ran + 1;
		int def = ran / 2 + 1;
		RPG_Unit temp = new RPG_Unit(name, 1, hp, atk, def, 0);

		System.out.println("=====================================");
		System.out.print(" [이름 : " + name + "]");
		System.out.print(" [레벨 : " + 1 + "]");
		System.out.print(" [체력 : " + hp);
		System.out.println(" / " + hp + "]");
		System.out.print(" [공격력 : " + atk + "]");
		System.out.println(" [방어력 : " + def + "]");
		System.out.println("길드원을 추가합니다.");
		System.out.println("=====================================");

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		guildList.add(temp);
		int money = RPG_Player.getPlayerMoney();
		RPG_Player.setPlayerMoney(money - 5000);
	}

	public void removeUnit() {
		printAllUnitStatus();
		System.out.println("삭제할 번호를 입력하세요.");
		int sel = MainGame.scan.nextInt() - 1;
		if (guildList.get(sel).isParty()) {
			System.out.println("파티중인 멤버는 삭제할 수 없습니다.");
		} else {
			System.out.println("=================================");
			System.out.print(" [이름 : " + guildList.get(sel).getName() + "]");
			System.out.println("길드원을 삭제합니다.");
			System.out.println("=================================");
			guildList.remove(sel);
		}
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printParty() {
		System.out.println("================ [파티원] ===============");
		for (int i = 0; i < PARTY_SIZE; i++) {
			System.out.print(" [ " + (i + 1) + "번]");
			System.out.print(" [이름 : " + partyList[i].getName() + "]");
			System.out.print(" [레벨 : " + partyList[i].getLevel() + "]");
			System.out.print(" [체력 : " + partyList[i].getHp());
			System.out.println(" / " + partyList[i].getMaxHp() + "]");
			System.out.print(" [공격력 : " + partyList[i].getAtk() + "]");
			System.out.print(" [방어력 : " + partyList[i].getDef() + "]");
			System.out.println(" [파티중 : " + guildList.get(i).isParty() + "]");
			System.out.println("");
		}
		System.out.println("=====================================");
	}

	public void partyChange() {
		printParty();
		System.out.println("교체할 번호를 입력하세요. ");
		int partyNum = MainGame.scan.nextInt() - 1;
		printAllUnitStatus();
		System.out.println("참가할 번호를 입력하세요. ");
		int guildNum = MainGame.scan.nextInt() - 1;

		partyList[partyNum].setParty(false);
		guildList.get(guildNum).setParty(true);
		System.out.println("====================================");
		System.out.print(" [ 이름 : " + partyList[partyNum].getName() + "]");
		System.out.print("에서");
		System.out.print(" [ 이름 : " + guildList.get(guildNum).getName() + "]");
		System.out.println("으로 교체 합니다.");
		System.out.println("====================================");

		int n = 0;
		for (int i = 0; i < guildList.size(); i++) {
			if (guildList.get(i).isParty()) {
				partyList[n] = guildList.get(i);
				n++;
			}
		}

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void guildMenu() {
		while (true) {
			System.out.println("=============== [길드관리] ================");
			System.out.println("[1.길드목록] [2.길드원추가] [3.길드원삭제]\n" + "[4.파티원교체] [5.정렬]  [0.뒤로가기]");
			int sel = MainGame.scan.nextInt();
			if (sel == 1) {
				printAllUnitStatus();
			} else if (sel == 2) {
				buyUnit();
			} else if (sel == 3) {
				removeUnit();
			} else if (sel == 4) {
				partyChange();
			} else if (sel == 5) {
				sort();
			} else if (sel == 0) {
				break;
			}
		}
	}

	public void sort() {
		System.out.println("=============== [정렬할 항목] ================");
		System.out.println("[1. 체력] [2. 공격력] [3. 방어력]");
		int sel = MainGame.scan.nextInt();
		if (sel == 1) {
			for (int i = 0; i < guildList.size(); i++) {
				int check1 = 0;
				int check2 = 0;
				if (guildList.get(i).getRing() != null) {
					check1 = (guildList.get(i).getHp() + guildList.get(i).getRing().getPower());
				} else {
					check1 = guildList.get(i).getHp();
				}
				for (int j = 0; j < guildList.size() - 1; j++) {
					if (guildList.get(j).getRing() != null) {
						check2 = (guildList.get(j).getHp() + guildList.get(j).getRing().getPower());
					} else {
						check2 = guildList.get(j).getHp();
					}
					
					if(check1 > check2) {
						RPG_Unit temp = guildList.get(i);
						guildList.set(i, guildList.get(j));
						guildList.set(j,temp);
					}
					
				}
			}
		} else if (sel == 2) {
			for (int i = 0; i < guildList.size(); i++) {
				int check1 = 0;
				int check2 = 0;
				if (guildList.get(i).getWeapon() != null) {
					check1 = (guildList.get(i).getAtk() + guildList.get(i).getWeapon().getPower());
				} else {
					check1 = guildList.get(i).getAtk();
				}
				for (int j = 0; j < guildList.size() - 1; j++) {
					if (guildList.get(j).getWeapon() != null) {
						check2 = (guildList.get(j).getAtk() + guildList.get(j).getWeapon().getPower());
					} else {
						check2 = guildList.get(j).getAtk();
					}
					
					if(check1 > check2) {
						RPG_Unit temp = guildList.get(i);
						guildList.set(i, guildList.get(j));
						guildList.set(j,temp);
					}
					
				}
			}
		} else if (sel == 3) {
			for (int i = 0; i < guildList.size(); i++) {
				int check1 = 0;
				int check2 = 0;
				if (guildList.get(i).getArmor() != null) {
					check1 = (guildList.get(i).getDef() + guildList.get(i).getArmor().getPower());
				} else {
					check1 = guildList.get(i).getDef();
				}
				for (int j = 0; j < guildList.size() - 1; j++) {
					if (guildList.get(j).getWeapon() != null) {
						check2 = (guildList.get(j).getDef() + guildList.get(j).getArmor().getPower());
					} else {
						check2 = guildList.get(j).getDef();
					}
					
					if(check1 > check2) {
						RPG_Unit temp = guildList.get(i);
						guildList.set(i, guildList.get(j));
						guildList.set(j,temp);
					}
					
				}
			}
		}
	}

	public ArrayList<RPG_Unit> getGuildList() {
		return guildList;
	}
}
