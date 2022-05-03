package com.ssafy.ssafygo.model.dto;



public class StoreMenu {
	private int id;
	private String name;
	private int price;
	private int storeId;
	
	public StoreMenu(){}
	
	public StoreMenu(int id, String name, int price, int storeId) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.storeId = storeId;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	
	
}
