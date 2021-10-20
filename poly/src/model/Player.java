package model;

import java.util.Vector;

public class Player extends Unit {
	public Player(String na, int max, int pow) {
		super(na, max, pow);
	}

	@Override
	public void skill(String name) {

	}

	@Override
	public void skillAttackX(Unit target, int mul) {
		System.out.println("[스킬 사용]");
		target.setCurhp(target.getCurhp() - getPower() * mul);
		System.out.println("[" + getName() + "] 이 " + "[" + target.getName() + "] 에게 "
				+ (getPower() * mul) + "의 데미지를 입힙니다. ");
		if (target.getCurhp() <= 0) {
			System.out.println("[" + target.getName() + "] 을 처치했습니다.");
			target.setCurhp(0);
		}
	}

	@Override
	public void skillAttackAll(Unit target, int mul) {
		System.out.println("[스킬 사용]");
		target.setCurhp(target.getCurhp() - getPower() * mul);
		System.out.println("[" + getName() + "] 이 " + "[" + target.getName() + "] 에게 " + getPower() + "의 데미지를 입힙니다. ");
		if (target.getCurhp() <= 0) {
			System.out.println("[" + target.getName() + "] 을 처치했습니다.");
			target.setCurhp(0);
		}
	}

	@Override
	public void skillHealAll(Vector list, int num) {
		System.out.println("[스킬 사용]");
		for (int i = 0; i < list.size(); i++) {
			Player p = (Player) list.get(i);			
			if (p.getCurhp() + 70 > p.getMaxhp()) {
				p.setCurhp(p.getMaxhp());
			} else {
				p.setCurhp(p.getCurhp() + 70);
			}
		}
	}

	@Override
	public void statusAbnormality(Unit target) {
		// TODO Auto-generated method stub
		
	}

}
