package com.example.bridge;

public class BridgePattern {

	public static void main(String[] args) {
		Shape shape1 = new Rect(new DrawDP1());
		Shape shape2 = new Rect(new DrawDP2());
		shape1.draw();
		shape2.draw();
		
		Shape shape3 = new Circle(new DrawDP1());
		Shape shape4 = new Circle(new DrawDP2());
		shape3.draw();
		shape4.draw();
	}
}

abstract class Shape {
	protected AbstractDraw drawObj;
	abstract public void draw();
	public Shape(AbstractDraw drawObj) {
		this.drawObj = drawObj;
	}
	protected void drawRect() {
		drawObj.drawRect();
	}
	protected void drawCircle() {
		drawObj.drawCircle();
	}
}

 class Rect extends Shape {
	public Rect(AbstractDraw drawObj) {
		super(drawObj);
	}

	public void draw() {
		drawRect();
	}
}

class Circle extends Shape {
	public Circle(AbstractDraw drawObj) {
		super(drawObj);
	}

	public void draw() {
		drawCircle();
	}
}

abstract class AbstractDraw {
	abstract public void drawCircle();
	abstract public void drawRect();
}

class DrawDP1 extends AbstractDraw {
	public void drawCircle() {
		CIRCLE_DP1 dp = new CIRCLE_DP1();
		dp.drawCircle1();
	}
	public void drawRect() {
		RECT_DP1 dp = new RECT_DP1();
		dp.drawLine1();
	}
}

class DrawDP2 extends AbstractDraw {
	public void drawCircle() {
		CIRCLE_DP2 dp = new CIRCLE_DP2();
		dp.drawCircle2();
	}
	public void drawRect() {
		RECT_DP2 dp = new RECT_DP2();
		dp.drawLine2();
	}
}

class RECT_DP1 {
	public void drawLine1() {
		System.out.println("use draw line 1");
	}
}

class RECT_DP2 {
	public void drawLine2() {
		System.out.println("use draw line 2");
	}
}

class CIRCLE_DP1 {
	public void drawCircle1() {
		System.out.println("use draw circle 1");
	}
}

class CIRCLE_DP2 {
	public void drawCircle2() {
		System.out.println("use draw circle 2");
	}
}