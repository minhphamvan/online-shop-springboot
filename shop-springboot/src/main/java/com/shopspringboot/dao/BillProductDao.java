package com.shopspringboot.dao;

import java.util.List;

import com.shopspringboot.entity.Bill;
import com.shopspringboot.entity.BillProduct;

public interface BillProductDao {

	public void addBillProduct(BillProduct billProduct);

	public void updateBillProduct(BillProduct billProduct);
	
	public void deleteBillProduct(BillProduct billProduct);
	
	public BillProduct getBillProductById(int id);
	
	public List<BillProduct> getAllBillProduct();
	
	public List<BillProduct> getAllBillProductByIdBill(int idBill);
	
	public long countBillProduct();
}