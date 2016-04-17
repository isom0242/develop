package com.example.chainofresponsibility;

public class ChainOfResponsibility {
	public static void main(String[] args) {
		Handler h1 = new ConcreteHandler1();
		Handler h2 = new ConcreteHandler2();
		
		h1.setNext(h2);
		h1.handle(103);
//		System.out.println("ok");
	}
}

abstract class Handler {
	private Handler next;
	public void handle(int num) {
		if (request(num)) {
			System.out.println(num + " resolved");
		} else if (next != null) {
			next.handle(num);
		} else {
			System.out.println(num + " not resolved");
		}
	}
	protected abstract boolean request(int num);
	public Handler setNext(Handler handler) {
		this.next = handler;
		return handler;
	}
}

class ConcreteHandler1 extends Handler {
	protected boolean request(int num) {
		return false;
	}
}

class ConcreteHandler2 extends Handler {
	protected boolean request(int num) {
		if (num < 100) {
			return true;
		}
		return false;
	}
	
}