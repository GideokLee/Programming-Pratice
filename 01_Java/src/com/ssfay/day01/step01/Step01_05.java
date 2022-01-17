package com.ssfay.day01.step01;

import java.util.Scanner;

public class Step01_05 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		double[] input = {85.6 ,79.5, 83.1, 80.0, 78.2, 75.0};
		double avg = 0.0;
		
		for(int i = 0; i<2; i++) {
			avg += input[Integer.parseInt(sc.next())-1];
		}
		
		System.out.println(Math.round(avg*10)/10.0);
	}
	
}
