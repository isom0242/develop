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

		Visitor v = new ConcreteVisitor();
		root.accept(v);
	}
}

interface Visitor {
	public void visit(File f);
	public void visit(Dir d);
}
class ConcreteVisitor implements Visitor {
	private String path = "";

	public void visit(File f) {
		System.out.println(path + "/" + f.name);
	}
	public void visit(Dir d) {
		String savePath = path;
		path += "/" + d.name;
		System.out.println(path);
		for (Entity entity : d.getEntities()) {
			entity.accept(this);
		}
		path = savePath;
	}
}

abstract class Entity {
	protected String name;
	abstract void accept(Visitor v);
}

class File extends Entity {
	public File(String name) {
		super.name = name;
	}
	public void accept(Visitor v) {
		v.visit(this);
	}
}

class Dir extends Entity {
	private List<Entity> entities = new ArrayList<Entity>();

	public Dir(String name) {
		super.name = name;
	}
	public void accept(Visitor v) {
		v.visit(this);
	}
	public Dir add(Entity entity) {
		entities.add(entity);
		return this;
	}
	public List<Entity> getEntities() {
		return entities;
	}
}