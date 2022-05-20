package com.ssafy.day03.abs;


public class VehicleTest {

	public static void main(String[] args) {
		// 여러 가지 운송 수단들을 관리해보자.
		Vehicle[] vehicles = {new DieselSUV(0, 0) , new ElectricCar(0, 0)};
		
		for(Vehicle v : vehicles) {
			v.addFuel();
			v.reportPosition();
		}
		
	}
}