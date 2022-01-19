package com.ssafy.wa07.step3;

import java.util.ArrayList;

import javax.swing.plaf.basic.BasicTreeUI.TreeHomeAction;

public class BookManagerImpl implements IBookManager{
	
	private ArrayList<Book> books;
	private static BookManagerImpl instance;
	
	private BookManagerImpl() {
		books = new ArrayList<Book>();
	}
	public static BookManagerImpl getInstance() {
		if(instance == null) {
			instance = new BookManagerImpl();
		}
		return instance;
	}
	
	@Override
	public void add(Book book) {
		books.add(book);
	}

	@Override
	public void remove(String isbn) {
		for(int i =0; i < books.size(); i++) {
			if(books.get(i).getIsbn().equals(isbn)){
				books.remove(i);
			}
		}
	}

	@Override
	public ArrayList<Book> getList() {
		return books;
	}

	@Override
	public Book searchByisbn(String isbn) throws ISBNNotFoundException{
		
		for(int i =0; i< books.size(); i++) {
			if(books.get(i).getIsbn().equals(isbn)) {
				return books.get(i);
			}
		}
		
		throw new ISBNNotFoundException(isbn);
	}

	@Override
	public ArrayList<Book> searchByTitle(String title) {
		ArrayList<Book> temp = new ArrayList<Book>();
		for(int i =0; i< books.size(); i++) {
			if(books.get(i).getTitle().contains(title)) {
				temp.add(books.get(i));
			}
		}
		return temp;
	}

	@Override
	public ArrayList<Book> getMagazine() {
		ArrayList<Book> temp = new ArrayList<Book>();
		for(int i =0; i< books.size(); i++) {
			if(books.get(i).getClass() == Magazine.class) {
				temp.add(books.get(i));
			}
		}
		return temp;
	}

	@Override
	public ArrayList<Book> getBooks() {
		ArrayList<Book> temp = new ArrayList<Book>();
		for(int i =0; i< books.size(); i++) {
			if(books.get(i).getClass() == Book.class) {
				temp.add(books.get(i));
			}
		}
		return temp;
	}

	@Override
	public int getTotalPrice() {
		int total = 0;
		for(int i =0; i<books.size(); i++) {
			total+=books.get(i).getPrice();
		}
		return total;
	}

	@Override
	public double getPriceAvg() {
		return (double) getTotalPrice() / books.size();
	}

	@Override
	public void buy(String isbn, int quantity) throws ISBNNotFoundException {
		for(int i =0; i < books.size(); i++) {
			if(books.get(i).getIsbn().equals(isbn)) {
				int temp = books.get(i).getQuantity();
				books.get(i).setQuantity(temp + quantity);
				return;
			}
		}
		throw new ISBNNotFoundException(isbn);
	}

	@Override
	public void sell(String isbn, int quantity) throws QuantityException, ISBNNotFoundException{
		for(int i =0; i < books.size(); i++) {
			if(books.get(i).getIsbn().equals(isbn)) {
				int temp = books.get(i).getQuantity();
				if(quantity > temp) {
					throw new QuantityException();
				}
				books.get(i).setQuantity(temp - quantity);
				return;
			}
		}
		throw new ISBNNotFoundException(isbn);
	}
	

}
