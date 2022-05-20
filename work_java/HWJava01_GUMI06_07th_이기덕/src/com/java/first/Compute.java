package com.java.first;

import java.util.Scanner;

public class Compute {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input1 = Integer.parseInt(sc.next());
		int input2 = Integer.parseInt(sc.next());
		
		int multi = input1 * input2;
		int div = input1 / input2;
		
		System.out.println("곱=" + multi);
		System.out.println("몫=" + div );
	}
}
