package model;

public class UnitBat extends Unit {
	public UnitBat() {
		setName("박쥐");
	}
	
	public void skill() {
		System.out.println("적 한명에게 기절 시전");
	}
}
