package com.example.bridge;

public class CharacterAction {

	public static void main(String[] args) {
		EscapeAction brave = new EscapeAction(new Brave());
		MagicalAction witch = new MagicalAction(new Witch());
		Action monk = new Action(new Monk());
		
		brave.attack();
		brave.damaged();
		brave.magicalAttack();
		monk.damaged();
		witch.magicalAttack();
		witch.damaged();
	}

}

class Action {
	protected AbstractCharacter character;
	Action(AbstractCharacter character) {
		this.character = character;
	}
	public void attack() {
		character.attack();
	}
	public void damaged() {
		character.damaged();
	}
}

class MagicalAction extends Action {
	MagicalAction(AbstractCharacter character) {
		super(character);
	}
	public void magicalAttack() {
		System.out.println("magical attacking");
	}
}

class EscapeAction extends MagicalAction {
	EscapeAction(AbstractCharacter character) {
		super(character);
	}
	public void escape() {
		System.out.println("escaped");
	}
}

abstract class AbstractCharacter {
	protected int hp;
	protected int atk;
	protected int def;
	public abstract void attack();
	public abstract void damaged();
}

class Brave extends AbstractCharacter {
	public void attack() {
		System.out.println("Brave attacking");
	}
	public void damaged() {
		System.out.println("Brave damaged");
	}
}

class Witch extends AbstractCharacter {
	public void attack() {
		System.out.println("Witch attacking");
	}
	public void damaged() {
		System.out.println("Witch damaged");
	}
}

class Monk extends AbstractCharacter {
	public void attack() {
		System.out.println("Monk attacking");
	}
	public void damaged() {
		System.out.println("Monk damaged");
	}
}

