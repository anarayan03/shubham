package com.capgemini.salesmanagement.dao;

public class QweryMapper {

	public final static String VIEW_PRODUCT_USING_PID="select * from product where product_code=?";  
	public final static String INSERT_SALES="INSERT INTO SALES VALUES(SALES_ID_SEQ.NEXTVAL,?,?,SYSDATE,?)";
}
