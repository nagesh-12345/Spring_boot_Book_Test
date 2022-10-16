package com.demo.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.in.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

	

	
}
