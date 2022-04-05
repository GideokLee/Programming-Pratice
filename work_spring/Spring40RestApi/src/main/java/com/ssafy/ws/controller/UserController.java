package com.ssafy.ws.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssafy.ws.dto.User;
import com.ssafy.ws.model.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService uService;
	
	/**
	 * <pre>
	 * /login 요청이 post 방식으로 왔을 때 login 처리한다.
	 * </pre>
	 * 
	 * 사용자의 요청에서 계정 정보를 추출해 로그인 처리한다.
	 * 일단 사용자 id가 ssafy, 비밀번호가 1234면 로그인에 성공으로 간주한다.
	 * 로그인 성공 시 session에 정보를 담고 redirect로 index로 이동한다.
	 * 그렇지 않을 경우는 로그인 실패 메시지를 model에 담고 forward로 index로 이동한다.
	 * 
	 * @param user
	 *            전달된 파라미터는 ModelAttribute를 통해서 객체로 받을 수 있다.
	 * @param session
	 *            사용자 정보를 세션에 저장하기 위해 사용한다.
	 * @param model
	 *            Request scope에 정보를 저장하기 위해서 사용된다.
	 * @return
	 */
	@PostMapping("/login")
	public String doLogin(User user, HttpSession session, Model model) {
		
		User loginUser = uService.select(user);
		
		if (loginUser != null && loginUser.getUserId() != null) {
			session.setAttribute("loginUser", loginUser);
			return "redirect:/";
		}
		else {
			model.addAttribute("msg", "로그인 실패");
			return "index";
		}
	}
	
	/**
	 * <pre>/logout을 get 방식으로 요청했을 때 session을 만료 시키고 로그아웃 처리한다.</pre>
	 * 다음 경로는 redirect 형태로 /index로 이동한다.
	 * @param session
	 * @return
	 */
	
	@GetMapping("/logout")
	public String doLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
}
