package com.example.memento;

import java.util.ArrayDeque;
import java.util.Deque;

public class MementoPattern {

	public static void main(String[] args) {
		Caretaker<Memento> caretaker = new Caretaker<Memento>();
		Hero h1 = new Hero(100);
		caretaker.put(h1.createMemento()); // mementoをスタックに保存
		h1.damaged(15);
		h1.damaged(17);
		h1.damaged(22);
		h1.damaged(10);
		System.out.println("HP: " + h1.getHp());
		h1.setMemento(caretaker.pull()); // スタックからリカバリ
		System.out.println("HP: " + h1.getHp());


	}

}

class Memento {
	int hp;
	Memento(int hp) {
		this.hp = hp;
	}
}

class Hero {
	private int hp;
	public Hero(int hp) {
		this.hp = hp;
	}
	public int getHp() {
		return hp;
	}
	Memento createMemento() {
		return new Memento(hp);
	}
	void setMemento(Memento memento) {
		hp = memento.hp;
	}
	void damaged(int damage) {
		hp -=damage;
	}
}

class Caretaker<T> {
	Deque<T> history = new ArrayDeque<T>();
	void put(T memento) {
		history.push(memento);
	}
	T pull() {
		return history.poll();
	}
}