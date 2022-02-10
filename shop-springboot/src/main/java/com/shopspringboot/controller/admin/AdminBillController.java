package com.shopspringboot.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopspringboot.modelDTO.BillDTO;
import com.shopspringboot.modelDTO.BillProductDTO;
import com.shopspringboot.modelDTO.CategoryDTO;
import com.shopspringboot.modelDTO.ProductDTO;
import com.shopspringboot.modelDTO.UserPrincipal;
import com.shopspringboot.service.BillProductService;
import com.shopspringboot.service.BillService;
import com.shopspringboot.service.CategoryService;
import com.shopspringboot.service.ProductService;

@Controller
public class AdminBillController {

	@Autowired
	BillService billService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;

	@Autowired
	BillProductService billProductService;
	
	@GetMapping("/admin/show-all-bill")
	public String showAllBill(HttpServletRequest request) {
		List<BillDTO> billDTOs = billService.getAllBill();
		request.setAttribute("billDTOs", billDTOs);

		return "/admin/show-all-bill";
	}
	
	
	@GetMapping("/admin/details-bill/{id}")
	public String detailsBill(HttpServletRequest request, @PathVariable(name = "id") int id) {
		List<ProductDTO> productDTOs = productService.getAllProduct();
		request.setAttribute("productDTOs", productDTOs);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		// Lấy ra người dùng đang đăng nhập hiện tại
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		request.setAttribute("userPrincipal", userPrincipal);

		List<BillProductDTO> billProductDTOs = billProductService.getAllBillProductByIdBill(id);
		request.setAttribute("billProductDTOs", billProductDTOs);

		return "admin/details-bill";
	}

}
