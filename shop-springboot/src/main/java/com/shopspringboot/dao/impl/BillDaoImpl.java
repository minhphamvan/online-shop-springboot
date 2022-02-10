package com.shopspringboot.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.shopspringboot.dao.BillDao;
import com.shopspringboot.entity.Bill;

@Controller
@Transactional
public class BillDaoImpl implements BillDao {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void addBill(Bill bill) {
		entityManager.persist(bill);
	}

	@Override
	public void updateBill(Bill bill) {
		entityManager.merge(bill);
	}

	@Override
	public void deleteBill(Bill bill) {
		entityManager.remove(bill);
	}

	@Override
	public Bill getBillById(int id) {
		return entityManager.find(Bill.class, id);
	}

	@Override
	public List<Bill> getAllBill() {
		String jql = "SELECT b FROM Bill b";
		return entityManager.createQuery(jql, Bill.class).getResultList();
	}

	@Override
	public List<Bill> getAllBillByIdUser(int idUser) {
		String jql = "SELECT b FROM Bill b WHERE b.user.id = ?1"; // làm việc với đối tượng nên cần lấy ra lần lượt thuộc tính
		return entityManager.createQuery(jql, Bill.class).setParameter(1, idUser).getResultList();
	}

	@Override
	public long countBill() {
		String jql = "SELECT COUNT(*) from Bill";
		
		return entityManager.createQuery(jql, Long.class).getSingleResult().longValue();
	}
}
