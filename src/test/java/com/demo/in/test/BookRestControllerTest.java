package com.demo.in.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demo.in.controller.BookController;
import com.demo.in.model.Book;

import com.demo.in.service.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(value = BookController.class)
public class BookRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookServiceImpl bookService;

	@Test
	public void test_1_addBook() throws Exception {
		Book b = new Book(101, "Spring", 450.00);
		when(bookService.addBook(ArgumentMatchers.any())).thenReturn(true);

		ObjectMapper mapper = new ObjectMapper();
		String bookJson = mapper.writeValueAsString(b);

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/addbook")
																		 .contentType("application/json")
																		 .content(bookJson);

		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201,status);
		
	}
	
	@Test
	public void test_2_addBook() throws Exception {

		Book b = new Book(101, "Spring", 450.00);
		
		when(bookService.addBook(b)).thenReturn(false);

		ObjectMapper mapper = new ObjectMapper();
		String bookJson = mapper.writeValueAsString(b);

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/addbook")
																		 .contentType("application/json")
																		 .content(bookJson);

		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(400, status);
	}
}
