package com.demo.in.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book
{
	@Id
	private int bid;
	private String bname;
	private double bprice;
	public Book(int bid, String bname, double bprice) {
		this.bid = bid;
		this.bname = bname;
		this.bprice = bprice;
	}
	public Book() {

	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public double getBprice() {
		return bprice;
	}
	public void setBprice(double bprice) {
		this.bprice = bprice;
	}
	@Override
	public String toString() {
		return "Book [bid=" + bid + ", bname=" + bname + ", bprice=" + bprice + "]";
	}
	
	

}
