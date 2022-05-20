package com.ssafy.day04.q02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ListObjectIOTest {
	public static void main(String[] args) {
		
		// 1. 책 겍체를 3개 생성
		// 2. 1번에서 만든 객체를 ArrayList에 담기
		ArrayList<Book> books = new ArrayList<Book>();
		books.add(new Book("21424", "Java Pro", "김하나", "jaen.kr",15000,"Java 기본 문법", 10));
		books.add(new Book("21425", "Java Pro2", "김하나", "jaen.kr",25000, "Java 응용", 20));
		books.add(new Book("35355", "분석설계", "소나무", "jaen.kr",30000, "SW 모델링", 30));
		
		//3. 2번에 만든 ArrayList 객체를 파일에 저장 (ObjextIOTestDrive 예제 참고)
		//book_data.dat
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("book_data.dat");
			oos = new ObjectOutputStream(fos);

			oos.writeObject(books);
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
		
		System.out.println("객체 정보를 파일에 저장 완료");
		
		//4. 저장된 파일에서 ArrayList 객체 정보를 읽어오기
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("book_data.dat");
			ois = new ObjectInputStream(fis);
			
			ArrayList<Book> p = (ArrayList<Book>) ois.readObject();
			
			//5. syso 출력
			for(int i =0; i<p.size(); i++) {
				System.out.println(p.get(i).toString());
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//6. 파일 입출력 관련 변수를 close() 호출하여 정리
		finally {
			try {
				ois.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
