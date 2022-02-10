package com.shopspringboot.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {
	private int id;
	
	// thông tin đặt hàng
	private UserDTO userDTO;
	private String time; // Thời gian nhận: giờ hành chính / sau 18h
	private String note; // Ghi chú
	
	private String buyDate; // Ngày đặt hàng
	private int totalPrice; // Tổng tiền
	private String payMethod; // Phương thức thanh toán: COD / Online
	private String status; // Trạng thái: Chưa lên đơn / Đã gửi
}
