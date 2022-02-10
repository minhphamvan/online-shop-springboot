package com.shopspringboot.dao;

import java.util.List;

import com.shopspringboot.entity.Category;

public interface CategoryDao {

	public void addCategory(Category category);

	public void updateCategory(Category category);
	
	public void deleteCategory(Category category);
	
	public Category getCategoryById(int id);
	
	public List<Category> getCategoryByName(String name);
	
	public List<Category> getAllCategory();
	
	public long countCategory();
}
