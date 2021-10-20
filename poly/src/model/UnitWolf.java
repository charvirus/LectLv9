package model;

import java.util.Vector;

public class UnitWolf extends Unit{
	public UnitWolf(){
		setName("늑대");
	}
	
	public void skill() {
		System.out.println("적 전체에게 공격력의 절반 데미지");
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




}
