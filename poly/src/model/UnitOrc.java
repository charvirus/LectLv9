package model;

public class UnitOrc extends Unit{
	public UnitOrc(){
		setName("오크");
	}
	
	public void skill() {
		System.out.println("한명에게 2배의 데미지 + 기절");
	}
}
