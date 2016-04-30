package com.example.proxy;

public class ProxyPattern {

	public static void main(String[] args) {
		Process p = new EchoProxy();
		p.read();
		p.write();
		p.execute();
		
		Process r = new EchoReal();
		r.read();
		r.write();
		r.execute();
	}
}

interface Process {
	public void read();
	public void write();
	public void execute();
}

class EchoProxy implements Process {
	Process real;
	@Override
	public void read() {
		System.out.println("proxy read");
	}

	@Override
	public void write() {
		System.out.println("proxy write");
	}

	@Override
	public void execute() {
		if (this.real == null) {
			this.real = new EchoReal();
		}
		this.real.execute();
	}
}

class EchoReal implements Process {
	@Override
	public void read() {
		System.out.println("real read");
	}

	@Override
	public void write() {
		System.out.println("real write");
	}

	@Override
	public void execute() {
		System.out.println("real execute");
	}
}