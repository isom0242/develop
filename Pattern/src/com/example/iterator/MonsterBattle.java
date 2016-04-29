package com.example.iterator;

import java.util.ArrayList;
import java.util.List;

public class MonsterBattle {
	public static void main(String[] args) {
		Monster m1 = new Monster("slime");
		Monster m2 = new Monster("ikkaku usagi");
		Monster m3 = new Monster("ogarasu");
		Monster m4 = new Monster("oarikui");

		Monsters monsters = new Monsters();
		monsters.add(m1).add(m2).add(m3).add(m4);

		Iterator iterator = monsters.iterator();
		while (iterator.hasNext()) {
			Monster m = (Monster)(iterator.next());
			System.out.println(m.getName());
		}

	}
}

class Monster {
	private String name;
	public Monster(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
}

class Monsters implements AggregateIterator {
	private List<Monster> monsters = new ArrayList<Monster>();
	public Monsters add(Monster monster) {
		monsters.add(monster);
		return this;
	}
	public List<Monster> getMonsters() {
		return this.monsters;
	}
	@Override
	public Iterator iterator() {
		return new MonstersIterator(this);
	}
}

class MonstersIterator implements Iterator {
	private int index;
	private Monsters monsters;
	public MonstersIterator(Monsters monsters) {
		this.index = 0;
		this.monsters = monsters;
	}
	@Override
	public boolean hasNext() {
		if (monsters.getMonsters().size() > index) {
			return true;
		}
		return false;
	}

	@Override
	public Object next() {
		Object obj = monsters.getMonsters().get(index);
		index++;
		return obj;
	}
	
}
