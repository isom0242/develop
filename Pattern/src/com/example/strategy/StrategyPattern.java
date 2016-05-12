package com.example.strategy;

public class StrategyPattern {

	public static void main(String[] args) {
		Battle battle = new Battle();
		battle.attack(new Brave(), 10);
		battle.attack(new Witch(), 10);
	}
}

class Battle {
	public int attack(Hero hero, int enemyDefense) {
		return hero.attack(enemyDefense);
	}
}

interface Hero {
	public int attack(int enemyDefense);
}

class Brave implements Hero {
	private int attack = 100;
	public int attack(int enemyDefense) {
		int damage = Math.max(attack - enemyDefense, 0);
		System.out.println("Brave attacked: " + damage + " damaged");
		return damage;
	}
}

class Witch implements Hero {
	private int attack = 50;
	public int attack(int enemyDefense) {
		int damage = Math.max((int)(attack / 2.0) - enemyDefense, 0);
		System.out.println("Witch attacked: " + damage + " damaged");
		return damage;
	}
}
