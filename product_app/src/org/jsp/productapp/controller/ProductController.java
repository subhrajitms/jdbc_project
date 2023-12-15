package org.jsp.productapp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.productapp.dao.ProductDao;
import org.jsp.productapp.dto.Product;

public class ProductController {
	 public static void main(String[] args) {
		 
		System.out.println("1.Save Product");
		System.out.println("2.Update Product...");
		System.out.println("3.Find Product by Id...");
		System.out.println("4.Delete Product by Id....");
		System.out.println("5.Find Products by brand...");
		System.out.println("6.Find Product by category....");
		System.out.println("7.Filter Product by cost....");
		System.out.println("8.Exit......");
		
		Scanner sc=new Scanner(System.in);
		ProductDao p=new ProductDao();
		while(true)
		{
			System.out.println("Enter the Option...");
			int option=sc.nextInt();
			switch(option)
			{
			case 1:{
				System.out.println("Enter id,name,brand,category,description,cost,image_url to save record...");
				int id=sc.nextInt();
				String name=sc.next();
				String brand=sc.next();
				String cate=sc.next();
				String desc=sc.next();
				double cost=sc.nextDouble();
				String url=sc.next();
				
				Product p1=new Product(id, name, brand, cate, desc, cost, url);
				String save=p.saveProdcut(p1);
				System.out.println(save);
				
			}
			break;
			case 2:{
				System.out.println("Enter id to Update record...");
				int id=sc.nextInt();
				String name=sc.next();
				String brand=sc.next();
				String cate=sc.next();
				String desc=sc.next();
				double cost=sc.nextDouble();
				String url=sc.next();
				
				Product p1=new Product(id, name, brand, cate, desc, cost, url);
				String update=p.updateProduct(p1);
				System.out.println(update);
			}
			break;
			case 3:
			{
				System.out.println("Enter the Product Id.......");
				int id=sc.nextInt();
				Product p2=p.findProductById(id);
				if(p2!=null)
				{
					System.out.println("User Found..");
					System.out.println("Id of Product ..."+p2.getId());
					System.out.println("Name of Product:"+p2.getName());
					System.out.println("Brand:"+p2.getBrand());
					System.out.println("Category:"+p2.getCategory());
					System.out.println("Description:"+p2.getDescription());
					System.out.println("Cost is:"+p2.getCost());
					System.out.println("Image url is:"+p2.getImage_url());
				}
				else
				{
					System.out.println("User Not Found.....");
				}
				
			}
			break;
			case 4:
			{
				System.out.println("Enter the Product id to Delete the record.....");
				int id=sc.nextInt();
				boolean delete=p.deleteProduct(id);
				if(delete)
				{
					System.out.println("Product Deleted...");
				}
				else
				{
					System.out.println("Product Not Deleted....");
				}
			}
			break;
			case 5:
			{
				System.out.println("Enter Product brand......");
				String brand=sc.next();
				List<Product> l1=p.findByBrand(brand);
				if(l1.size()>0)
				{
					for(Product p1:l1)
					{
						System.out.println("Id of Product ..."+p1.getId());
						System.out.println("Name of Product:"+p1.getName());
						System.out.println("Brand:"+p1.getBrand());
						System.out.println("Category:"+p1.getCategory());
						System.out.println("Description:"+p1.getDescription());
						System.out.println("Cost is:"+p1.getCost());
						System.out.println("Image url is:"+p1.getImage_url());
					}
				}
				else
				{
					System.out.println("Product is not Found.....");
				}
			}
			break;
			case 6:
			{
				System.out.println("Enter Product category......");
				String category =sc.next();
				List<Product> l1=p.findByCategory(category);
				if(l1.size()>0)
				{
					for(Product p1:l1)
					{
						System.out.println("Id of Product ..."+p1.getId());
						System.out.println("Name of Product:"+p1.getName());
						System.out.println("Brand:"+p1.getBrand());
						System.out.println("Category:"+p1.getCategory());
						System.out.println("Description:"+p1.getDescription());
						System.out.println("Cost is:"+p1.getCost());
						System.out.println("Image url is:"+p1.getImage_url());
					}
				}
				else
				{
					System.out.println("Product is not Found.....");
				}
			}
			break;
			case 7:
			{
				System.out.println("Enter min value..");
				double min=sc.nextDouble();
				System.out.println("Enter max value");
				double max=sc.nextDouble();
				List<Product> product=p.filterByCost(min, max);
				for(Product p1:product)
				{
					System.out.println("Id of Product ..."+p1.getId());
					System.out.println("Name of Product:"+p1.getName());
					System.out.println("Brand:"+p1.getBrand());
					System.out.println("Category:"+p1.getCategory());
					System.out.println("Description:"+p1.getDescription());
					System.out.println("Cost is:"+p1.getCost());
					System.out.println("Image url is:"+p1.getImage_url());
				}
			}
				break;
			case 8:
				String exit=p.exit();
				System.out.println(exit);
				System.exit(0);
				
			}
		}
	}
}
