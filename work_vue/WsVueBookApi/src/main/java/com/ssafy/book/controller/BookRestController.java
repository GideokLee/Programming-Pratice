package com.ssafy.book.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.book.model.BookDto;
import com.ssafy.book.model.service.BookService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/vuews/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/book")
public class BookRestController {

	private static final Logger logger = LoggerFactory.getLogger(BookRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	BookService bookService;

	@ApiOperation(value = "��� å�� ������ ��ȯ�Ѵ�.", response = List.class)
	@GetMapping
	public ResponseEntity<List<BookDto>> listBook() {
		logger.debug("listBook - ȣ��");
		return new ResponseEntity<>(bookService.list(), HttpStatus.OK);
	}

	@ApiOperation(value = "isbn�� �ش��ϴ� å�� ������ ��ȯ�Ѵ�.", response = BookDto.class)
	@GetMapping("/{isbn}")
	public ResponseEntity<BookDto> searchBook(@PathVariable String isbn) {
		logger.debug("searchBook - ȣ��");
		return new ResponseEntity<>(bookService.search(isbn), HttpStatus.OK);
	}

	@ApiOperation(value = "���ο� å ������ �Է��Ѵ�. �׸��� DB�Է� �������ο� ���� 'success' �Ǵ� 'fail' ���ڿ��� ��ȯ�Ѵ�.", response = String.class)
	@PostMapping
	public ResponseEntity<String> createBook(@RequestBody BookDto bookDto) {
		logger.debug("createBook - ȣ��");
		if(bookService.create(bookDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "isbn�� �ش��ϴ� å�� ������ �����Ѵ�. �׸��� DB���� �������ο� ���� 'success' �Ǵ� 'fail' ���ڿ��� ��ȯ�Ѵ�.", response = String.class)
	@PutMapping("{isbn}")
	public ResponseEntity<String> modifyBook(@RequestBody BookDto bookDto) {
		logger.debug("modifyBook - ȣ��");
		logger.debug("" + bookDto);
		if(bookService.modify(bookDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "isbn�� �ش��ϴ� å�� ������ �����Ѵ�. �׸��� DB���� �������ο� ���� 'success' �Ǵ� 'fail' ���ڿ��� ��ȯ�Ѵ�.", response = String.class)
	@DeleteMapping("{isbn}")
	public ResponseEntity<String> deleteBook(@PathVariable("isbn") String isbn) {
		logger.debug("deleteBook - ȣ��");
		if(bookService.delete(isbn)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
