package com.shopspringboot.service;

import java.util.List;

import com.shopspringboot.modelDTO.CategoryDTO;

public interface CategoryService {
	public void addCategory(CategoryDTO categoryDTO);

	public void updateCategory(CategoryDTO categoryDTO);
	
	public void deleteCategory(int id);
	
	public CategoryDTO getCategoryById(int id);
	
	public List<CategoryDTO> getCategoryByName(String name);
	
	public List<CategoryDTO> getAllCategory();
	
	public long countCategory();
}
