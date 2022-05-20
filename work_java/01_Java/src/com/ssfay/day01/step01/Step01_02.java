package com.ssfay.day01.step01;

import java.util.Scanner;

public class Step01_02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] input = new int[10];
		
		
		for(int i = 0; i<10; i++) {
			input[i] = Integer.parseInt(sc.next());
		}
		
		
		for(int i = 0; i<10; i++) {
			System.out.print(input[i]+ " ");
		}
		System.out.println();
	}
}
