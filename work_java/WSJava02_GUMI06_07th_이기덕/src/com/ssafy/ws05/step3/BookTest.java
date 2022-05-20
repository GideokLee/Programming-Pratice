package com.ssafy.ws05.step3;

public class BookTest {
	public static void main(String[] args) {
		
		BookManager manager = new BookManager();
		Book[] result;
		final int MAX_SIZE = 100;
		
		//add()
		manager.add(new Book("21424", "Java Pro", "김하나", "jaen.kr",15000, "Java 기본 문법"));
		manager.add(new Book("21425", "Java Pro2", "김하나", "jaen.kr",25000, "Java 응용"));
		manager.add(new Book("35355", "분석설계", "소나무", "jaen.kr",30000, "SW 모델링"));
		manager.add(new Magazine("45678", "월간 알고리즘", "홍길동", "jaen.kr",10000, "1월 알고리즘",2021 , 1));
		manager.add(new Magazine("21428", "remove() Test", "test", "test",30000, "remove() Test" , 2021, 7));
		
		//remove()
		manager.remove("21428");
		
		//getList()
		System.out.println("*********************도서 전체 목록*********************");
		result= manager.getList();
		for(int i=0; i<MAX_SIZE; i++) {
			if(result[i]==null) break;
			System.out.println(result[i].toString());
		}
		
		//getBooks()
		System.out.println("*********************일반 도서 목록*********************");
		result= manager.getBooks();
		for(int i=0; i<MAX_SIZE; i++) {
			if(result[i]==null) break;
			System.out.println(result[i].toString());
		}
		
		//getMagazines()
		System.out.println("*********************잡지 목록*********************");
		result= manager.getMagazines();
		for(int i=0; i<MAX_SIZE; i++) {
			if(result[i]==null) break;
			System.out.println(result[i].toString());
		}
		
		//searchByTitle("Java")
		System.out.println("*********************도서 제목 포함검색:Java*********************");
		result= manager.searchByTitle("Java");
		for(int i=0; i<MAX_SIZE; i++) {
			if(result[i]==null) break;
			System.out.println(result[i].toString());
		}
		
		//getTotalPrice()
		System.out.println("도서 가격 총합 : " + manager.getTotalPrice());
		
		//getPriceAvg()
		System.out.println("도서 가격 평균 : " + manager.getPriceAvg());
	}
}
