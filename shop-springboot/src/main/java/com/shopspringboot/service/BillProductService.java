package com.shopspringboot.service;

import java.util.List;

import com.shopspringboot.modelDTO.BillProductDTO;

public interface BillProductService {

	public void addBillProduct(BillProductDTO billProductDTO);

	public void updateBillProduct(BillProductDTO billProductDTO);

	public void deleteBillProduct(int id);

	public BillProductDTO getBillProductById(int id);

	public List<BillProductDTO> getAllBillProduct();
	
	public List<BillProductDTO> getAllBillProductByIdBill(int idBill);
	
	public long countBillProduct();
}
