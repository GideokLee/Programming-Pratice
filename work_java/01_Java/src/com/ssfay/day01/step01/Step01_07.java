package com.ssfay.day01.step01;

import java.util.Scanner;

public class Step01_07 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] input = new int[10];
		int min = 10001;
		int max = 0;
		
		for(int i = 0; i<10; i++) {
			input[i] = Integer.parseInt(sc.next());
			if(input[i]<100) {
				if(input[i] > max) max = input[i];
			}else {
				if(input[i] < min) min = input[i];
			}
			
		}
		
		System.out.println(max + " " + min);
	}
}
