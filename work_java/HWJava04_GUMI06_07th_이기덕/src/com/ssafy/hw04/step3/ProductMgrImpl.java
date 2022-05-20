package com.ssafy.hw04.step3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ProductMgrImpl implements IProductMgr{
	
	private ArrayList<Product> products;
	private static ProductMgrImpl instance;
	
	private ProductMgrImpl() {
		products = new ArrayList<Product>();
		loadData();
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

	@Override
	public void saveData() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("product.dat");
			oos = new ObjectOutputStream(fos);

			oos.writeObject(products);
			oos.writeObject(null);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				oos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	private void loadData() {
		LoadThread loadTh = new LoadThread();
		loadTh.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void reLoadTest() {
		products = new ArrayList<Product>();
		loadData();
	}

}
