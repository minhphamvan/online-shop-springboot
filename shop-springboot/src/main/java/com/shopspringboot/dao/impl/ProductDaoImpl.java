package com.shopspringboot.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopspringboot.dao.ProductDao;
import com.shopspringboot.entity.Product;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void addProduct(Product product) {
		entityManager.persist(product);
	}

	@Override
	public void updateProduct(Product product) {
		entityManager.merge(product);
	}

	@Override
	public void deleteProduct(Product product) {
		entityManager.remove(product);
	}

	@Override
	public Product getProductById(int id) {
		return entityManager.find(Product.class, id);
	}

	@Override
	public List<Product> getProductByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductByNamePagination(String name, long limit, long startFromRecord) {
//		String jql = "SELECT p FROM Product p LIMIT :limit OFFSET :startFromRecord";
//		
//		return entityManager.createQuery(jql, Product.class)
//				.setParameter("limit", limit)
//				.setParameter("startFromRecord", startFromRecord)
//				.getResultList();

		String jql = "SELECT p FROM Product p WHERE p.name like:pname";

		return entityManager.createQuery(jql, Product.class).setParameter("pname", "%" + name + "%")
				.setFirstResult((int) startFromRecord).setMaxResults((int) limit).getResultList();
	}

	@Override
	public List<Product> getAllProduct() {
		String jql = "SELECT p FROM Product p";
		return entityManager.createQuery(jql, Product.class).getResultList();
	}

	@Override
	public List<Product> getProductPagination(long limit, long startFromRecord) {
//		String jql = "SELECT p FROM Product p LIMIT :limit OFFSET :startFromRecord";
//		
//		return entityManager.createQuery(jql, Product.class)
//				.setParameter("limit", limit)
//				.setParameter("startFromRecord", startFromRecord)
//				.getResultList();

		String jql = "SELECT p FROM Product p";

		return entityManager.createQuery(jql, Product.class).setFirstResult((int) startFromRecord)
				.setMaxResults((int) limit).getResultList();
	}

	@Override
	public long countProduct() {
		String jql = "SELECT COUNT(*) from Product";

		return entityManager.createQuery(jql, Long.class).getSingleResult().longValue();
	}

}
