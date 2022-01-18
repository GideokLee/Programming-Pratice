package com.ssafy.hw.step3;

public class ProductMgr {
	private final int MAX_SIZE = 100;
	private Product[] products;
	private int size;
	
	public ProductMgr() {
		products = new Product[MAX_SIZE];
		size = 0;
	}

	public void add(Product product) {
		if(size < MAX_SIZE)
			products[size++] = product;
	}
	
	public void remove(String ispn) {
		boolean togle = false;
		int count = 0;
		Product[] result = new Product[MAX_SIZE];
		for(int i = 0; i< size; i++) {
			if(!products[i].getIspn().equals(ispn))
				result[count++] = products[i];
			else
				togle= true;
		}
		if(togle) {
			products = result;
			size--;
		}
	}
	public Product[] getList() {
		return products;
	}
	public Product searchByIspn(String ispn) {
		for(int i =0; i< size; i++)
			if(products[i].getIspn().equals(ispn))
				return products[i];
		
		return null;
	}
	public Product[] searchByName(String name) {
		Product[] result = new Product[size];
		int count = 0;
		for(int i =0; i < size; i++)
			if(products[i].getName().contains(name))
				result[count++] = products[i];
		
		return result;
	}
	
	public Product[] getTVs() {
		Product[] result = new Product[size];
		int count = 0;
		for(int i =0; i < size; i++)
			if(products[i].getClass() == TV.class)
				result[count++] = products[i];
		
		return result;
	}
	public Product[] getRefrigerators() {
		Product[] result = new Product[size];
		int count = 0;
		for(int i =0; i < size; i++)
			if(products[i].getClass() == Refrigerator.class)
				result[count++] = products[i];
		
		return result;
	}
	
	public int getTotalPrice() {
		int total =0;
		
		for(int i =0; i <size; i++)
			total+=products[i].getPrice() * products[i].getAmount();
		
		return total;
	}
}
