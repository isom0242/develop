package com.example.visitor;

import java.util.ArrayList;
import java.util.List;

public class VisitorPattern {

	public static void main(String[] args) {
		Dir root = new Dir("root");
		Dir var = new Dir("var");

		File hoge = new File("hoge.txt");
		File foo = new File("foo.txt");

		root.add(var);
		root.add(hoge);
		var.add(foo);

		root.print();
	}
}

class Visitor {
	public void visit(Entity entity) {
		entity.visitor = this;
	}

	public void accept(Entity entity) {

	}
}

abstract class Entity {
	Visitor visitor;
	protected String name;

	abstract void print(String name);

	public void print() {
		print("");
	}
}

class File extends Entity {

	public File(String name) {
		super.name = name;
	}

	public void print(String path) {
		System.out.println(path + "/" + name);
	}
}

class Dir extends Entity {
	private List<Entity> entities = new ArrayList<Entity>();

	public Dir(String name) {
		super.name = name;
	}

	public void print(String path) {
		System.out.println(path);
		for (Entity entity : entities) {
			entity.print(path + "/" + name);
		}
	}

	public Dir add(Entity entity) {
		entities.add(entity);
		return this;
	}
}