package com.ssafy.school.person;

public class Professor extends Person {
	private String classRoom;
	private String majorSubject;

	public Professor() {
	}

	public Professor(String name, int age, String phone, String classRoom, String majorSubject) {
		super(name, age, phone);
		setClassRoom(classRoom);
		setMajorSubject(majorSubject);
	}

	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

	public String getMajorSubject() {
		return majorSubject;
	}

	public void setMajorSubject(String majorSubject) {
		this.majorSubject = majorSubject;
	}

	@Override
	public String toString() {
		return "Professor [classRoom=" + classRoom + ", majorSubject=" + majorSubject + "]";
	}
}
