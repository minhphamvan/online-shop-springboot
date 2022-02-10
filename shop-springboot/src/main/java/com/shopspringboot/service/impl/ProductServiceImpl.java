package com.shopspringboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopspringboot.dao.ProductDao;
import com.shopspringboot.entity.Category;
import com.shopspringboot.entity.Product;
import com.shopspringboot.modelDTO.CategoryDTO;
import com.shopspringboot.modelDTO.ProductDTO;
import com.shopspringboot.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public void addProduct(ProductDTO productDTO) {
		productDao.addProduct(convertToProduct(productDTO));
	}

	@Override
	public void updateProduct(ProductDTO productDTO) {
		productDao.updateProduct(convertToProduct(productDTO));
	}

	@Override
	public void deleteProduct(int id) {
		Product product = productDao.getProductById(id);

		productDao.deleteProduct(product);
	}

	@Override
	public ProductDTO getProductById(int id) {
		Product product = productDao.getProductById(id);

		return convertToProductDTO(product);
	}

	@Override
	public List<ProductDTO> getProductByName(String name) {
		List<Product> products = productDao.getProductByName(name);
		List<ProductDTO> productDTOs = new ArrayList<>();

		for (Product p : products) {
			productDTOs.add(convertToProductDTO(p));
		}

		return productDTOs;
	}
	
	@Override
	public List<ProductDTO> getProductByNamePagination(String name, long limit, long startFromRecord) {
		List<Product> products = productDao.getProductByNamePagination(name, limit, startFromRecord);
		List<ProductDTO> productDTOs = new ArrayList<>();

		for (Product p : products) {
			productDTOs.add(convertToProductDTO(p));
		}

		return productDTOs;
	}

	@Override
	public List<ProductDTO> getAllProduct() {
		List<Product> products = productDao.getAllProduct();
		List<ProductDTO> productDTOs = new ArrayList<>();

		for (Product p : products) {
			productDTOs.add(convertToProductDTO(p));
		}

		return productDTOs;
	}
	
	@Override
	public List<ProductDTO> getProductPagination(long limit, long startFromRecord) {
		List<Product> products = productDao.getProductPagination(limit, startFromRecord);
		List<ProductDTO> productDTOs = new ArrayList<>();

		for (Product p : products) {
			productDTOs.add(convertToProductDTO(p));
		}

		return productDTOs;
	}
	
	@Override
	public long countProduct() {
		return productDao.countProduct();
	}

	// Convert Product - ProductDTO
	private ProductDTO convertToProductDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();

		productDTO.setId(product.getId());
		productDTO.setImage(product.getImage());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setSale(product.getSale());
		productDTO.setQuantity(product.getQuantity());
		productDTO.setRating(product.getRating());
		productDTO.setDescription(product.getDescription());

		Category category = product.getCategory();
		CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getName());
		productDTO.setCategoryDTO(categoryDTO);

		return productDTO;
	}

	private Product convertToProduct(ProductDTO productDTO) {
		Product product = new Product();

		product.setId(productDTO.getId());
		product.setImage(productDTO.getImage());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setSale(productDTO.getSale());
		product.setQuantity(productDTO.getQuantity());
		product.setRating(productDTO.getRating());
		product.setDescription(productDTO.getDescription());

		CategoryDTO categoryDTO = productDTO.getCategoryDTO();
		Category category = new Category(categoryDTO.getId(), categoryDTO.getName());
		product.setCategory(category);

		return product;
	}

	

	
}
