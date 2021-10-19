package model;

public class RPG_Unit {
	String name;
	int level;
	int hp;
	int maxHp;
	int atk;
	int def;
	int exp;
	boolean party;
	RPG_Item weapon;
	RPG_Item armor;
	RPG_Item ring;

	public RPG_Unit(String n, int l, int h, int a, int d, int e) {
		this.name = n;
		this.level = l;
		this.maxHp = h;
		this.atk = a;
		this.def = d;
		this.exp = e;
		this.hp = this.maxHp;
		this.party = false;
		this.weapon = null;
		this.armor = null;
		this.ring = null;
	}

	public RPG_Unit(String n, int l, int h, int a, int d, int e, boolean p) {
		this.name = n;
		this.level = l;
		this.maxHp = h;
		this.atk = a;
		this.def = d;
		this.exp = e;
		this.hp = this.maxHp;
		this.party = p;
		this.weapon = null;
		this.armor = null;
		this.ring = null;
	}

	public void setItem(RPG_Item w, RPG_Item a, RPG_Item r) {
		this.weapon = w;
		this.armor = a;
		this.ring = r;
	}

	public void printStatus() {
		System.out.print("[ 이름 : " + this.name + " ]");
		System.out.print("[ 레벨 : " + this.level + " ]");
		if (this.ring != null) {
			System.out.print(" [ 체력 : " + hp + "+" + this.ring.getPower() + " ] ");
		} else {
			System.out.print(" [ 체력 : " + hp);
		}
		if (this.ring != null) {
			System.out.println(" / " + this.maxHp + " + " + this.ring.getPower() + " ] ");
		} else {
			System.out.println(" / " + this.maxHp + " ] ");
		}
		if (this.weapon != null) {
			System.out.print(" [ 공격력 : " + this.atk + " + " + this.weapon.getPower() + " ] ");
		} else {
			System.out.print(" [ 공격력 : " + this.atk + " ] ");
		}
		if (this.armor != null) {
			System.out.println(" [ 방어력 : " + this.def + " + " + this.armor.getPower() + " ] ");
		} else {
			System.out.println(" [ 방어력 : " + this.def + " ] ");
		}
		System.out.println(" [ 파티중 : " + this.party + " ] ");
	}

	public void printEquipedItem() {

		if (this.weapon == null) {
			System.out.println(" [ 무기 : 없음 ] ");
		} else {
			System.out.println(" [ 무기 : " + this.weapon.getName() + " ] ");
		}
		if (this.armor == null) {
			System.out.println(" [ 방어구 : 없음 ] ");
		} else {
			System.out.println(" [ 방어구 : " + this.armor.getName() + " ] ");
		}

		if (this.ring == null) {
			System.out.println(" [ 반지 : 없음 ] ");
		} else {
			System.out.println(" [ 반지 : " + this.ring.getName() + " ] ");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public boolean isParty() {
		return party;
	}

	public void setParty(boolean party) {
		this.party = party;
	}

	public RPG_Item getWeapon() {
		return weapon;
	}

	public void setWeapon(RPG_Item weapon) {
		this.weapon = weapon;
	}

	public RPG_Item getArmor() {
		return armor;
	}

	public void setArmor(RPG_Item armor) {
		this.armor = armor;
	}

	public RPG_Item getRing() {
		return ring;
	}

	public void setRing(RPG_Item ring) {
		this.ring = ring;
	}
	
	
}
