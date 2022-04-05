package com.ssafy.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.dto.Board;
import com.ssafy.ws.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
@Api(value = "Board")
public class BoardRestController {
	
	@Autowired
	private BoardService bService;
	
	@ApiOperation(value = "모든 게시물의 정보를 반환한다.", response = List.class)
	@GetMapping("/board")
	public ResponseEntity<?> searchBoard() {
		List<Board> boards = bService.search();
		
		if (boards != null && !boards.isEmpty()) {
			return new ResponseEntity<List<Board>>(boards, HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/board/{no}")
	public ResponseEntity<?> selectBoard(@PathVariable("no") String no) {
		Board board = bService.select(no);
		
		if (board != null) {
			return new ResponseEntity<Board>(board, HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/board")
	public ResponseEntity<?> insertBoard(@RequestBody Board board) {
		int result = bService.insert(board);
		
		if (result > 0) {
			return new ResponseEntity<Board>(board, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/board/{no}")
	public ResponseEntity<?> updateBoard(@PathVariable("no") Integer no, @RequestBody Board board) {
		board.setNo(no);
		int result = bService.update(board);
		
		if (result > 0) {
			return new ResponseEntity<Board>(board, HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/board/{no}")
	public ResponseEntity<?> deleteBoard(@PathVariable("no") String no) {
		int result = bService.delete(no);
		
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
