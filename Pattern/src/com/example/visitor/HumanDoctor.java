package com.example.visitor;

public class HumanDoctor {

	public static void main(String[] args) {
		Visitor2 v = new Doctor();

		Man man1 = new Man("john", 1.8f, 60);
//		Woman woman1 = new Woman("mary", 1.8f, 60);

		man1.accept(v);
		v.printBmi();

	}
}
interface Visitor2 {
	void visit(Man v);
	void visit(Woman v);
	void printBmi();
}
class Doctor implements Visitor2 {
	private float bmi;
	public void visit(Man v) {
		bmi = v.getWeight() / (v.getHeight() * v.getHeight());
	}
	public void visit(Woman v) {
		bmi = v.getWeight() / (v.getHeight() * v.getHeight()) * 1.05f;
	}
	public void printBmi() {
		System.out.println(bmi);
	}
}

abstract class Human {
	protected String name;
	protected float weight;
	protected float height;
	public String getName() {
		return name;
	}
	public float getWeight() {
		return weight;
	}
	public float getHeight() {
		return height;
	}
	public Human(String name, float height, float weight) {
		this.name = name;
		this.height = height;
		this.weight = weight;
	}
	abstract void accept(Visitor2 v);
}

class Man extends Human {
	public Man(String name, float height, float weight) {
		super(name, height, weight);
	}
	public void accept(Visitor2 v) {
		v.visit(this);
	}
}

class Woman extends Human {
	public Woman(String name, float height, float weight) {
		super(name,height, weight);
	}
	public void accept(Visitor2 v) {
		v.visit(this);
	}
}
