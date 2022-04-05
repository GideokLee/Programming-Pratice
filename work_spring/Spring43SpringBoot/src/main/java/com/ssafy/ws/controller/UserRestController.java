package com.ssafy.ws.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.dto.User;
import com.ssafy.ws.model.service.UserService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class UserRestController {
	
	@Autowired
	private UserService uService;
	
	@PostMapping("/user/login")
	public User login(@RequestBody User user, HttpServletResponse response) throws UnsupportedEncodingException {
		User selected = uService.select(user);
        if (selected != null) {
            Cookie cookie = new Cookie("loginId", URLEncoder.encode(selected.getUserId(), "utf-8"));
            cookie.setPath("/");
            cookie.setMaxAge(10 * 60);  // 초 단위, 10분
            response.addCookie(cookie);
        }
        return selected;
	}

}
