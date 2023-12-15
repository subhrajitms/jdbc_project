package org.jsp.productapp.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.jsp.productapp.dto.Product;

import com.mysql.cj.protocol.Resultset;

public class ProductDao {
	 
	Connection con;
	PreparedStatement pst;
	ResultSet rst;
	{
		Properties p=new Properties();
		FileInputStream fin=null;
		
		try {
			fin=new FileInputStream("D:\\j2ee\\props.properties");
			p.load(fin);
			con=DriverManager.getConnection(p.getProperty("url"),p);
		} catch (IOException|SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(fin!=null)
			{
				try {
					fin.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public String saveProdcut(Product p)
	{
		String qry="insert into product values(?,?,?,?,?,?,?)";
		try {
			pst=con.prepareStatement(qry);
			pst.setInt(1, p.getId());
			pst.setString(2, p.getName());
			pst.setString(3, p.getBrand());
			pst.setString(4, p.getCategory());
			pst.setString(5, p.getDescription());
			pst.setDouble(6, p.getCost());
			pst.setString(7, p.getImage_url());
			pst.executeUpdate();
			return "Product Saved";
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Product Not Saved";
			
		}
	}
	public String updateProduct(Product p)
	{
		String qry="update product set name=?,brand=?,category=?,description=?,cost=?,image_url=? where id=?";
		try {
			pst=con.prepareStatement(qry);
			pst.setInt(7, p.getId());
			pst.setString(1, p.getName());
			pst.setString(2, p.getBrand());
			pst.setString(3, p.getCategory());
			pst.setString(4, p.getDescription());
			pst.setDouble(5, p.getCost());
			pst.setString(6, p.getImage_url());
			pst.executeUpdate();
			return "Product Updated";
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Product Not Updated..";
			
		}
	}
	public Product findProductById(int id)
	{
		String qry="select * from product where id=?";
		try {
			pst=con.prepareStatement(qry);
			pst.setInt(1, id);
			rst=pst.executeQuery();
			Product p=new Product();
			if(rst.next())
			{
				p.setId(rst.getInt("id"));
				p.setName(rst.getString("name"));
				p.setBrand(rst.getString("brand"));
				p.setCategory(rst.getString("category"));
				p.setDescription(rst.getString("description"));
				p.setCost(rst.getDouble("cost"));
				p.setImage_url(rst.getString("image_url"));
				return p;
				
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public boolean deleteProduct(int id)
	{
		String qry="delete from product where id=?";
		try {
			pst=con.prepareStatement(qry);
			pst.setInt(1, id);
			int r=pst.executeUpdate();
			if(r==1)
				return true;
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public List<Product> findByBrand(String brand)
	{
		String qry="select * from product where brand=?";
		List<Product> l1=new ArrayList<Product>();
		try {
			pst=con.prepareStatement(qry);
			pst.setString(1, brand);
			rst=pst.executeQuery();
			
			while(rst.next())
			{
				Product p=new Product();
				p.setId(rst.getInt("id"));
				p.setName(rst.getString("name"));
				p.setBrand(rst.getString("brand"));
				p.setCategory(rst.getString("category"));
				p.setDescription(rst.getString("description"));
				p.setCost(rst.getDouble("cost"));
				p.setImage_url(rst.getString("image_url"));
				l1.add(p);
			}
			return l1;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public List<Product> findByCategory(String category)
	{
		String qry="select * from product where category=?";
		List<Product> l1=new ArrayList<Product>();
		try {
			pst=con.prepareStatement(qry);
			pst.setString(1, category);
			rst=pst.executeQuery();
			while(rst.next())
			{
				Product p=new Product();
				p.setId(rst.getInt("id"));
				p.setName(rst.getString("name"));
				p.setBrand(rst.getString("brand"));
				p.setCategory(rst.getString("category"));
				p.setDescription(rst.getString("description"));
				p.setCost(rst.getDouble("cost"));
				p.setImage_url(rst.getString("image_url"));
				l1.add(p);
			}
			return l1;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Product> filterByCost(double minVal,double maxVal)
	{
		String qry="select * from product where cost>=? and cost<=?";
		List<Product> product=new ArrayList<Product>();
		try {
			pst=con.prepareStatement(qry);
			pst.setDouble(1, minVal);
			pst.setDouble(2, maxVal);
			rst=pst.executeQuery();
			while(rst.next())
			{
				Product p=new Product();
				p.setId(rst.getInt("id"));
				p.setName(rst.getString("name"));
				p.setBrand(rst.getString("brand"));
				p.setCategory(rst.getString("category"));
				p.setDescription(rst.getString("description"));
				p.setCost(rst.getDouble("cost"));
				p.setImage_url(rst.getString("image_url"));
				product.add(p);
			}
			return product;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	public String exit()
	{
		if(con!=null)
		{
			try {
				con.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		if(pst!=null)
		{
			try {
				pst.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		if(rst!=null)
		{
			try {
				rst.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return "Application Closed.....!!!!";
	}
	
}
