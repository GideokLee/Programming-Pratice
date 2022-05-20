package com.ssafy.day01.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloSsafyServlet extends HttpServlet {

	private static final long serialVersionUID = -4401332290258235074L;
	
	// 클라이언트에서 GET 요청이 들어온 경우에 호출
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 메서드 호출 ...");
		
		// 응답 메시지의 Content-Type 설정
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Hello Servlet !!<br>");
		out.println("<h2>안녕 서블릿!</h2>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
	}
}
