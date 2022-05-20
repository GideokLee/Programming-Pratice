package com.ssafy.day04.ex09;

public class MainData {
	private int data;

	public void increase(String name) {
		System.out.print(name + "가 " + data);
		data++;
		//this.notify();
		System.out.println("를 " + data + "로 증가시킴");
	}

	public void decrease(String name) {
		/*if (data <= 0) {
			try {
				System.out.println(name + " 대기중...");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/

		System.out.print(name + "가 " + data);
		data--;
		System.out.println("를 " + data + "로 감소시킴");
	}
}