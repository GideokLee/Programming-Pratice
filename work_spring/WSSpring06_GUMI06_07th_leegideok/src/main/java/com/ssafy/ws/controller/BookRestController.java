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

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.model.service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/bookapi")
@Api(value = "Book")
public class BookRestController {

		@Autowired
		private BookService bService;
		
		@ApiOperation(value = "모든 도서 정보를 반환한다.", response = List.class)
		@GetMapping("/list")
		public ResponseEntity<?> searchBoard(){
			List<Book> books = bService.search();
			
			if(books != null && !books.isEmpty()) {
				return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
			}
			
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		@ApiOperation(value = "{isbn}에 해당하는 도서 정보를 반환한다.", response = Book.class)
		@GetMapping("/book/{isbn}")
		public ResponseEntity<?> selectBoard(@PathVariable("isbn") String isbn) {
			Book book = bService.select(isbn);
			
			if (book != null) {
				return new ResponseEntity<Book>(book, HttpStatus.OK);
			}
			
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		
		@ApiOperation(value = "도서 정보를 저장한다.", response = Book.class)
		@PostMapping("/book")
		public ResponseEntity<?> insertBoard(@RequestBody Book book) {
			int result = bService.insert(book);
			
			if (result > 0) {
				return new ResponseEntity<Book>(book, HttpStatus.CREATED);
			}
			
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		@ApiOperation(value = "{isbn}에 해당하는 도서 정보를 수정한다.", response = Book.class)
		@PutMapping("/book/{isbn}")
		public ResponseEntity<?> updateBoard(@PathVariable("isbn") String isbn, @RequestBody Book book) {
			book.setIsbn(isbn);
			int result = bService.update(book);
			
			if (result > 0) {
				return new ResponseEntity<Book>(book, HttpStatus.OK);
			}
			
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		@ApiOperation(value = "{isbn}에 해당하는 도서 정보를 삭제한다.", response = Integer.class)
		@DeleteMapping("/book/{isbn}")
		public ResponseEntity<?> deleteBoard(@PathVariable("isbn") String isbn) {
			int result = bService.delete(isbn);
			
			if (result > 0) {
				return new ResponseEntity<Integer>(result, HttpStatus.OK);
			}
			
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		
		
}
