package com.shopspringboot.dao;

import java.util.List;

import com.shopspringboot.entity.Bill;
import com.shopspringboot.entity.BillProduct;

public interface BillDao {
	
	public void addBill(Bill bill);

	public void updateBill(Bill bill);
	
	public void deleteBill(Bill bill);
	
	public Bill getBillById(int id);
	
	public List<Bill> getAllBill();
	
	public List<Bill> getAllBillByIdUser(int idUser);
	
	public long countBill();
}
