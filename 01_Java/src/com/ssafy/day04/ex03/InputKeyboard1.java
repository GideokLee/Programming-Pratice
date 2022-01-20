package com.ssafy.day04.ex03;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

// 키보드로부터 데이터 입력 1 (Java 1.4)
public class InputKeyboard1 {
	
	public static void main(String[] args) {
		InputStream is = System.in;
		PrintStream ps = System.out;
		
		try {
			int ch = is.read();
			ps.println((char) ch);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
