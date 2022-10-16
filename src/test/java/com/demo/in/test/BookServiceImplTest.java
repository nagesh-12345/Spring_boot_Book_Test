package com.demo.in.test;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.Ignore;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.in.exceptions.NoDataFoundException;
import com.demo.in.model.Book;
import com.demo.in.repository.BookRepository;
import com.demo.in.service.BookServiceImpl;


import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertThrows;

@SpringBootTest
public class BookServiceImplTest 
{
	//private static Book b=null;
	
//	@BeforeClass
//	public static void init()
//	{
//		b=new Book(101,"abc",50.36);
//	}
//	
//	@AfterAll
//	public static void destroy()
//	{
//		b=null;
//	}
	@InjectMocks
	BookServiceImpl bookService;

	@Mock
	BookRepository bookRepository;
	
	@Test
	@Ignore
	public void saveBook()
	{
		Book bk=new Book(101,"C++",199.3);
		Book bk1=new Book(101,null,199.3);
		when(bookRepository.save(bk1)).thenReturn(bk1);	
		
		assertEquals(bk1,bookService.saveBook(bk1));
	}
	
	@Test
	@Ignore
	public void testGetAllBook_01()
	{
		List<Book> list=new ArrayList<>();
		Book bk1=new Book(101,"C++",199.36);
		Book bk2=new Book(102,"C#",299.50);
		Book bk3=new Book(103,"Java",459.36);
		
		list.add(bk1);
		list.add(bk2);
		list.add(bk3);

		System.out.println(list);
		
		when(bookRepository.findAll()).thenReturn(list);
		
		List<Book> bkList = bookService.getAllBook();
		assertEquals(list, bkList);
	}
	
	@Test
	public void testIsBookExistsById_01()
	{
		Book bk=new Book(109,"C++",199.36);
		Book save = bookRepository.save(bk);
		
		boolean expectedResult = bookRepository.existsById(bk.getBid());
		boolean actualResult = bookService.isBookExistsById(109);
		
		//assertThat(actualResult).isTrue();
		
		assertEquals(expectedResult, actualResult);
		//assertNotNull(expectedResult);
	}
	@Test
	public void testGetAllBookNoDataFoundException_02()
	{
		List<Book> list=new ArrayList<>();
		Book bk1=new Book(101,"C++",199.36);
		Book bk2=new Book(102,"C#",299.50);
		Book bk3=new Book(103,"Java",459.36);
		
//		list.add(bk1);
//		list.add(bk2);
//		list.add(bk3);

		System.out.println(list);
		
		List<Book> bkList = bookRepository.findAll();
		assertThrows(NoDataFoundException.class,()->bookService.getAllBook());		
		
	}
	
	
	
	@org.junit.Test(expected = NoDataFoundException.class)
	public void testGetBookById_2()
	{
		Book bk1=new Book(110,"C++",199.36);
		bookRepository.save(bk1);
		Book book = bookRepository.getById(110);
		
		//Book book = bookService.getBookById(110);
		System.out.println("book:"+book);
		
	}
	
//	@Test
//	public void testGetBookById_01()
//	{
//		Book bk1=new Book(99,"C++",199.36);
//		Book save = bookRepository.save(bk1);
//		//Book book = bookRepository.findById(99).get();
//		when(bookRepository.findById(bk1.getBid()).get()).thenReturn(save);
//		
//		assertEquals(save,bookService.getBookById(99));
//		
//	}
	
	
	@Test
	public void testDeleteBook()
	{
		Book bk1=new Book(110,"C++",199.36);
		bookRepository.save(bk1);
		bookRepository.deleteById(bk1.getBid());
		Optional<Book> bk = bookRepository.findById(bk1.getBid());
		if(bk.isPresent()) {
			Book book = bk.get();
			assertEquals(null, book);
			System.out.println(book);
		}
		//System.out.println(book);
		
		
	}
}
