package com.capgemini.salesmanagement.service;

import java.io.IOException;
import java.sql.SQLException;

import com.capgemini.salesmanagement.bean.ProductBean;
import com.capgemini.salesmanagement.dao.IProductDAO;
import com.capgemini.salesmanagement.dao.ProductDAO;
import com.capgemini.salesmanagement.exception.ProductException;


public class ProductService implements IProductService{
	
	IProductDAO iProductDAO =null;
	@Override
	public ProductBean getProductDetails(int productCode) throws ClassNotFoundException, ProductException, IOException, SQLException {
		iProductDAO = new ProductDAO();
		ProductBean productBean;
		productBean = iProductDAO.getProductDetails(productCode);	
		return productBean;
	}

	@Override
	public boolean insertSalesDetails(ProductBean product) throws ClassNotFoundException, ProductException, IOException, SQLException {
		iProductDAO = new ProductDAO();
		boolean inserted;
		inserted=iProductDAO.insertSalesDetails(product);
		return inserted;
	}

	public boolean validateProductCode(int productCode) {
		return productCode>1000;
	}

	public boolean validateQuantity(int quantity) {
		return quantity>0;
	}

}
