package com.ssafy.wa07.step4;

import java.util.ArrayList;

public interface IBookManager {
	public void add(Book book);
	public void remove(String isbn);
	public ArrayList<Book> getList();
	public Book searchByisbn(String isbn) throws ISBNNotFoundException;
	public ArrayList<Book> searchByTitle(String title);
	public ArrayList<Book> getMagazine();
	public ArrayList<Book> getBooks();
	public int getTotalPrice();
	public double getPriceAvg();
	public void buy(String isbn, int quantity) throws ISBNNotFoundException;
	public void sell(String isbn, int quantity) throws QuantityException, ISBNNotFoundException;
	public void saveData();
	}
