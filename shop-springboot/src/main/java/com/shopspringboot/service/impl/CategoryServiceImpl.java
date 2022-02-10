package com.shopspringboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopspringboot.dao.CategoryDao;
import com.shopspringboot.entity.Category;
import com.shopspringboot.modelDTO.CategoryDTO;
import com.shopspringboot.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public void addCategory(CategoryDTO categoryDTO) {
		categoryDao.addCategory(convertToCategory(categoryDTO));
	}

	@Override
	public void updateCategory(CategoryDTO categoryDTO) {
		categoryDao.updateCategory(convertToCategory(categoryDTO));
	}

	@Override
	public void deleteCategory(int id) {
		Category category = categoryDao.getCategoryById(id);

		categoryDao.deleteCategory(category);
	}

	@Override
	public CategoryDTO getCategoryById(int id) {
		Category category = categoryDao.getCategoryById(id);
		
		return convertToCategoryDTO(category);
	}

	@Override
	public List<CategoryDTO> getCategoryByName(String name) {
		List<Category> categories = categoryDao.getCategoryByName(name);
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		
		for(Category c : categories) {
			categoryDTOs.add(convertToCategoryDTO(c));
		}
		
		return categoryDTOs;
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> categories = categoryDao.getAllCategory();
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		
		for(Category c : categories) {
			categoryDTOs.add(convertToCategoryDTO(c));
		}
		
		return categoryDTOs;
	}
	
	@Override
	public long countCategory() {
		return categoryDao.countCategory();
	}
	
	// Convert Category - CategoryDTO
	private Category convertToCategory(CategoryDTO categoryDTO) {
		Category category  = new Category();
		
		category.setId(categoryDTO.getId());
		category.setName(categoryDTO.getName());
		
		return category;
	}
	
	private CategoryDTO convertToCategoryDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
		
		return categoryDTO;
	}

	
}
