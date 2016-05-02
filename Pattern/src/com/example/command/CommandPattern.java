package com.example.command;

import java.util.Stack;

public class CommandPattern {

	public static void main(String[] args) {
		Receiver r = new Receiver();
		Command cmd1 = new AttackCommand(r);
		Command cmd2 = new RecoveryCommand(r);

		Invoker invoker = new Invoker();
		invoker.add(cmd1);
		invoker.add(cmd2);
		invoker.run();
	}
}

interface Command {
	public void execute();
}

class AttackCommand implements Command {
	private Receiver receiver;
	public AttackCommand(Receiver receiver) {
		this.receiver = receiver;
	}
	public void execute() {
		receiver.attackAction();
	}
}
class RecoveryCommand implements Command {
	private Receiver receiver;
	public RecoveryCommand(Receiver receiver) {
		this.receiver = receiver;
	}
	public void execute() {
		receiver.recoveryAction();
	}
}

class Receiver {
	public void attackAction() {
		System.out.println("attack action");
	}
	public void recoveryAction() {
		System.out.println("recovery action");
	}
}

class Invoker {
	Stack<Command> stack = new Stack<Command>();
	public void add(Command cmd) {
		stack.add(cmd);
	}
	public void run() {
		for (Command cmd: stack) {
			cmd.execute();
		}
	}
}

