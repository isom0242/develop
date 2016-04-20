package com.example.mediator;

import java.util.ArrayList;
import java.util.List;

public class MediatorPattern {
	public static void main(String args[]) {
		Mediator mediator = new ConcreteMediator();
		Colleague c1 = new ConcreteColleaguePlus(5);
		Colleague c2 = new ConcreteColleagueOdd(21);
		Colleague c3 = new ConcreteColleagueEven(30);
		
		c1.setMediator(mediator);
		c2.setMediator(mediator);
		c3.setMediator(mediator);
		
		System.out.println(mediator.getResult());
	}
}

interface Mediator {
	public void add(Colleague colleague);
	public boolean getResult();
}

class ConcreteMediator implements Mediator {
	private List<Colleague> colleagues = new ArrayList<Colleague>();
	public void add(Colleague colleague) {
		colleagues.add(colleague);
	}
	public boolean getResult() {
		for (Colleague colleague: colleagues) {
			if (!colleague.isOkay()) {
				return false;
			}
		}
		return true;
	}
}

abstract class Colleague {
	protected int number;
	protected Mediator mediator;
	Colleague(int number){
		this.number = number;
	}
	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
		mediator.add(this);
	}
	abstract boolean isOkay();
}

class ConcreteColleaguePlus extends Colleague {
	public ConcreteColleaguePlus(int number) {
		super(number);
	}
	public boolean isOkay() {
		if (number > 0) {
			return true;
		}
		return false;
	}
}
class ConcreteColleagueOdd extends Colleague {
	public ConcreteColleagueOdd(int number) {
		super(number);
	}
	public boolean isOkay() {
		if (number % 2 == 1) {
			return true;
		}
		return false;
	}
}
class ConcreteColleagueEven extends Colleague {
	public ConcreteColleagueEven(int number) {
		super(number);
	}
	public boolean isOkay() {
		if (number % 2 == 0) {
			return true;
		}
		return false;
	}
}