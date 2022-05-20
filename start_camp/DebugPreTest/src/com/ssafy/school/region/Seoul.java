package com.ssafy.school.region;

public class Seoul extends Region {
	private String roomNumber;

	public Seoul() {
	}

	public Seoul(String name, int studentCount, String roomNumber) {
		super(name, studentCount);
		setRoomNumber(roomNumber);
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	@Override
	public String toString() {
		return "Seoul [roomNumber=" + roomNumber + "]";
	}
}