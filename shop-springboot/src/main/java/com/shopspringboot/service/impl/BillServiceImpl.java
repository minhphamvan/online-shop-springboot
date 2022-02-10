package com.shopspringboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopspringboot.dao.BillDao;
import com.shopspringboot.entity.Bill;
import com.shopspringboot.entity.User;
import com.shopspringboot.modelDTO.BillDTO;
import com.shopspringboot.modelDTO.UserDTO;
import com.shopspringboot.service.BillService;

@Service
@Transactional
public class BillServiceImpl implements BillService {

	@Autowired
	BillDao billDao;

	@Override
	public void addBill(BillDTO billDTO) {
		billDao.addBill(convertToBill(billDTO));
	}

	@Override
	public void updateBill(BillDTO billDTO) {
		billDao.updateBill(convertToBill(billDTO));
	}

	@Override
	public void deleteBill(int id) {
		Bill bill = billDao.getBillById(id);

		billDao.deleteBill(bill);
	}

	@Override
	public BillDTO getBillById(int id) {
		Bill bill = billDao.getBillById(id);

		return convertToBillDTO(bill);
	}

	@Override
	public List<BillDTO> getAllBill() {
		List<Bill> bills = billDao.getAllBill();
		List<BillDTO> billDTOs = new ArrayList<>();

		for (Bill b : bills) {
			billDTOs.add(convertToBillDTO(b));
		}

		return billDTOs;
	}

	@Override
	public List<BillDTO> getAllBillByIdUser(int idUser) {
		List<Bill> bills = billDao.getAllBillByIdUser(idUser);
		List<BillDTO> billDTOs = new ArrayList<>();

		for (Bill b : bills) {
			billDTOs.add(convertToBillDTO(b));
		}

		return billDTOs;
	}
	
	@Override
	public long countBill() {
		return billDao.countBill();
	}

	// Convert Bill - BillDTO
	private BillDTO convertToBillDTO(Bill bill) {
		BillDTO billDTO = new BillDTO();

		billDTO.setId(bill.getId());
		billDTO.setTime(bill.getTime());
		billDTO.setNote(bill.getNote());
		billDTO.setBuyDate(bill.getBuyDate());
		billDTO.setTotalPrice(bill.getTotalPrice());
		billDTO.setPayMethod(bill.getPayMethod());
		billDTO.setStatus(bill.getStatus());
		billDTO.setUserDTO(convertToUserDTO(bill.getUser()));

		return billDTO;
	}

	private Bill convertToBill(BillDTO billDTO) {
		Bill bill = new Bill();

		bill.setId(billDTO.getId());
		bill.setTime(billDTO.getTime());
		bill.setNote(billDTO.getNote());
		bill.setBuyDate(billDTO.getBuyDate());
		bill.setTotalPrice(billDTO.getTotalPrice());
		bill.setPayMethod(billDTO.getPayMethod());
		bill.setStatus(billDTO.getStatus());
		bill.setUser(convertToUser(billDTO.getUserDTO()));

		return bill;
	}

	// Convert User - UserDTO
	private UserDTO convertToUserDTO(User user) {
		UserDTO userDTO = new UserDTO();

		userDTO.setId(user.getId());
		userDTO.setImage(user.getImage());
		userDTO.setName(user.getName());
		userDTO.setAddress(user.getAddress());
		userDTO.setEmail(user.getEmail());
		userDTO.setPhone(user.getPhone());

		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setRole(user.getRole());
		userDTO.setActive(user.getActive());

		return userDTO;
	}

	private User convertToUser(UserDTO userDTO) {
		User user = new User();

		user.setId(userDTO.getId());
		user.setImage(userDTO.getImage());
		user.setName(userDTO.getName());
		user.setAddress(userDTO.getAddress());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());

		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setRole(userDTO.getRole());
		user.setActive(userDTO.getActive());

		return user;
	}

	

}
