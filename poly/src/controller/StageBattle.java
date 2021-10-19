package controller;

import java.util.Random;
import java.util.Vector;

import model.Player;
import model.Stage;
import model.Unit;

public class StageBattle extends Stage {
	UnitManager unitManager = new UnitManager();
	Vector<Player> playerList = null;
	Vector<Unit> monList = null;
	Random ran = new Random();
	int monDead = 0;
	int playerDead = 0;

	public void init() {
		unitManager.mon_list.clear();
		unitManager.monster_rand_set(4);
		playerList = null;
		playerList = unitManager.player_list;
		monList = null;
		monList = unitManager.mon_list;
		monDead = monList.size();
		playerDead = playerList.size();
	}

	void print_character() {
		System.out.println("=====[BATTLE]=====");
		System.out.println("=====[PLAYER]=====");
		for (int i = 0; i < playerList.size(); i++) {
			playerList.get(i).printData();
		}
		System.out.println("=====[MONSTER]=====");
		for (int i = 0; i < monList.size(); i++) {
			monList.get(i).printData();
		}
	}

	void player_attack(int index) {
		Player p = playerList.get(index);
		if (p.getCurhp() <= 0)
			return;
		System.out.println("=====[메뉴 선택]=====");
		System.out.println("[" + p.getName() + "] [1.어택] [2.스킬]");
		int sel = GameManager.scan.nextInt();
		if (sel == 1) {
			while (true) {
				int idx = ran.nextInt(monList.size());

				if (monList.get(idx).getCurhp() > 0) {
					p.attack(monList.get(idx));
					break;
				}
			}
		} else if (sel == 2) {
		}
	}

	void monster_attack(int index) {
		Unit m = monList.get(index);
		if (m.getCurhp() <= 0)
			return;
		while (true) {
			int skill = ran.nextInt(100);
			int idx = ran.nextInt(playerList.size());
			if (skill > 74) {
				System.out.println("몬스터 ["+m.getName()+"]의 스킬 발동");
				
			} else {
				if (playerList.get(idx).getCurhp() > 0) {
					m.attack(playerList.get(idx));
					break;
				}
			}
		}
	}

	void check_live() {
		int num = 0;
		for (int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i).getCurhp() <= 0) {
				num++;
			}
		}
		playerDead = playerList.size() - num;
		num = 0;

		for (int i = 0; i < monList.size(); i++) {
			if (monList.get(i).getCurhp() <= 0) {
				num++;
			}
		}
		monDead = monList.size() - num;
	}

	@Override
	public boolean update() {
		boolean run = true;
		int p_index = 0;
		int m_index = 0;
		boolean turn = true;

		while (run) {
			if (turn) {
				print_character();
				if (p_index < playerList.size()) {
					player_attack(p_index);
					p_index++;
				} else {
					turn = !turn;
					p_index = 0;
				}
			} else if (!turn) {
				if (m_index < monList.size()) {
					monster_attack(m_index);
					m_index++;
				} else {
					turn = !turn;
					m_index = 0;
				}
			}
			check_live();
			if (monDead <= 0 || playerDead <= 0)
				break;
		}
		GameManager.setNextStage("LOBBY");
		return false;
	}
}
