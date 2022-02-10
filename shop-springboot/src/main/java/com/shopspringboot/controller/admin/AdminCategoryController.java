package com.shopspringboot.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shopspringboot.modelDTO.CategoryDTO;
import com.shopspringboot.modelDTO.UserPrincipal;
import com.shopspringboot.service.CategoryService;

@Controller
public class AdminCategoryController<CateoryDTO> {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/admin/show-all-category")
	public String showAllCategory(HttpServletRequest request) {
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs); // dùng Model thì list sẽ bị rỗng

		return "/admin/show-all-category";
	}

	@GetMapping("/admin/add-category")
	public String addCategory(Model model, HttpServletRequest request) {
		model.addAttribute("categoryDTO", new CategoryDTO());
		
		return "/admin/add-category";
	}

	@PostMapping("/admin/add-category")
	public String addCategory(@ModelAttribute(name = "categoryDTO") CategoryDTO categoryDTO, HttpServletRequest request) {
		categoryService.addCategory(categoryDTO);

		return "redirect:/admin/show-all-category";
	}

	@GetMapping("/admin/details-category/{id}")
	public String detailsCategory(Model model, @PathVariable(name = "id") int id, HttpServletRequest request) {
		CategoryDTO categoryDTO = categoryService.getCategoryById(id);
		model.addAttribute("categoryDTO", categoryDTO);

		return "/admin/details-category";
	}

	@GetMapping("/admin/update-category/{id}")
	public String updateCategory(Model model, @PathVariable(name = "id") int id, HttpServletRequest request) {
		CategoryDTO categoryDTO = categoryService.getCategoryById(id);
		model.addAttribute("categoryDTO", categoryDTO);

		return "/admin/details-category";
	}

	@PostMapping("/admin/update-category")
	public String updateCategory(@ModelAttribute(name = "categoryDTO") CategoryDTO categoryDTO, HttpServletRequest request) {
		categoryService.updateCategory(categoryDTO);

		return "redirect:/admin/show-all-category";
	}

	@GetMapping("/admin/delete-category/{id}")
	public String deleteCategory(@PathVariable(name = "id") int id, HttpServletRequest request) {
		categoryService.deleteCategory(id);

		return "redirect:/admin/show-all-category";
	}
}
