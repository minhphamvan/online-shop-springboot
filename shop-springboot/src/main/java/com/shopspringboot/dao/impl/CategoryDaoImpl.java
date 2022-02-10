package com.shopspringboot.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopspringboot.dao.CategoryDao;
import com.shopspringboot.entity.Category;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void addCategory(Category category) {
		entityManager.persist(category);
	}

	@Override
	public void updateCategory(Category category) {
		entityManager.merge(category);

	}

	@Override
	public void deleteCategory(Category category) {
		entityManager.remove(category);
	}

	@Override
	public Category getCategoryById(int id) {
		return entityManager.find(Category.class, id);
	}

	@Override
	public List<Category> getCategoryByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getAllCategory() {
		String jql = "SELECT category from Category category";
		return entityManager.createQuery(jql, Category.class).getResultList();
	}

	@Override
	public long countCategory() {
		String jql = "SELECT COUNT(*) from Category";
		
		return entityManager.createQuery(jql, Long.class).getSingleResult().longValue();
	}

}
