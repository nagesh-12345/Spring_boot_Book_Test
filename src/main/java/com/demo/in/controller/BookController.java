package com.demo.in.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.in.model.Book;
import com.demo.in.service.BookService;

@RestController
public class BookController 
{
	@Autowired
	private BookService bookService;

	@PostMapping(value = "/addbook", consumes = { "application/json" })
	public ResponseEntity<String> addBook(@RequestBody Book book) {
		String msg = null;
		Boolean isSaved = bookService.addBook(book);
		if (isSaved) {
			msg = "Book saved";
			return new ResponseEntity<>(msg, HttpStatus.CREATED);
		} else {
			msg = "Failed to save";
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
	}

}
