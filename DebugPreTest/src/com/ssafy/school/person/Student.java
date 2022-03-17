package com.ssafy.school.person;

public class Student extends Person {
	private String address;
	private String region;
	private boolean isAttendance;

	public Student() {
	}

	public Student(String name, int age, String phone, String address, String region, boolean isAttendance) {
		super(name, age, phone);
		setAddress(address);
		setRegion(region);
		setAttendance(isAttendance);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public boolean isAttendance() {
		return isAttendance;
	}

	public void setAttendance(boolean isAttendance) {
		this.isAttendance = isAttendance;
	}

	@Override
	public String toString() {
		return "Student [address=" + address + ", region=" + region + ", isAttendance=" + isAttendance + "]";
	}
	
}