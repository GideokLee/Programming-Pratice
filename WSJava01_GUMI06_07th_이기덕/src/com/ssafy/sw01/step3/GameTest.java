package com.ssafy.sw01.step3;

import java.util.Scanner;

public class GameTest {
	private Scanner sc;
	private int score_u;
	private int score_c;
	private int option;
	
	public GameTest() {
		sc = new Scanner(System.in);
		score_c =0;
		score_u =0;
		option = 0;
	}
	
	public void stepUp() {
		System.out.println("가가위바위보 게임을 시작합니다. 아래 보기 중 하나를 고르세요.");
		System.out.println("1. 5 판 3 승");
		System.out.println("2. 3 판 2 승");
		System.out.println("3. 1 판 1 승");
		System.out.print("번호를 입력하세요. ");
		option = Integer.parseInt(sc.next());
	
	}
	public void playGame() {
		switch (option) {
		case 1:
			for(int i = 0; i<5; i++) {
				turn();
				if(score_c ==3 || score_u ==3) break;
			}
			break;
		case 2:
			for(int i = 0; i<3; i++) {
				turn();
				if(score_c ==2 || score_u ==2) break;
			}
			break;
		case 3:
			turn();
			break;
		default:
			break;
		}
		
		if(score_u > score_c)
			System.out.println("### 유저 승!!!");
		else if(score_u < score_c)
			System.out.println("### 컴퓨터 승!!!");
		else
			System.out.println("### 비겼습니다!!!");
		
	}
	public void turn() {
		System.out.print("가위바위보 중 하나 입력:");
		String user = sc.next();
		int com = (int) (Math.random() * 3) +1;
		if(com == 1) {
			if(user.equals("가위")) {
				System.out.println("비겼습니다!!");
			}else if(user.equals("바위")) {
				score_u++;
				System.out.println("이겼습니다!!");
			}else {
				score_c++;
				System.out.println("졌습니다!!");
			}
		}else if(com == 2) {
			if(user.equals("가위")) {
				score_c++;
				System.out.println("졌습니다!!");
			}else if(user.equals("바위")) {
				System.out.println("비겼습니다!!");
			}else {
				score_u++;
				System.out.println("이겼습니다!!");
			}
		}else {
			if(user.equals("가위")) {
				score_u++;
				System.out.println("이겼습니다!!");
			}else if(user.equals("바위")) {
				score_c++;
				System.out.println("졌습니다!!");
			}else {
				System.out.println("비겼습니다!!");
			}
		}
	}
	
	public static void main(String[] args) {
		GameTest start = new GameTest();
		start.stepUp();
		start.playGame();
	}
	
}
