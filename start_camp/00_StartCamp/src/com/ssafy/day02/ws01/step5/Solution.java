/*
 *  데일리 실습_Java_01_5
 *  Java 01. 2차원배열 응용
 *  
 */
package com.ssafy.day02.ws01.step5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Solution {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/ws01_input.txt"));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(in.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(in.readLine());
			String[][] input = new String[N][];
			int temp;
			int result = 0;
			
			for(int i = 0; i< N; i++)
				input[i] = in.readLine().split(" ");
			
			for(int i = 0; i<N; i++) {
				for(int j =0; j<N; j++) {
					boolean stop = false;
					if(input[i][j].equals("B")) {
						for(int x = i-1; x < i +2; x++) 
							for(int y = j-1; y < j+2; y++) 
								if( ( x >= 0 && x < N) && (y >= 0 && y < N))
										if(input[x][y].equals("G")) {
											stop=true;
											break;
										}
							
						if(stop) temp = 2;
						else {
							temp = -1;
							for(int x = 0; x < N; x++)
								if(input[x][j].equals("B")) temp++;
							for(int y = 0; y < N; y++)
								if(input[i][y].equals("B")) temp++;
						}
						if(result < temp) result = temp;
					}
				}
			}
			System.out.println("#" + test_case + " " + result);
		}
	}
}
