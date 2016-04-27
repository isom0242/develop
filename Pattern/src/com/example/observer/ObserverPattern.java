package com.example.observer;

import java.util.ArrayList;
import java.util.Random;

public class ObserverPattern {
	public static void main(String[] args) {
		NumberGenerator generator = new RandomNumberGenerator();
		Observer observer1 = new DigitObserver();
		Observer observer2 = new GraphObserver();
		generator.addObserver(observer1);;
		generator.addObserver(observer2);
		generator.execute();
	}
}

interface Observer {
	public abstract void update(NumberGenerator generator);
}

abstract class NumberGenerator {
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	public void deleteObserver(Observer observer) {
		observers.remove(observer);
	}
	public void notifyObservers() {
		for (Observer observer: observers) {
			observer.update(this);
		}
	}
	public abstract int getNumber();
	public abstract void execute();
}

class RandomNumberGenerator extends NumberGenerator {
	private Random random = new Random();
	private int number;
	public int getNumber() {
		return number;
	}
	public void execute() {
		for (int i = 0; i < 20; i++) {
			number = random.nextInt(50);
			notifyObservers();
		}
	}
}

class DigitObserver implements Observer {
	public void update(NumberGenerator generator) {
		System.out.println("DigitObserver:" + generator.getNumber());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}
}

class GraphObserver implements Observer {
	public void update(NumberGenerator generator) {
		System.out.println("GraphObserver:");
		int count = generator.getNumber();
		for (int i = 0; i < count; i++) {
			System.out.print("*");
		}
		System.out.println("");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}
}