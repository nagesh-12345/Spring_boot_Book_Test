package com.demo.in.service;

import java.util.List;

import com.demo.in.model.Book;

public interface BookService 
{

	public Book saveBook(Book bk);
	
	public Book getBookById(Integer bid);
	
	public List<Book> getAllBook();
	
	public Book deleteBookById(Integer bid);
	
	public boolean isBookExistsById(Integer bid);

	public Boolean addBook(Book book);
}
