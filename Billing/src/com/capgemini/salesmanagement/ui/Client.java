package com.capgemini.salesmanagement.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.capgemini.salesmanagement.bean.ProductBean;
import com.capgemini.salesmanagement.exception.ProductException;
import com.capgemini.salesmanagement.service.IProductService;
import com.capgemini.salesmanagement.service.ProductService;


public class Client {

	static Scanner sc = new Scanner(System.in);
	static IProductService iproductService=null;
	static ProductService productService=null;
	static Logger logger = Logger.getRootLogger();
	
	public static void main(String[] args) {
		
		int condition=0;
		ProductBean productBean = null;
		int productCode;
		int quantity;
		while(true) {
			System.out.println();
			System.out.println("-------------------MENU--------------------");
			System.out.println("1. View Product Information based on ProductCode");
			System.out.println("2. Insert Sales Details.");
			System.out.println("3. Exit.");
			System.out.println("please Select from above options");
		
			
			try 
			{
				condition = sc.nextInt();
				switch (condition) {
				case 1:
					
					System.out.println("Enter the product code: ");
					productCode= sc.nextInt();
					try
					{
						productBean = new ProductBean();
						iproductService = new ProductService();
						productService = new ProductService();
					
						if(productService.validateProductCode(productCode))
						{
							logger.info("get product detail");
							productBean=iproductService.getProductDetails(productCode);
							if(productBean.getProduct_code()!=0)
								System.out.println(productBean);
							else
								logger.info("Product Doesn't exist");
								//System.out.println("Product doesn't Exists");
								throw new ProductException("Product Doesn't exist");
						}
						else
						{
							logger.info("Please Enter VAlid Data");
							throw new ProductException("Please Enter VAlid Data");
						}
					}
					catch(Exception exc)
					{
						logger.error("exception occured");
						System.err.println("Error:"+exc.getMessage());
					}
					finally {
						productBean=null;
						iproductService=null;
						productCode=0;
						productService=null;
					}
					break;
				case 2:
					System.out.println("Enter the product code: ");
					productCode= sc.nextInt();
					System.out.println("Enter the Quantity: ");
					quantity=sc.nextInt();
					double line_total=0;
					try
					{
						productBean = new ProductBean();
						iproductService = new ProductService();
						productService = new ProductService();
					
						if(productService.validateProductCode(productCode)&&productService.validateQuantity(quantity))
						{
							productBean.setProduct_code(productCode);
							productBean.setQuantity(quantity);
							if(iproductService.insertSalesDetails(productBean)) {
								productBean=iproductService.getProductDetails(productCode);
								if(productBean.getProduct_code()!=0) 
								{
									System.out.println(productBean);
									System.out.println("Quantity:        "+quantity);
									line_total=(productBean.getProduct_price())*quantity;
									System.out.println("Line Total(Rs):    "+line_total);
								}
								else
									throw new ProductException("Product doesn't Exists");
							}
							else {
								throw new ProductException("Sales Details Can't be inserted!!!!!");
							}
						}
						else
						{
							System.out.println("Please Enter VAlid Data");
						}
					}
					catch(Exception exc)
					{
						System.err.println("Error:"+exc.getMessage());
					}
					finally {
						productBean=null;
						iproductService=null;
						productCode=0;
						productService=null;
						quantity=0;
						line_total=0;
					}
					
					break;
				case 3:
					System.out.println("successfully exit");
					System.exit(0);
				default:
					System.out.println("Please Select only from above two options");
					break;
				}
			}
			catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Input Type Mismatch Occured!!!!!!!");
			}
			}
	}
}
