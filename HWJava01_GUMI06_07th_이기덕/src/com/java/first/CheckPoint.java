package com.java.first;

import java.util.Scanner;

public class CheckPoint {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		int height = Integer.parseInt(sc.next());
		int weight = Integer.parseInt(sc.next());
		
		
		int compute = weight + 100 - height;
		
		System.out.println("비만수치는 " + compute + "입니다.");
		if(compute > 0)
			System.out.println("당신은 비만이군요");
	}
	
	
		
}
