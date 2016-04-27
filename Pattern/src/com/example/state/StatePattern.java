package com.example.state;

public class StatePattern {

	public static void main(String[] args) {
		Context context = new Context();
		context.print();

		context.toDisease();
		context.print();
		
		context.toRecover();
		context.print();
	}
}

class Context {
	protected State state = Healthy.getInstance();
	public void print() {
		state.print();
	}
	public void toDisease() {
		state.disease(this);
	}
	public void toRecover() {
		state.recover(this);
	}
	
}

interface State {
	public void print();
	public void disease(Context context);
	public void recover(Context context);
}

class Healthy implements State {
	private static Healthy healthy = new Healthy();
	private Healthy(){}
	public static State getInstance() {
		return healthy;
	}
	public void print() {
		System.out.println("I'm healthy.");
	}
	public void disease(Context context) {
		context.state = Sickness.getInstance();
	}
	public void recover(Context context) {
		context.state = Healthy.getInstance();
	}
}

class Sickness implements State {
	private static Sickness sickness = new Sickness();
	private Sickness(){}
	public static State getInstance() {
		return sickness;
	}
	public void print() {
		System.out.println("I'm sickness.");
	}
	public void disease(Context context) {
		context.state = Sickness.getInstance();
	}
	public void recover(Context context) {
		context.state = Healthy.getInstance();
	}
}