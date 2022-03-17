package com.ssafy.school.subject;

public class Subject {
	private String name;
	private int level;

	public Subject() {
	}

	public Subject(String name, int level) {
		setName(name);
		setLevel(level);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append("\t").append(getLevel());
		return sb.toString();
	}
}
