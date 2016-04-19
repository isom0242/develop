package com.example.facade;


public class FacadePattern {
	public static void main(String args[]) {
		PageMaker.makePage("foo");
	}
}

enum Person {
	HOGE("hoge", "hoge@example.com"),
	FOO("foo", "foo@example.com"),
	BAR("bar", "bar@example.com"),
	NONE("", "");
	
	private String name;
	private String email;
	
	Person(String name, String email) {
		this.name = name;
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public static Person getPersonal(String name) {
		for (Person p: Person.values()) {
			if (name.equals(p.name)) {
				return p;
			}
		}
		return null;
	}
}

class Database {
	private Database () {
	}
	public static Person getPersonal(String name) {
		// do something
		return Person.getPersonal(name);
	}
}

class HtmlWriter {
	private String html;
	public void title(String title) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>" + title + "</title>");
		sb.append("</head>");
		sb.append("<body>¥n");
		sb.append("<h1>" + title + "</h1>");
		html = sb.toString();
	}
	public void addParagraph(String msg) {
		html += "<p>" + msg + "</p>";
	}
	public void createLink(String href, String caption) {
		this.addParagraph("<a href=\"" + href + "\">" + caption + "</a>");
	}
	public void createMail(String name, String email) {
		this.createLink("mailto:" + email, name);
	}
	public String getHtml() {
		return html;
	}
}

class PageMaker {
	private PageMaker() {}
	public static void makePage(String name) {
		Person person = Database.getPersonal(name);
		HtmlWriter writer = new HtmlWriter();
		writer.title("welcome to " + name + "'s page");
		writer.addParagraph("my name is " + name);
		writer.createMail(name, person.getEmail());
		System.out.println(writer.getHtml());
	}
}