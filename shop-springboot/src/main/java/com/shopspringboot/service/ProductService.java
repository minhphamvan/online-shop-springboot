package com.shopspringboot.service;

import java.util.List;

import com.shopspringboot.entity.Product;
import com.shopspringboot.modelDTO.ProductDTO;

public interface ProductService {
	public void addProduct(ProductDTO productDTO);

	public void updateProduct(ProductDTO productDTO);
	
	public void deleteProduct(int id);
	
	public ProductDTO getProductById(int id);
	
	public List<ProductDTO> getProductByName(String name);
	
	public List<ProductDTO> getProductByNamePagination(String name, long limit, long startFromRecor);
	
	public List<ProductDTO> getAllProduct();
	
	public List<ProductDTO> getProductPagination(long limit, long startFromRecord);
	
	public long countProduct();
}
