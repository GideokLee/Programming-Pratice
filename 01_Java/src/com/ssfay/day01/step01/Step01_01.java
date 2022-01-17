package com.ssfay.day01.step01;

import java.util.Scanner;

public class Step01_01 {
		
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char[] input = new char[10];
		
		
		for(int i = 0; i<10; i++) {
			input[i] = sc.next().charAt(0);
		}
		
		
		for(int i = 0; i<10; i++) {
			System.out.print(input[i]);
		}
		System.out.println();
	}
		
		
}
