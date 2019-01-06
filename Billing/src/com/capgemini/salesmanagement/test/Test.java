package com.capgemini.salesmanagement.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Ignore;

import com.capgemini.salesmanagement.bean.ProductBean;
import com.capgemini.salesmanagement.dao.IProductDAO;
import com.capgemini.salesmanagement.dao.ProductDAO;
import com.capgemini.salesmanagement.exception.ProductException;

public class Test
{

	static ProductBean productBean;
	static IProductDAO dao;
	
	@Before
	public void init()
	{
		System.out.println("Before Class");
		dao = new ProductDAO();
		productBean = new ProductBean();
	}
	
	@org.junit.Test
	public void insertSalesDetailsTest() throws ProductException, NumberFormatException, ClassNotFoundException, SQLException, IOException
	{
		
		productBean.setProduct_code(1001);
		productBean.setQuantity(6);
		assertTrue(dao.insertSalesDetails(productBean));
		
	}
	
	
//	@org.junit.Test
//	public void test() 
//	{
//		fail("Not yet implemented");
//	}

}
