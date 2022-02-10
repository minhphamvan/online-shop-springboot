package com.shopspringboot.controller.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shopspringboot.entity.BillProduct;
import com.shopspringboot.modelDTO.BillDTO;
import com.shopspringboot.modelDTO.BillProductDTO;
import com.shopspringboot.modelDTO.CategoryDTO;
import com.shopspringboot.modelDTO.ProductDTO;
import com.shopspringboot.modelDTO.UserDTO;
import com.shopspringboot.modelDTO.UserPrincipal;
import com.shopspringboot.service.BillProductService;
import com.shopspringboot.service.BillService;
import com.shopspringboot.service.CategoryService;
import com.shopspringboot.service.ProductService;
import com.shopspringboot.service.UserService;
import com.shopspringboot.service.impl.UserLoginServiceImpl;
import com.shopspringboot.utils.PasswordGenerator;

@Controller
public class UserController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	UserService userService;

	@Autowired
	BillService billService;

	@Autowired
	BillProductService billProductService;

	@Autowired
	UserLoginServiceImpl userLoginService;

	@GetMapping(value = "/user/checkout")
	public String checkout(HttpServletRequest request, Model model) {
		List<ProductDTO> productDTOs = productService.getAllProduct();
		request.setAttribute("productDTOs", productDTOs);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		// Lấy ra tổng tiền
		HttpSession session = request.getSession();
		Object object = session.getAttribute("cart");

		if (object != null) {
			Map<Integer, BillProductDTO> map = (Map<Integer, BillProductDTO>) object;

			int total = 0;

			Set<Integer> set = map.keySet();
			for (Integer key : set) {
				total += map.get(key).getQuantity() * map.get(key).getProductDTO().getSale();
			}

			request.setAttribute("total", total);
			request.setAttribute("check", true);

			// Lấy ra người dùng đang đăng nhập hiện tại
			UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			UserDTO userDTO = userService.getUserById(userPrincipal.getId());
			request.setAttribute("userDTO", userDTO);

			model.addAttribute("billDTO", new BillDTO());
		}

		return "user/checkout";
	}

	@PostMapping(value = "/user/checkout")
	public String checkout(HttpServletRequest request, @ModelAttribute(name = "billDTO") BillDTO billDTO) {
		List<ProductDTO> productDTOs = productService.getAllProduct();
		request.setAttribute("productDTOs", productDTOs);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		// Lấy ra tổng tiền
		HttpSession session = request.getSession();
		Object object = session.getAttribute("cart");

		if (object != null) {
			Map<Integer, BillProductDTO> map = (Map<Integer, BillProductDTO>) object;

			int total = 0;

			Set<Integer> set = map.keySet();
			for (Integer key : set) {
				total += map.get(key).getQuantity() * map.get(key).getProductDTO().getSale();
			}

			request.setAttribute("total", total);
			request.setAttribute("check", true);

			// Lấy ra người dùng đang đăng nhập hiện tại
			UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			UserDTO userDTO = userService.getUserById(userPrincipal.getId());
			request.setAttribute("userDTO", userDTO);

			// Tạo Bill
			billDTO.setUserDTO(userDTO);
			billDTO.setBuyDate(java.time.LocalDate.now().toString());
			billDTO.setTotalPrice(total + 10);

			// Vẫn fix cứng giá trị status, payMethod
			billDTO.setStatus("Đã lên đơn");
			billDTO.setPayMethod("COD");

			billService.addBill(billDTO);

			// Tạo BillProduct
			List<BillDTO> bills = billService.getAllBill();
			int id_bill = bills.get(bills.size() - 1).getId();
			billDTO.setId(id_bill);

			for (Integer key : set) {
				BillProductDTO billProductDTO = new BillProductDTO();

				billProductDTO.setBillDTO(billDTO);
				billProductDTO.setProductDTO(map.get(key).getProductDTO());
				billProductDTO.setQuantity(map.get(key).getQuantity());
				billProductDTO.setSubPrice(map.get(key).getQuantity() * map.get(key).getProductDTO().getSale());

				billProductService.addBillProduct(billProductDTO);
			}

			session.removeAttribute("cart"); // Thanh toán xong thì xóa giỏ hàng
		}

		return "redirect:/user/show-all-bill";
	}

	@GetMapping("/user/show-all-bill")
	public String showAllBill(HttpServletRequest request) {
		List<ProductDTO> productDTOs = productService.getAllProduct();
		request.setAttribute("productDTOs", productDTOs);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		// Lấy ra người dùng đang đăng nhập hiện tại
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		UserDTO userDTO = userService.getUserById(userPrincipal.getId());

		List<BillDTO> billDTOs = billService.getAllBillByIdUser(userDTO.getId());
		request.setAttribute("billDTOs", billDTOs);

		return "user/show-all-bill";
	}

	@GetMapping("/user/details-bill/{id}")
	public String detailsBill(HttpServletRequest request, @PathVariable(name = "id") int id) {
		List<ProductDTO> productDTOs = productService.getAllProduct();
		request.setAttribute("productDTOs", productDTOs);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		List<BillProductDTO> billProductDTOs = billProductService.getAllBillProductByIdBill(id);
		request.setAttribute("billProductDTOs", billProductDTOs);

		return "user/details-bill";
	}

	@GetMapping({ "/user/my-info", "/user/details-user-login" })
	public String myInfo(HttpServletRequest request, Model model) {
		List<ProductDTO> productDTOs = productService.getAllProduct();
		request.setAttribute("productDTOs", productDTOs);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		// Lấy ra người dùng đang đăng nhập hiện tại
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		UserDTO userDTO = userService.getUserById(userPrincipal.getId());
		model.addAttribute(userDTO);

		return "user/my-info";
	}

	@PostMapping(value = { "/user/update-user-login/{id}" })
	public String updateUserLogin(@PathVariable(name = "id") int id, @ModelAttribute(name = "userDTO") UserDTO userDTO,
			@RequestParam(name = "imageFile") MultipartFile imageFile) {

		if (imageFile.getSize() > 0) {
			String originalFilename = imageFile.getOriginalFilename(); // Lấy tên file và đuôi avatar.png
			String ext = originalFilename.substring(originalFilename.lastIndexOf(".")); // Lấy đuôi file
			String imageFileName = System.currentTimeMillis() + ext; // time + .đuôi file

			// Ghi file lên ổ đĩa
			try {
				File newfile = new File("D:\\JavaSpring\\class-spring08\\" + "Save for project shop-springboot\\Image\\"
						+ imageFileName);

				FileOutputStream fileOutputStream = new FileOutputStream(newfile);
				fileOutputStream.write(imageFile.getBytes());

				fileOutputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			userDTO.setImage(imageFileName);
		} else {
			userDTO.setImage(userService.getUserById(id).getImage());
		}

		// Cập nhật trên database
		if (userDTO.getPassword().equals(userService.getUserById(id).getPassword())) {
			userService.updateUserNoChangePassword(userDTO);
		} else {
			userService.updateUser(userDTO);
		}

		userLoginService.updateUserLogin(userDTO); // Cập nhật lại người dùng đang đăng nhập

		return "redirect:/user/my-info";
	}

	@GetMapping("/user/change-password")
	public String changePassword(HttpServletRequest request) {
		List<ProductDTO> productDTOs = productService.getAllProduct();
		request.setAttribute("productDTOs", productDTOs);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		return "user/change-password";
	}

	@PostMapping("/user/update-password")
	public String updatePassword(HttpServletRequest request) {
		List<ProductDTO> productDTOs = productService.getAllProduct();
		request.setAttribute("productDTOs", productDTOs);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		// Lấy ra người dùng đang đăng nhập hiện tại
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		UserDTO userDTO = userService.getUserById(userPrincipal.getId());
		String currentPassword = userDTO.getPassword();

		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		
		boolean check = PasswordGenerator.checkHashStrings(oldPassword, currentPassword);
		
		if(check == true) {
			userDTO.setPassword(newPassword);
			
			userService.updateUser(userDTO);
			
			return "redirect:/user/my-info";
		}else {
			return "user/change-password";
		}
	}

}
