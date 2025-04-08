package com.demo.in.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.in.exceptions.NoDataFoundException;
import com.demo.in.model.Book;
import com.demo.in.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	// Endpoint for adding a new book
	@PostMapping(value = "/addbook", consumes = { "application/json" })
	public ResponseEntity<String> addBook(@RequestBody Book book) {
		String msg;
		Boolean isSaved = bookService.addBook(book);
		if (isSaved) {
			msg = "Book saved";
			return new ResponseEntity<>(msg, HttpStatus.CREATED);
		} else {
			msg = "Failed to save";
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
	}

	// Endpoint for retrieving a book by ID
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
		try {
			Book book = bookService.getBookById(id);
			return new ResponseEntity<>(book, HttpStatus.OK);
		} catch (NoDataFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// Endpoint for retrieving all books
	@GetMapping("/all")
	public ResponseEntity<List<Book>> getAllBooks() {
		try {
			List<Book> books = bookService.getAllBook();
			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (NoDataFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// Endpoint for deleting a book by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBookById(@PathVariable Integer id) {
		try {
			Book deletedBook = bookService.deleteBookById(id);
			return new ResponseEntity<>("Book deleted: " + deletedBook.getBname(), HttpStatus.OK);
		} catch (NoDataFoundException e) {
			return new ResponseEntity<>("Failed to delete book", HttpStatus.NOT_FOUND);
		}
	}

	// Endpoint for checking if a book exists by ID
	@GetMapping("/exists/{id}")
	public ResponseEntity<Boolean> isBookExistsById(@PathVariable Integer id) {
		boolean exists = bookService.isBookExistsById(id);
		return new ResponseEntity<>(exists, HttpStatus.OK);
	}

	// Endpoint for saving a book (extra, if needed)
	@PostMapping("/save")
	public ResponseEntity<Book> saveBook(@RequestBody Book book) {
		Book savedBook = bookService.saveBook(book);
		return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
	}
}
