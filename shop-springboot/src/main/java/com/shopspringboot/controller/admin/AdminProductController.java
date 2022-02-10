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

import com.shopspringboot.modelDTO.CategoryDTO;
import com.shopspringboot.modelDTO.ProductDTO;
import com.shopspringboot.service.CategoryService;
import com.shopspringboot.service.ProductService;

@Controller
public class AdminProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;

	@GetMapping("/admin/show-all-product")
	public String showAllProduct(HttpServletRequest request) {
		List<ProductDTO> productDTOs = productService.getAllProduct();

		request.setAttribute("productDTOs", productDTOs);

		return "admin/show-all-product";
	}

	@GetMapping("/admin/add-product")
	public String addProduct(Model model, HttpServletRequest request) {
		model.addAttribute("productDTO", new ProductDTO());
		
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);
		
		return "admin/add-product";
	}

	@PostMapping("/admin/add-product")
	public String addProduct(@ModelAttribute(name = "productDTO") ProductDTO productDTO,
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
		
		productDTO.setImage(imageFileName);
		
		productService.addProduct(productDTO);
		
		return "redirect:/admin/show-all-product";
	}

	@GetMapping("/admin/details-product/{id}")
	public String detailsProduct(Model model, @PathVariable(name = "id") int id, HttpServletRequest request) {
		ProductDTO productDTO = productService.getProductById(id);
		model.addAttribute(productDTO);
		
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		return "admin/details-product";
	}

	@GetMapping("/admin/update-product/{id}")
	public String updateProduct(Model model, @PathVariable(name = "id") int id, HttpServletRequest request) {
		ProductDTO productDTO = productService.getProductById(id);
		model.addAttribute(productDTO);
		
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		return "admin/details-product";
	}

	@PostMapping("/admin/update-product/{id}")
	public String updateProduct(@PathVariable(name = "id") int id, @ModelAttribute(name = "productDTO") ProductDTO productDTO,
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

			productDTO.setImage(imageFileName);
		}else {
			ProductDTO p = productService.getProductById(id);
			productDTO.setImage(p.getImage());
		}

		productService.updateProduct(productDTO);

		return "redirect:/admin/show-all-product";
	}

	@GetMapping("/admin/delete-product/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		productService.deleteProduct(id);

		return "redirect:/admin/show-all-product";
	}
}
