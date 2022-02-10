package com.shopspringboot.controller.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shopspringboot.modelDTO.UserDTO;
import com.shopspringboot.service.UserService;

@Controller
public class AdminUserController {

	@Autowired
	UserService userService;

	@GetMapping("/admin/show-all-user")
	public String showAllUser(HttpServletRequest request) {
		List<UserDTO> userDTOs = userService.getAllUser();
		request.setAttribute("userDTOs", userDTOs);

		return "/admin/show-all-user";
	}

	@GetMapping("/admin/add-user")
	public String addUser(Model model) {
		model.addAttribute(new UserDTO());

		return "/admin/add-user";
	}

	@PostMapping("/admin/add-user")
	public String addUser(@ModelAttribute(name = "userDTO") UserDTO userDTO,
			@RequestParam(name = "imageFile") MultipartFile imageFile) {

		String originalFilename = imageFile.getOriginalFilename(); // Lấy tên file và đuôi avatar.png
		String ext = originalFilename.substring(originalFilename.lastIndexOf(".")); // Lấy đuôi file
		String imageFileName = System.currentTimeMillis() + ext; // time + .đuôi file

		// Ghi file lên ổ đĩa
		try {
			File newfile = new File(
					"D:\\JavaSpring\\class-spring08\\" + "Save for project shop-springboot\\Image\\" + imageFileName);

			FileOutputStream fileOutputStream = new FileOutputStream(newfile);
			fileOutputStream.write(imageFile.getBytes());

			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		userDTO.setImage(imageFileName);

		userService.addUser(userDTO);

		return "redirect:/admin/show-all-user";
	}

	@GetMapping("/admin/details-user/{id}")
	public String detailsUser(Model model, @PathVariable(name = "id") int id) {
		UserDTO userDTO = userService.getUserById(id);
		model.addAttribute(userDTO);

		return "/admin/details-user";
	}

	@GetMapping("/admin/update-user/{id}")
	public String updateUser(Model model, @PathVariable(name = "id") int id) {
		UserDTO userDTO = userService.getUserById(id);
		model.addAttribute(userDTO);

		return "/admin/details-user";
	}

	@PostMapping("/admin/update-user/{id}")
	public String updateUser(@PathVariable(name = "id") int id, @ModelAttribute(name = "userDTO") UserDTO userDTO,
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
		
		if(userDTO.getPassword().equals(userService.getUserById(id).getPassword())){
			userService.updateUserNoChangePassword(userDTO); 
		} else {
			userService.updateUser(userDTO);
		}

		return "redirect:/admin/show-all-user";
	}

	@GetMapping("/admin/delete-user/{id}")
	public String deleteUser(@PathVariable(name = "id") int id) {
		userService.deleteUser(id);

		return "redirect:/admin/show-all-user";
	}
}
