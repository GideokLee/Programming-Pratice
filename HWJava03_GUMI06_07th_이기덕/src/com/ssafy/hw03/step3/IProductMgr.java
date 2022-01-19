package com.ssafy.hw03.step3;

import java.util.ArrayList;

public interface IProductMgr {
	public void add(Product product);
	public void remove(String ispn);
	public ArrayList<Product> getList();
	public Product searchByIspn(String ispn);
	public ArrayList<Product> searchByName(String name);
	public ArrayList<Product> getTVs();
	public ArrayList<Product> getRefrigerators();
	public int getTotalPrice();
	public ArrayList<Product> searchByInch(int inch);
	public ArrayList<Product> searchByVolume(int volume);
	public void setPrice(String ispn, int price);
}
