package com.ssfay.day01.step01;

import java.util.Scanner;

public class Step01_04 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] input = new int[100];
		int size = 0;
		
		for(int i = 0; i<100; i++) {
			input[size++] = Integer.parseInt(sc.next());
			if(input[i] == 0) break;
		}
		
		for(int i = size - 2; i>=0; i--) {
			System.out.print(input[i]+ " ");
		}
		System.out.println();
	}
}
