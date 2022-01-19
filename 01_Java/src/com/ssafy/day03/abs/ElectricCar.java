package com.ssafy.day03.abs;

public class ElectricCar extends Vehicle{
	
	
	public ElectricCar(int curX, int curY) {
		super(curX, curY);
	}

	@Override
	public void addFuel() {
		System.out.println("급속 충전");
	}
}


