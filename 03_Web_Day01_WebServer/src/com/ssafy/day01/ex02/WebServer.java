package com.ssafy.day01.ex02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class WebServer {
	
	private static final int PORT = 10001;
	
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		while (true) {
			try {
				// 클라이언트 요청을 받을 서버 소켓 생성
				serverSocket = new ServerSocket(PORT);
				System.out.println("서버 실행 중 ... 포트번호는 " + PORT);
				
				// 클라이언트 요청 대기
				socket = serverSocket.accept();  // 클라이언트 접속할 때까지 대기
				System.out.println("클라이언트 접속 ...");
				
				// 데이터 주고받기 위해 File I/O 객체 생성
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				// 클라이언트로부터 요청 받기
				String line = br.readLine();
				if (line != null) {
					
					// 요청받은 내용 분석
					StringTokenizer tokenizer = new StringTokenizer(line);
					String method = tokenizer.nextToken();
					String uri = tokenizer.nextToken();
					String ver = tokenizer.nextToken();
					
					// 내용 출력해보기
					System.out.println("mehtod: " + method);
					System.out.println("uri: " + uri);
					System.out.println("ver: " + ver);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					br.close();
					bw.close();
					
					socket.close();
					serverSocket.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
