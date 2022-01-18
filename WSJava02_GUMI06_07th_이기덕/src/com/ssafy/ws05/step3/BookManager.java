package com.ssafy.ws05.step3;

public class BookManager {
	private final int MAX_SIZE = 100;
	private Book[] books;
	private int size;
	
	public BookManager() {
		// TODO Auto-generated constructor stub
		books = new Book[MAX_SIZE];
		size = 0;
	}
	public void add(Book book) {
		if(size < MAX_SIZE)
			books[size++] = book;
	}
	public void remove(String isbn) {
		boolean togle = false;
		int count = 0;
		Book[] temp = new Book[MAX_SIZE];
		for(int i = 0; i< size; i++) {
			if(!books[i].getIsbn().equals(isbn)) 
				temp[count++] = books[i];
			else
				togle = true;
		}
		if(togle) {
			books = temp;
			size--;
		}
	}
	public Book[] getList() {
		return books;
	}
	public Book searchByIsbn(String isBn) {
		for(int i =0; i < size; i++) {
			if(books[i].getIsbn().equals(isBn)) {
				return books[i];
			}
		}
		return null;
	}
	public Book[] searchByTitle(String title) {
		Book[] result = new Book[MAX_SIZE];
		int count = 0;
		for(int i =0; i < size; i++) {
			if(books[i].getTitle().contains(title)) {
				result[count++] = books[i];
			}
		}
		return result;
	}
	public Book[] getBooks() {
		Book[] result = new Book[MAX_SIZE];
		int count = 0;
		for(int i =0; i < size; i++) {
			if(books[i].getClass() == Book.class) {
				result[count++] = books[i];
			}
		}
		return result;
	}
	public Book[] getMagazines() {
		Book[] result = new Book[MAX_SIZE];
		int count = 0;
		for(int i =0; i < size; i++) {
			if(books[i].getClass() == Magazine.class) {
				result[count++] = books[i];
			}
		}
		return result;
	}
	public int getTotalPrice(){
		int total =0;
		for(int i =0; i< size; i++) {
			total += books[i].getPrice();
		}
		
		return total;
	}
	public double getPriceAvg() {
		double avg = 0.0;
		for(int i =0; i<size; i++) {
			avg += books[i].getPrice();
		}
		
		return avg / size;
	}
}
