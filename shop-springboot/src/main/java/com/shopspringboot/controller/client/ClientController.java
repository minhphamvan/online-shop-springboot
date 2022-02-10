package com.shopspringboot.controller.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shopspringboot.modelDTO.BillProductDTO;
import com.shopspringboot.modelDTO.CategoryDTO;
import com.shopspringboot.modelDTO.ProductDTO;
import com.shopspringboot.modelDTO.UserDTO;
import com.shopspringboot.service.CategoryService;
import com.shopspringboot.service.ProductService;
import com.shopspringboot.service.UserService;

@Controller
public class ClientController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;

	@GetMapping(value = {"/", "/index"})
	public String index(HttpServletRequest request) {
		List<ProductDTO> productDTOs = productService.getAllProduct();
		request.setAttribute("productDTOs", productDTOs);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		return "client/index";
	}

//	@GetMapping(value = "/show-all-product")
//	public String showAllProduct(HttpServletRequest request) {
//		List<ProductDTO> productDTOs = productService.getAllProduct();
//		request.setAttribute("productDTOs", productDTOs);
//
//		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
//		request.setAttribute("categoryDTOs", categoryDTOs);
//
//		return "client/show-all-product";
//	}
	
	@GetMapping(value = "/show-all-product/{pageNo}")
	public String showAllProductPagination(HttpServletRequest request, 
			@PathVariable("pageNo") long pageNo, Model model) {
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		long limit = 3;
		
		long totalRow = productService.countProduct(); // đếm số lượng bản ghi
		long totalPage = (long) Math.ceil(totalRow / limit) + 1; // số trang cần thiết
		long startFromRecord = (pageNo - 1) * limit; // bắt đầu từ bản ghi nào?

		if (totalPage > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, (int) totalPage)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
		
		List<ProductDTO> productDTOs = productService.getProductPagination(limit, startFromRecord);
		request.setAttribute("productDTOs", productDTOs);
		
		return "client/show-all-product";
	}
	
	@GetMapping(value = "/search/{name}/{pageNo}")
	public String searchProductPagination(HttpServletRequest request, @PathVariable("name") String name,
			@PathVariable("pageNo") long pageNo, Model model) {
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		long limit = 3;
		
		long totalRow = productService.countProduct(); // đếm số lượng bản ghi
		long totalPage = (long) Math.ceil(totalRow / limit) + 1; // số trang cần thiết
		long startFromRecord = (pageNo - 1) * limit; // bắt đầu từ bản ghi nào?

		if (totalPage > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, (int) totalPage)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
		
		request.setAttribute("name", name);
		System.out.println(name);
		
		List<ProductDTO> productDTOs = productService.getProductByNamePagination(name, limit, startFromRecord);
		request.setAttribute("productDTOs", productDTOs);
		System.out.println(productDTOs);
		
		return "client/search-product";
	}

	@GetMapping(value = "/details-product/{id}")
	public String detailsProduct(HttpServletRequest request, @PathVariable(name = "id") int id) {
		List<ProductDTO> productDTOs = productService.getAllProduct();
		request.setAttribute("productDTOs", productDTOs);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		ProductDTO productDTO = productService.getProductById(id);
		request.setAttribute("productDTO", productDTO);

		return "client/details-product";
	}

	@GetMapping(value = "/contact")
	public String contact(HttpServletRequest request) {
		List<ProductDTO> productDTOs = productService.getAllProduct();
		request.setAttribute("productDTOs", productDTOs);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		return "client/contact";
	}

	@GetMapping(value = "/cart")
	public String cart(HttpServletRequest request, Model model) {
		List<ProductDTO> productDTOs = productService.getAllProduct();
		request.setAttribute("productDTOs", productDTOs);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		// Lấy ra tổng tiền
		HttpSession session = request.getSession();
		Object object = session.getAttribute("cart");

		if (object != null) {
			Map<Integer, BillProductDTO> map = (Map<Integer, BillProductDTO>) object;

			int totalPrice = 0;

			Set<Integer> set = map.keySet();
			for (Integer key : set) {
				totalPrice += map.get(key).getQuantity() * map.get(key).getProductDTO().getSale();
			}

			request.setAttribute("totalPrice", totalPrice);
			request.setAttribute("check", true);
		} else {
			request.setAttribute("check", false); // không có giỏ hàng
		}

		return "client/cart";
	}

	@GetMapping(value = "/add-to-cart/{id}")
	public String addToCart(HttpServletRequest request, @PathVariable(name = "id") int id) {
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		ProductDTO productDTO = productService.getProductById(id);

		HttpSession session = request.getSession();
		Object object = session.getAttribute("cart");

		if (object == null) { // nếu giỏ rỗng
			BillProductDTO billProductDTO = new BillProductDTO();

			billProductDTO.setProductDTO(productDTO);
			billProductDTO.setQuantity(1);
			billProductDTO.setSubPrice(productDTO.getSale() * 1);

			Map<Integer, BillProductDTO> map = new HashMap<Integer, BillProductDTO>();
			map.put(id, billProductDTO);

			session.setAttribute("cart", map);
		} else { // nếu giỏ đã có sản phẩm
			Map<Integer, BillProductDTO> map = (Map<Integer, BillProductDTO>) object;

			BillProductDTO billProductDTO = map.get(id);

			if (billProductDTO == null) { // nếu giỏ chưa có sản phẩm này
				billProductDTO = new BillProductDTO();

				billProductDTO.setProductDTO(productDTO);
				billProductDTO.setQuantity(1);
				billProductDTO.setSubPrice(productDTO.getSale() * 1);

				map.put(id, billProductDTO);
			} else { // nếu đã có sản phẩm này trong giỏ thì tăng số lượng +1
				billProductDTO.setQuantity(billProductDTO.getQuantity() + 1);
			}

			session.setAttribute("cart", map);
		}

		return "redirect:/cart";
	}

	@GetMapping(value = "/delete-from-cart/{id}")
	public String deleteFromCart(HttpServletRequest request, @PathVariable(name = "id") int id) {
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		HttpSession session = request.getSession();
		Object object = session.getAttribute("cart");

		if (object != null) { // giỏ hàng đã có sản phẩm
			Map<Integer, BillProductDTO> map = (Map<Integer, BillProductDTO>) object;

			map.remove(id);

			if (map.size() == 0) {
				session.removeAttribute("cart");
			} else {
				session.setAttribute("cart", map);
			}
		}

		return "redirect:/cart";
	}
	
	@GetMapping(value = "/login")
	public String login(HttpServletRequest request, 
			@RequestParam(name = "err", required = false) String error) {
		List<ProductDTO> productDTOs = productService.getAllProduct();
		request.setAttribute("productDTOs", productDTOs);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);
		
		if (error != null) {
			request.setAttribute("err", error);
		}
		
		return "client/login";
	}

	@GetMapping(value = "/register")
	public String register(HttpServletRequest request, Model model) {
		List<ProductDTO> productDTOs = productService.getAllProduct();
		request.setAttribute("productDTOs", productDTOs);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);
		
		UserDTO userDTO = new UserDTO();
		
		userDTO.setActive(true);
		userDTO.setRole("ROLE_USER");
		
		model.addAttribute(userDTO);
		
		return "client/register";
	}
	
	@PostMapping(value = "/register")
	public String register(HttpServletRequest request, @ModelAttribute(name = "userDTO") UserDTO userDTO,
			@RequestParam(name = "imageFile") MultipartFile imageFile) {
		List<ProductDTO> productDTOs = productService.getAllProduct();
		request.setAttribute("productDTOs", productDTOs);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);
		
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
		
		return "redirect:/login";
	}

	// Chưa làm
	@GetMapping(value = "/forget-password")
	public String forgetPassword(HttpServletRequest request) {
		List<ProductDTO> productDTOs = productService.getAllProduct();
		request.setAttribute("productDTOs", productDTOs);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);
		
		return "client/forget-password";
	}
	
	// Chưa làm
	@GetMapping(value = "/wishlist")
	public String wishlist(HttpServletRequest request) {
		List<ProductDTO> productDTOs = productService.getAllProduct();
		request.setAttribute("productDTOs", productDTOs);

		List<CategoryDTO> categoryDTOs = categoryService.getAllCategory();
		request.setAttribute("categoryDTOs", categoryDTOs);

		return "client/wishlist";
	}

}
