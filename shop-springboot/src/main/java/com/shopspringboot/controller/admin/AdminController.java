package com.shopspringboot.controller.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shopspringboot.dao.UserDao;
import com.shopspringboot.modelDTO.UserDTO;
import com.shopspringboot.modelDTO.UserPrincipal;
import com.shopspringboot.service.BillService;
import com.shopspringboot.service.CategoryService;
import com.shopspringboot.service.ProductService;
import com.shopspringboot.service.UserService;
import com.shopspringboot.service.impl.UserLoginServiceImpl;

@Controller
public class AdminController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	BillService billService;
	
	@Autowired
	UserLoginServiceImpl userLoginService;

	@GetMapping(value = {"/admin", "/admin/dashboard"})
	public String dashboard(HttpServletRequest request, Model model) {
		long numberUser = userService.countUser();
		request.setAttribute("numberUser", numberUser);
		
		long numberCategory = categoryService.countCategory();
		request.setAttribute("numberCategory", numberCategory);
		
		long numberProduct = productService.countProduct();
		request.setAttribute("numberProduct", numberProduct);
		
		long numberBill = billService.countBill();
		request.setAttribute("numberBill", numberBill);
		
		Map<String, Long> chartData  = userService.countByAddress();
		
        model.addAttribute("chartData", chartData);

		return "admin/dashboard";
	}
	
	@GetMapping(value = {"/admin/details-user-login"})
	public String detailsUserLogin(HttpServletRequest request, Model model) {
		// Lấy ra người dùng đang đăng nhập hiện tại
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		UserDTO userDTO = userService.getUserById(userPrincipal.getId());
		
		model.addAttribute(userDTO);
		
		return "admin/details-user-login";
	}
	
	@PostMapping(value = {"/admin/update-user-login/{id}"})
	public String updateUserLogin(@PathVariable(name = "id") int id, 
			@ModelAttribute(name = "userDTO") UserDTO userDTO,
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
		}else {
			userDTO.setImage(userService.getUserById(id).getImage());
		}
		
		// Cập nhật trên database
		if(userDTO.getPassword().equals(userService.getUserById(id).getPassword())){
			userService.updateUserNoChangePassword(userDTO); 
		} else {
			userService.updateUser(userDTO);
		}
		
		userLoginService.updateUserLogin(userDTO); // Cập nhật lại người dùng đang đăng nhập

		return "redirect:/admin/show-all-user";
	}
}
