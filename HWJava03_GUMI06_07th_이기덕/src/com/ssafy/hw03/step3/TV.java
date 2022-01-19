package com.ssafy.hw03.step3;

public class TV extends Product {
	private double inch;
	private String type;
	
	
	public TV() {
		super();
		this.inch = 0.0;
		this.type = null;
	}
	public TV(String ispn, String name, int price, int amount, double inch, String type) {
		super(ispn, name, price, amount);
		this.inch = inch;
		this.type = type;
	}
	public double getInch() {
		return inch;
	}
	public void setInch(double inch) {
		this.inch = inch;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\t | 인치: " + inch + "\t | 디스플레이 타입: " + type;
	}
	
	

}
