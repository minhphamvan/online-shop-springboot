package com.shopspringboot.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillProductDTO { // Chi tiết đơn hàng
	private int id;
	
	private BillDTO billDTO; 
	private ProductDTO productDTO;
	
	private int quantity; // Số lượng sản phẩm
	private int subPrice; // Giá tiền * số lượng sản phẩm
}
