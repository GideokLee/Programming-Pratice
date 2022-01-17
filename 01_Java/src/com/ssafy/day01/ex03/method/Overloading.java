package com.ssafy.day01.ex03.method;

public class Overloading {
	
	// Method Overloading 적용 대상
	int add(int a, int b) {
		return a + b;
	}
	
	// argument 개수가 다른 경우
	int add(int a, int b, int c) {
		return a + b + c;
	}
	
	// argument의 데이터 타입이 다른 경우
	int add(int a, short b) {
		return a + b;
	}
	
	// argument의 개수와 데이터 타입이 다른 경우
	int add(int a, short b, short c) {
		return a + b + c;
	}
	
	int add(short a, short b, short c) {
		return a + b + c;
	}
	
}
