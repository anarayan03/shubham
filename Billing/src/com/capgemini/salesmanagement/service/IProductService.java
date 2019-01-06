package com.capgemini.salesmanagement.service;

import java.io.IOException;
import java.sql.SQLException;

import com.capgemini.salesmanagement.bean.ProductBean;
import com.capgemini.salesmanagement.exception.ProductException;

public interface IProductService {

	ProductBean getProductDetails(int productCode) throws ClassNotFoundException, ProductException, IOException, SQLException;
	boolean insertSalesDetails(ProductBean product) throws ClassNotFoundException, ProductException, IOException, SQLException;
	
}
