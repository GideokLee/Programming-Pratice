package com.ssafy.wa07.step3;

import java.util.ArrayList;


public class BookTest {
	public static void main(String[] args) {
		ArrayList<Book> result;
		BookManagerImpl managerImp = BookManagerImpl.getInstance();
		
		//add()
		managerImp.add(new Book("21424", "Java Pro", "김하나", "jaen.kr",15000,"Java 기본 문법", 10));
		managerImp.add(new Book("21425", "Java Pro2", "김하나", "jaen.kr",25000, "Java 응용", 20));
		managerImp.add(new Book("35355", "분석설계", "소나무", "jaen.kr",30000, "SW 모델링", 30));
		managerImp.add(new Magazine("45678", "월간 알고리즘", "홍길동", "jaen.kr",10000, "1월 알고리즘", 40, 2021 , 1));
		managerImp.add(new Magazine("21428", "remove() Test", "test", "test",30000, "remove() Test", 10, 2021, 7));
		
		//remove()
		managerImp.remove("21428");
		
		//getList()
		System.out.println("*********************도서 전체 목록*********************");
		result = managerImp.getList();
		for(int i =0; i< result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		
		//getBooks()
		System.out.println("*********************일반 도서 목록*********************");
		result = managerImp.getBooks();
		for(int i =0; i< result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		
		//getMagazines()
		System.out.println("*********************잡지 목록*********************");
		result = managerImp.getMagazine();
		for(int i =0; i< result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		
		//searchByTitle("Java")
		System.out.println("*********************도서 제목 포함검색:Java*********************");
//		result= managerImp.searchByTitle("Java");
//		for(int i =0; i< result.size(); i++) {
//			System.out.println(result.get(i).toString());
//		}
//		
		//getTotalPrice()
		System.out.println("도서 가격 총합 : " + managerImp.getTotalPrice());
		
		//getPriceAvg()
		System.out.println("도서 가격 평균 : " + managerImp.getPriceAvg());
		
		//sell()
		try {
			
			System.out.println("*********************도서 판매 : 21424, 11개*********************");
			managerImp.sell("21424", 11);
		} catch (QuantityException e) {
			System.out.println(e.getMessage());
		} catch (ISBNNotFoundException e) {
			System.out.println(e.getIsbn() + " : " + e.getMessage());
		}
		
		try {
			//buy("21424", 10)
			System.out.println("*********************도서 구매 : 21424, 10개*********************");
			managerImp.buy("21424", 10);
			System.out.println(managerImp.searchByisbn("21424").toString());
			
			//sell("21424", 11)
			System.out.println("*********************도서 판매 : 21424, 11개*********************");
			managerImp.sell("21424", 11);
			System.out.println(managerImp.searchByisbn("21424").toString());
			
		} catch (QuantityException e) {
			System.out.println(e.getMessage());
		} catch (ISBNNotFoundException e) {
			System.out.println(e.getIsbn() + " : " + e.getMessage());
		}
		
		
	}
}
