package com.ssafy.ws04.step4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool.ManagedBlocker;


public class BookTest {
	public static void main(String[] args) {
		//1. 데이터 파일이 존재하지 않는 경우
		System.out.println("데이터 파일이 존재하지 않는 경우");
		System.out.println("*********************불러온 도서 전체 목록*********************");

		// test를 위해 파일이 존재하면 삭제
		File file = new File("book_data.dat");
		if(file.exists()) {
			file.delete();
		}
		
		//객체 생성시 loadData() 호출
		BookManagerImpl managerImp = BookManagerImpl.getInstance();
		ArrayList<Book> result;
		
		result = managerImp.getList();
		for(int i =0; i< result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		
		//2. 데이터 파일이 존재 하는 경우
		//test를 위핸 객체 생성 및 saveData() 파일생성
		managerImp.add(new Book("21424", "Java Pro", "김하나", "jaen.kr",15000,"Java 기본 문법", 10));
		managerImp.add(new Book("21425", "Java Pro2", "김하나", "jaen.kr",25000, "Java 응용", 20));
		managerImp.add(new Book("35355", "분석설계", "소나무", "jaen.kr",30000, "SW 모델링", 30));
		managerImp.add(new Magazine("45678", "월간 알고리즘", "홍길동", "jaen.kr",10000, "1월 알고리즘", 40, 2021 , 1));
		managerImp.saveData();
		
		//리스트 삭제 후 reload
		managerImp.loadTest();
		
		result = managerImp.getList();
		
		System.out.println("\n데이터 파일이 존재하는 경우");
		System.out.println("*********************불러온 도서 전체 목록*********************");
		for(int i =0; i< result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
	}
}
