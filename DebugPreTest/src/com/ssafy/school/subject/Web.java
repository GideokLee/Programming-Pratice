package com.ssafy.school.subject;

public class Web extends Subject {
	private String side;

	public Web() {
	}

	public Web(String name, int level, String side) {
		super(name, level);
		setSide(side);
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	@Override
	public String toString() {
		return "Web [side=" + side + "]";
	}
}