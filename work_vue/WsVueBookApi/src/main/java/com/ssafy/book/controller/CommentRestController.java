package com.ssafy.book.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.book.model.CommentDto;
import com.ssafy.book.model.service.CommentService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/vuews/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/comment")
public class CommentRestController {

	private static final Logger logger = LoggerFactory.getLogger(CommentRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	CommentService commentService;

	@ApiOperation(value = "isbn�� �ش��ϴ� ������ ����� ��ȯ�Ѵ�.", response = List.class)
	@GetMapping("{isbn}")
	public ResponseEntity<List<CommentDto>> listComment(@PathVariable("isbn") String isbn) {
		logger.debug("listComment - ȣ��");
		return new ResponseEntity<>(commentService.list(isbn), HttpStatus.OK);
	}

	@ApiOperation(value = "���ο� �������� �Է��Ѵ�. �׸��� DB�Է� �������ο� ���� 'success' �Ǵ� 'fail' ���ڿ��� ��ȯ�Ѵ�.", response = String.class)
	@PostMapping
	public ResponseEntity<String> createComment(@RequestBody CommentDto commentDto) {
		logger.debug("createComment - ȣ��");
		if(commentService.create(commentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "�۹�ȣ�� comment_no�� �ش��ϴ� �������� �����Ѵ�. �׸��� DB���� �������ο� ���� 'success' �Ǵ� 'fail' ���ڿ��� ��ȯ�Ѵ�.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyComment(@RequestBody CommentDto commentDto) {
		logger.debug("modifyComment - ȣ��");
		logger.debug("" + commentDto);
		System.out.println("��� ��������!!!"  + commentDto);
		if(commentService.modify(commentDto)) {
			System.out.println("��� ���� ����!!!");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "�۹�ȣ�� comment_no�� �ش��ϴ� �������� �����Ѵ�. �׸��� DB���� �������ο� ���� 'success' �Ǵ� 'fail' ���ڿ��� ��ȯ�Ѵ�.", response = String.class)
	@DeleteMapping("{commentNO}")
	public ResponseEntity<String> deleteBook(@PathVariable("commentNO") int commentNO) {
		logger.debug("deleteBook - ȣ��");
		if(commentService.delete(commentNO)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
