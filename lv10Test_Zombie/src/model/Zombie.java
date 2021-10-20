package model;

public class Zombie extends Unit {

	public Zombie(String name, int hp, int atk, int def, int pos) {
		super(name, hp, atk, def, pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(Unit target) {
		super.attack(target);
	}
}
