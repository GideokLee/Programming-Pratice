package com.ssafy.hw03.step3;

public class Refrigerator extends Product{
	private int volume;

	public Refrigerator() {
		super();
		this.volume = 0;
	}

	public Refrigerator(String ispn, String name, int price, int amount, int volume) {
		super(ispn, name, price, amount);
		this.volume = volume;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return super.toString() + "\t | 용량: " + volume + "L";
	}

	
	
}
