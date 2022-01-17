package com.ssfay.day01.step01;

import java.util.Scanner;

public class Step01_08 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] input = new int[10];
		int sum_e = 0;
		double avg_o = 0.0;
		int count = 0;
		for(int i = 0; i<10; i++) {
			input[i] = Integer.parseInt(sc.next());
			if( (i + 1 ) % 2 == 0) {
				sum_e+=input[i];
			}else {
				avg_o+=input[i];
				count++;
			}
		}
		avg_o = avg_o/count;
		System.out.println("sum: " + sum_e);
		System.out.println("avg: " +Math.round(avg_o*10)/10.0);
		
	}
}
