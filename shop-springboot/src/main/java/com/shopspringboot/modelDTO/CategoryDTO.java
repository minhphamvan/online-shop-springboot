package com.shopspringboot.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
	private int id;
	private String name;

	public CategoryDTO(int id) {
		super();
		this.id = id;
	}

}
