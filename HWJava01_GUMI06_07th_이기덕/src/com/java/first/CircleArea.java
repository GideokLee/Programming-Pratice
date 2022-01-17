package com.java.first;

public class CircleArea {
	public static void main(String[] args) {
		final double PI = Math.PI;
		int r = 5;
		double result = r * r * PI;
		System.out.println("반디름이 5CM인 원의 넓이는 " + Math.round(result * 10) / 10.0 + "Cm2입니다." );
		
	}
}
