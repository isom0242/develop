package com.example.state;

public class StateHero {
	public static void main(String[] args) {
		Hero hero = new Hero();
		hero.damaged(20);
		hero.print();
//		System.out.println("attack: " + hero.attack());
	}
}

class Hero {
	private int hp;
	private StateInterface state;
	public Hero() {
		hp = 100;
		state = StateHealthy.getInstance();
	}
	public void damaged(int pow) {
		hp -=pow;
		if (hp > 30) {
			state = StateHealthy.getInstance();
		} else if (hp > 10) {
			state = StatePoison.getInstance();
		} else {
			state = StateDying.getInstance();
		}
	}
	public int attack() {
		return state.attack();
	}
	public void print() {
		System.out.println("hp: " + hp);
		state.print();
	}
}

interface StateInterface {
	int attack();
	void print();
}

class StateHealthy implements StateInterface {
	static StateInterface state = new StateHealthy();
	static StateInterface getInstance() {
		return state;
	}
	public int attack() {
		return 1;
	}
	public void print() {
		System.out.println("I'm healthy.");
	}
}

class StatePoison implements StateInterface {
	static StateInterface state = new StatePoison();
	static StateInterface getInstance() {
		return state;
	}
	public int attack() {
		return 2;
	}
	public void print() {
		System.out.println("I'm poisoned.");
	}
}

class StateDying implements StateInterface {
	static StateInterface state = new StateDying();
	static StateInterface getInstance() {
		return state;
	}
	public int attack() {
		return 3;
	}
	public void print() {
		System.out.println("I'm dying.");
	}
}