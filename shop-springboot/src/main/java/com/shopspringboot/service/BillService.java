package com.shopspringboot.service;

import java.util.List;

import com.shopspringboot.modelDTO.BillDTO;

public interface BillService {

	public void addBill(BillDTO billDTO);

	public void updateBill(BillDTO billDTO);
	
	public void deleteBill(int id);
	
	public BillDTO getBillById(int id);
	
	public List<BillDTO> getAllBill();
	
	public List<BillDTO> getAllBillByIdUser(int idUser);
	
	public long countBill();
}
