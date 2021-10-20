package model;

public class Hero extends Unit {

	private int cnt = 3;

	public Hero(String name, int hp, int atk, int def, int pos) {
		super(name, hp, atk, def, pos);
	}

	public int getcnt() {
		return cnt;
	}

	public void drink() {
		if (cnt > 0) {
			cnt--;
			this.setHp(this.getHp() + 100);
			System.out.println("체력이 100 회복 됨");
			System.out.println("현재 체력 : " + this.getHp());
		} else {
			System.out.println("물약 부족");
		}
	}

	@Override
	public void attack(Unit target) {
		if (target instanceof ZombieKing) {
			if (((ZombieKing) target).getShield() > 0) {
				int dam = (this.getAtk() - this.getDef()) * (ran.nextInt(150) + 50) / 100;
				if (dam <= 0) {
					dam = 1;
				}
				System.out.println(getName() + "의 공격");
				System.out.println(dam + "의 피해 입힘");
				((ZombieKing) target).setShield(((ZombieKing) target).getShield() - dam);
				if (((ZombieKing) target).getShield() <= 0) {
					System.out.println("쉴드 파괴");
					((ZombieKing) target).setShield(0);
				}
				System.out.println(target.getName() + "의 남는 체력 : " + target.getHp() + "(쉴드 : "
						+ ((ZombieKing) target).getShield() + ")");
			}else {
				super.attack(target);
			}
		}else {
			super.attack(target);			
		}
	}
}
