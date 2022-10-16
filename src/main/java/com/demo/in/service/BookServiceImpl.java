package com.demo.in.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.in.exceptions.NoDataFoundException;
import com.demo.in.model.Book;
import com.demo.in.repository.BookRepository;

@Service
public class BookServiceImpl  implements BookService{

	@Autowired
	private BookRepository bookRepo;
	
	
	@Override
	public Book saveBook(Book bk) {
		Book b=bookRepo.save(bk);
		return b;
	}

	@Override
	public Book getBookById(Integer bid) {
		Optional<Book> findById = bookRepo.findById(bid);
		if(findById.isPresent())
		{
			Book book = findById.get();
			
			return book;
		}
		throw new NoDataFoundException("NO Data found for given Book/id");
	}

	@Override
	public List<Book> getAllBook() {
		 List<Book> list = bookRepo.findAll();
		if(!list.isEmpty())
		{
			return list;
		}
		 throw new NoDataFoundException("no data found,List is empty ");
	}
	
	

	@Override
	public Boolean addBook(Book book) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Book deleteBookById(Integer bid) {
		Optional<Book> findById = bookRepo.findById(bid);
		Book book = findById.get();
		
		if(book==null) {
			throw new NoDataFoundException("no data found,List is empty ");
		} else {
			bookRepo.deleteById(bid);
			return book;
		}
	}

	@Override
	public boolean isBookExistsById(Integer bid) {
		
		boolean existsById = bookRepo.existsById(bid);
		/*
		 * Book book = bookRepo.findById(bid).get(); if(book.getBid()==bid) return true;
		 * else return false;
		 */
		return existsById;
	}

	
	
}
