package model;

public class ZombieKing extends Unit implements AttackInterface {
	private int shield;

	public ZombieKing(String name, int hp, int atk, int def, int pos, int shield) {
		super(name, hp, atk, def, pos);
		this.shield = shield;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}
	
	@Override
	public void attack(AttackInterface attackUnit) {
		Unit unit = (Unit) attackUnit;
		if (ran.nextInt(100) > 74) {
			int dam = (this.getAtk() - this.getDef()) * (ran.nextInt(150) + 50) / 100;
			if (dam <= 0) {
				dam = 1;
			}
			dam *= 2;
			System.out.println(getName() + "의 필살기");
			System.out.println(dam + "의 피해를 입힘");
			unit.setHp(unit.getHp() - dam);
			System.out.println(unit.getName() + "의 남는 체력 : " + unit.getHp());
		}else {
			super.attack(attackUnit);
		}
	}
}
