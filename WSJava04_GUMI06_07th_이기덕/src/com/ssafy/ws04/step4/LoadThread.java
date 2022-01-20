package com.ssafy.ws04.step4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LoadThread extends Thread{
	
	@Override
	public void run() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("book_data.dat");
			ois = new ObjectInputStream(fis);
			
			BookManagerImpl manager = BookManagerImpl.getInstance();
			ArrayList<Book> data = (ArrayList<Book>) ois.readObject();
			
			for(int i =0; i<data.size(); i++) {
				manager.add(data.get(i));
			}
			
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
