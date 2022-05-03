package com.ssafy.ssafygo.model.dto;



public class Receipt {
	private int id;
	private String menuName;
	private int price;
	private String orderDate;
	
	public Receipt() {}
	public Receipt(int id, String menuName, int price, String orderDate) {
		this.id = id;
		this.menuName = menuName;
		this.price = price;
		this.orderDate = orderDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
	
}
