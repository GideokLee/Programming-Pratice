package com.ssafy.ws.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.dto.User;
import com.ssafy.ws.model.service.BookServiceImpl;


@Controller
public class BookController {

	BookServiceImpl bService = new BookServiceImpl();
	List<Book> books = new ArrayList<Book>();
	/**
	 * <pre> / 또는 /index 요청이 get 방식으로 들어왔을 때 index 로 연결한다.</pre>
	 * 
	 * @return
	 */
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, Model model, HttpSession session) {
        System.out.println(user);
        
        if (user.getId().equals("ssafy") && user.getPass().equals("1234")) {
            user.setName("김싸피");
            session.setAttribute("loginUser", user);
            
            return "redirect:/";
        }
        else {
            model.addAttribute("msg", "아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
            return "index";
        }
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
    	session.removeAttribute("loginUser");
    	
    	return "redirect:/";
    }
    
	@RequestMapping(value = "/error/404", method = RequestMethod.GET)
	public String showError404() {
		return "error/404";
	}
	/**
	 * <pre>/list를 get 방식으로 요청할 때 도서 정보를 보여준다.</pre>
	 * 아직 MVC의 model 영역을 완성하지 않았기 때문에 여러 개의 Board를 직접 생성해서 List 형태로 전달한다.
	 * 정보를 Model 객체에 저장 후 forward로 list를 호출한다.
	 * @return
	 */
	@GetMapping("/list")
	public String showList(Model model) {

		books.add(new Book("111-222-3333", "홍길동", "책제목 1", 10000, "좋은 책 1", "abc1.png")); 
		books.add(new Book("111-222-4444", "임꺽정", "책제목 2", 20000, "좋은 책 2", "abc2.png")); 
		books.add(new Book("111-333-4444", "장길산", "책제목 3", 30000, "좋은 책 3", "abc3.png"));

		model.addAttribute("books", books);
		return "list";
	}
	/**
	 * get 방식의 regist 요청이 오면 regist 페이지를 forward로 연결한다.
	 * @return
	 */
	@GetMapping("/regist")
	public String showRegistForm() {
		return "regist";
	}
	

	/**
	 * post 방식의 regist 요청이 오면 요청으로 전달된 board 객체를 그대로 regist_result에 연결한다.
	 * @param board
	 * @return
	 */
	@PostMapping("/regist")
	public String doRegist(@ModelAttribute Book book) {
		System.out.println("POST /regist 내용: " + book);
		books.add(book);
		return "regist_result";
	}
	
	/**
	 * <pre>
	 * /error/commonerr 요청이 get 방식으로 들어왔을 때 error/commonerr로 연결한다.
	 * </pre>
	 * 
	 * @return
	 */
	@RequestMapping("/error/commonerr")
	public String showError500() {
		return "error/commonerr";
	}
}
