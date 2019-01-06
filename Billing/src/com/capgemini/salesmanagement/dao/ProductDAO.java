package com.capgemini.salesmanagement.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.salesmanagement.bean.ProductBean;
import com.capgemini.salesmanagement.exception.ProductException;
import com.capgemini.salesmanagement.util.DBConnection;




public class ProductDAO implements IProductDAO{

	Logger logger=Logger.getRootLogger();
	public ProductDAO()
	{
		
		PropertyConfigurator.configure("resources//log4j.properties");
	
	}
	
	//------------------------ 1. Billing Application --------------------------
		/*******************************************************************************************************
		 - Function Name	:	 getProductDetails(int productCode)
		 - Input Parameters	:	ProductBean productBean
		 - Return Type		:	ProductBean
		 - Throws			:  	ProductException
		 - Author			:	Subham Agarwal
		 - Creation Date	:	19/12/2018
		 - Description		:	product details
		 
		 ********************************************************************************************************/
	
	@Override
	public ProductBean getProductDetails(int productCode) throws ProductException, ClassNotFoundException, IOException, SQLException {
		
		Connection connection = DBConnection.getConnection();
		PreparedStatement pst =null;
		
		ProductBean productBean = new ProductBean();
		ResultSet rs = null;
		
		try {
		
		pst = connection.prepareStatement(QweryMapper.VIEW_PRODUCT_USING_PID);
		pst.setInt(1,productCode);
		rs = pst.executeQuery();
		
		while(rs.next())
		{
		productBean.setProduct_code(rs.getInt(1));
		productBean.setProduct_name(rs.getString(2));
		productBean.setProduct_category(rs.getString(3));
		productBean.setProduct_description(rs.getString(4));
		productBean.setProduct_price(rs.getDouble(5));
		}
		
		return productBean;
		
		}
		catch (SQLException e) {
			logger.error("Problem in Viewing Product from database!!");
			throw new ProductException("Problem in Viewing Product from database!!");
		}
		finally
		{
				try 
				{
					pst.close();
					rs.close();
					connection.close();
				}
				catch (SQLException sqlException) 
				{
					logger.error("Error in closing db connection");
					throw new ProductException("Error in closing db connection");

				}
		}
	}

	@Override
	public boolean insertSalesDetails(ProductBean product) throws ProductException, ClassNotFoundException, IOException, SQLException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement pst =null;
		PreparedStatement pst1=null;
		ResultSet rs = null;
		ResultSet rs1=null;
		int price=0;
		int total_price=0;
		try {
		pst1=connection.prepareStatement(QweryMapper.VIEW_PRODUCT_USING_PID);
		pst1.setInt(1,product.getProduct_code());
		rs1=pst1.executeQuery();
		while(rs1.next()) {
			price=rs1.getInt(5);
		}
		total_price=price*(product.getQuantity());
		
		pst = connection.prepareStatement(QweryMapper.INSERT_SALES);
		pst.setInt(1,product.getProduct_code());
		pst.setInt(2,product.getQuantity());
		pst.setInt(3,total_price);
		rs = pst.executeQuery();
		
		return true;
		}
		catch (SQLException e) {
			logger.error("Problem in Inserting Sales Details in database!!");
			throw new ProductException("Problem in Inserting Sales Details in database!!");
		}
		finally
		{
				try 
				{
					pst1.close();
					pst.close();
					rs1.close();
					rs.close();
					connection.close();
				}
				catch (SQLException sqlException) 
				{
					logger.error("Error in closing db connection");
					throw new ProductException("Error in closing db connection");

				}
		}
	}

}
