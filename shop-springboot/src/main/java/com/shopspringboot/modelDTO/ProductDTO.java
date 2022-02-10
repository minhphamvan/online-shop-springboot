package com.shopspringboot.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	private int id;
	private String image;
	private String name;
	
	private int price;
	private int sale;
	private int quantity;
	
	private int rating;
	private String description;
	private CategoryDTO categoryDTO;
}
