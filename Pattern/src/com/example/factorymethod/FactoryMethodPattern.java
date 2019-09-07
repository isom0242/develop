package com.example.factorymethod;

public class FactoryMethodPattern {

	public static void main(String[] args) {
		
		ParseManager parser1 = new XMLParseManager();
		parser1.read("XML");
		parser1.parse();
		parser1.print();
		
		ParseManager parser2 = new JSONParseManager();
		parser2.read("JSON");
		parser2.parse();
		parser2.print();

	}
}

class XMLParser implements Parser {
	@Override
	public String parse(String s) {
		return s +  " is xml parsed.";
	}
}

class JSONParser implements Parser {
	@Override
	public String parse(String s) {
		return s + " is json parsed.";
	}
}

class XMLParseManager extends ParseManager {
	@Override
	protected Parser makeParser() {
		return new XMLParser();
	}
}

class JSONParseManager extends ParseManager {
	@Override
	protected Parser makeParser() {
		return new JSONParser();
	}
}

interface Parser {
	public String parse(String s);
}

abstract class ParseManager {
	private String s;
	public void read(String s) {
		this.s = s;
	}
	public void parse() {
		s = makeParser().parse(s);
	}
	public void print() {
		System.out.println(s);
	}
	
	abstract protected Parser makeParser();
}
