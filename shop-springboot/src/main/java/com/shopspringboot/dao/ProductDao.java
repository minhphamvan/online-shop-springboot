package com.shopspringboot.dao;

import java.util.List;

import com.shopspringboot.entity.Product;

public interface ProductDao {
	public void addProduct(Product product);

	public void updateProduct(Product product);
	
	public void deleteProduct(Product product);
	
	public Product getProductById(int id);
	
	public List<Product> getProductByName(String name);
	
	public List<Product> getProductByNamePagination(String name, long limit, long startFromRecord);
	
	public List<Product> getAllProduct();
	
	public List<Product> getProductPagination(long limit, long startFromRecord);
	
	public long countProduct();
}
