package com.ssafy.day03.abs;

public class DieselSUV extends Vehicle{

	public DieselSUV(int curX, int curY) {
		super(curX, curY);
	}

	@Override
	public void addFuel() {
		System.out.println("주유소에서 급유");
	}
	
}


