package com.ssafy.hw03.step3;

import java.util.ArrayList;

public class ProductMgrImpl implements IProductMgr{
	
	private ArrayList<Product> products;
	private static ProductMgrImpl instance;
	
	private ProductMgrImpl() {
		products = new ArrayList<Product>();
	}
	
	public static ProductMgrImpl getInstance() {
		if(instance ==null) {
			instance = new ProductMgrImpl();
		}
		return instance;
	}
	
	
	@Override
	public void add(Product product) {
		products.add(product);
	}

	@Override
	public void remove(String ispn) {
		for(int i =0; i<products.size(); i++) {
			if(products.get(i).getIspn().equals(ispn)) {
				products.remove(i);
			}
		}
	}

	@Override
	public ArrayList<Product> getList() {
		return products;
	}

	@Override
	public Product searchByIspn(String ispn) {
		for(int i =0; i<products.size(); i++) {
			if(products.get(i).getIspn().equals(ispn)) {
				return products.get(i);
			}
		}
		return null;
	}

	@Override
	public ArrayList<Product> searchByName(String name) {
		ArrayList<Product> result = new ArrayList<Product>();
		for(int i =0; i<products.size(); i++) {
			if(products.get(i).getName().contains(name)) {
				result.add(products.get(i));
			}
		}
		return result;
	}

	@Override
	public ArrayList<Product> getTVs() {
		ArrayList<Product> result = new ArrayList<Product>();
		for(int i =0; i<products.size(); i++) {
			if(products.get(i).getClass() == TV.class) {
				result.add(products.get(i));
			}
		}
		return result;
	}

	@Override
	public ArrayList<Product> getRefrigerators() {
		ArrayList<Product> result = new ArrayList<Product>();
		for(int i = 0; i<products.size(); i++) {
			if(products.get(i).getClass() == Refrigerator.class) {
				result.add(products.get(i));
			}
		}
		return result;
	}

	@Override
	public int getTotalPrice() {
		int total = 0;
		for(int i = 0; i<products.size(); i++) {
			total+=products.get(i).getPrice() * products.get(i).getAmount();
		}
		return total;
	}

	@Override
	public ArrayList<Product> searchByInch(int inch) {
		ArrayList<Product> temp = getTVs();
		ArrayList<Product> result = new ArrayList<Product>();
		for(int i = 0; i < temp.size(); i++) {
			if(((TV) temp.get(i)).getInch() >= inch) {
				result.add(temp.get(i));
			}
		}
		return result;
	}

	@Override
	public ArrayList<Product> searchByVolume(int volume) {
		ArrayList<Product> temp = getRefrigerators();
		ArrayList<Product> result = new ArrayList<Product>();
		for(int i = 0; i < temp.size(); i++) {
			if(((Refrigerator) temp.get(i)).getVolume() >= volume) {
				result.add(temp.get(i));
			}
		}
		return result;
	}

	@Override
	public void setPrice(String ispn, int price) {
		Product target = searchByIspn(ispn);
		target.setPrice(price);
	}

}
