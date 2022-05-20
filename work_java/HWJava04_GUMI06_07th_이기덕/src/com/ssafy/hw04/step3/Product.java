package com.ssafy.hw04.step3;

import java.io.Serializable;

public class Product implements Serializable{
	private String ispn;
	private String name;
	private int price;
	private int amount;
	
	public Product() {
		ispn = null;
		name = null;
		price = 0;
		amount = 0;
	}
	
	public Product(String ispn, String name, int price, int amount) {
		this.ispn = ispn;
		this.name = name;
		this.price = price;
		this.amount = amount;
	}

	public String getIspn() {
		return ispn;
	}

	public void setIsPn(String ispn) {
		this.ispn = ispn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "제품 번호: " + ispn + "\t | 제품 명: " + name + "\t | 가격: " + price + "\t | 수량: " + amount;
	}
	
	

}
