package model;

import java.util.Vector;

public class UnitBat extends Unit {
	public UnitBat() {
		setName("박쥐");
	}
	
	public void skill() {
		System.out.println("적 한명에게 기절 시전");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void statusAbnormality(Unit target) {
		// TODO Auto-generated method stub
		
	}

	
}
