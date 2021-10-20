package model;

import java.util.Vector;

public class UnitOrc extends Unit {
	public UnitOrc() {
		setName("오크");
	}

	public void skill() {
		System.out.println("한명에게 2배의 데미지 + 기절");
	}

	@Override
	public void skill(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void skillAttackX(Unit target, int mul) {
		// TODO Auto-generated method stub

	}

	@Override
	public void skillAttackAll(Unit target, int mul) {
		// TODO Auto-generated method stub

	}

	@Override
	public void skillHealAll(Vector list, int num) {
		
	}

}
