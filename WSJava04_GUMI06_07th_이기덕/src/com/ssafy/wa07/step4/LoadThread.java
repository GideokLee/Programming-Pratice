package com.ssafy.wa07.step4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LoadThread extends Thread{
	private ArrayList<Book> data;
	
	public LoadThread(ArrayList<Book> books) {
		this.data = data;
	}
	
	@Override
	public void run() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("book_data.dat");
			ois = new ObjectInputStream(fis);
			
			data = (ArrayList<Book>) ois.readObject();
		}
		catch (FileNotFoundException e) {
			System.out.println("등록된 도서가 없습니다.");
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
				if(ois != null) {
					ois.close();
				}
				if(fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
