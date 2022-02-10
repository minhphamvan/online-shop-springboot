package com.shopspringboot.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopspringboot.dao.BillProductDao;
import com.shopspringboot.entity.Bill;
import com.shopspringboot.entity.BillProduct;

@Repository
@Transactional
public class BillProductDaoImpl implements BillProductDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void addBillProduct(BillProduct billProduct) {
		entityManager.persist(billProduct);
	}

	@Override
	public void updateBillProduct(BillProduct billProduct) {
		entityManager.merge(billProduct);
	}

	@Override
	public void deleteBillProduct(BillProduct billProduct) {
		entityManager.remove(billProduct);
	}

	@Override
	public BillProduct getBillProductById(int id) {
		return entityManager.find(BillProduct.class, id);
	}

	@Override
	public List<BillProduct> getAllBillProduct() {
		String jql = "SELECT bp FROM BillProduct bp";
		return entityManager.createQuery(jql, BillProduct.class).getResultList();
	}

	@Override
	public List<BillProduct> getAllBillProductByIdBill(int idBill) {
		String jql = "SELECT bp FROM BillProduct bp WHERE bp.bill.id = ?1"; // làm việc với đối tượng nên cần lấy ra lần lượt thuộc tính
		return entityManager.createQuery(jql, BillProduct.class).setParameter(1, idBill).getResultList();
	}

	@Override
	public long countBillProduct() {
		String jql = "SELECT COUNT(*) from BillProduct";
		
		return entityManager.createQuery(jql, Long.class).getSingleResult().longValue();
	}

}
