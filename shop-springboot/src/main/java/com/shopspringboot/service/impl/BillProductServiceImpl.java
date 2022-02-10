package com.shopspringboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopspringboot.dao.BillProductDao;
import com.shopspringboot.entity.Bill;
import com.shopspringboot.entity.BillProduct;
import com.shopspringboot.entity.Category;
import com.shopspringboot.entity.Product;
import com.shopspringboot.entity.User;
import com.shopspringboot.modelDTO.BillDTO;
import com.shopspringboot.modelDTO.BillProductDTO;
import com.shopspringboot.modelDTO.CategoryDTO;
import com.shopspringboot.modelDTO.ProductDTO;
import com.shopspringboot.modelDTO.UserDTO;
import com.shopspringboot.service.BillProductService;

@Service
@Transactional
public class BillProductServiceImpl implements BillProductService {

	@Autowired
	BillProductDao billProductDao;

	@Override
	public void addBillProduct(BillProductDTO billProductDTO) {
		billProductDao.addBillProduct(convertToBillProduct(billProductDTO));
	}

	@Override
	public void updateBillProduct(BillProductDTO billProductDTO) {
		billProductDao.updateBillProduct(convertToBillProduct(billProductDTO));
	}

	@Override
	public void deleteBillProduct(int id) {
		BillProduct billProduct = billProductDao.getBillProductById(id);

		billProductDao.deleteBillProduct(billProduct);
	}

	@Override
	public BillProductDTO getBillProductById(int id) {
		BillProduct billProduct = billProductDao.getBillProductById(id);

		return convertToBillProductDTO(billProduct);
	}

	@Override
	public List<BillProductDTO> getAllBillProduct() {
		List<BillProduct> billProducts = billProductDao.getAllBillProduct();
		List<BillProductDTO> billProductDTOs = new ArrayList<>();

		for (BillProduct bp : billProducts) {
			billProductDTOs.add(convertToBillProductDTO(bp));
		}

		return billProductDTOs;
	}
	
	@Override
	public List<BillProductDTO> getAllBillProductByIdBill(int idBill) {
		List<BillProduct> billProducts = billProductDao.getAllBillProductByIdBill(idBill);
		List<BillProductDTO> billProductDTOs = new ArrayList<>();

		for (BillProduct bp : billProducts) {
			billProductDTOs.add(convertToBillProductDTO(bp));
		}

		return billProductDTOs;
	}
	
	@Override
	public long countBillProduct() {
		return billProductDao.countBillProduct();
	}
	

	// Convert BillProduct - BillProductDTO
	private BillProduct convertToBillProduct(BillProductDTO billProductDTO) {
		BillProduct billProduct = new BillProduct();

		billProduct.setId(billProductDTO.getId());
		billProduct.setBill(convertToBill(billProductDTO.getBillDTO()));
		billProduct.setProduct(convertToProduct(billProductDTO.getProductDTO()));
		billProduct.setQuantity(billProductDTO.getQuantity());
		billProduct.setSubPrice(billProductDTO.getSubPrice());

		return billProduct;
	}

	private BillProductDTO convertToBillProductDTO(BillProduct billProduct) {
		BillProductDTO billProductDTO = new BillProductDTO();

		billProductDTO.setId(billProduct.getId());
		billProductDTO.setBillDTO(convertToBillDTO(billProduct.getBill()));
		billProductDTO.setProductDTO(convertToProductDTO(billProduct.getProduct()));
		billProductDTO.setQuantity(billProduct.getQuantity());
		billProductDTO.setSubPrice(billProduct.getSubPrice());

		return billProductDTO;
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

	// Convert Product - ProductDTO
	private ProductDTO convertToProductDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();

		productDTO.setId(product.getId());
		productDTO.setImage(product.getImage());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setSale(product.getSale());
		productDTO.setQuantity(product.getQuantity());
		productDTO.setRating(product.getRating());
		productDTO.setDescription(product.getDescription());

		Category category = product.getCategory();
		CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getName());
		productDTO.setCategoryDTO(categoryDTO);

		return productDTO;
	}

	private Product convertToProduct(ProductDTO productDTO) {
		Product product = new Product();

		product.setId(productDTO.getId());
		product.setImage(productDTO.getImage());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setSale(productDTO.getSale());
		product.setQuantity(productDTO.getQuantity());
		product.setRating(productDTO.getRating());
		product.setDescription(productDTO.getDescription());

		CategoryDTO categoryDTO = productDTO.getCategoryDTO();
		Category category = new Category(categoryDTO.getId(), categoryDTO.getName());
		product.setCategory(category);

		return product;
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
