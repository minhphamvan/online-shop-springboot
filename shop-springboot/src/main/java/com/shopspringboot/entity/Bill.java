package com.shopspringboot.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="bill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;
	
	@Column(name="time")
	private String time; // Thời gian nhận: giờ hành chính / sau 18h
	
	@Column(name="note")
	private String note; // Ghi chú
	
	@Column(name="buy_date")
	private String buyDate; // Ngày đặt hàng
	
	@Column(name="total_price")
	private int totalPrice; // Tổng tiền
	
	@Column(name="pay_method")
	private String payMethod; // Phương thức thanh toán: COD / Online
	
	@Column(name="status")
	private String status; // Trạng thái: Chưa lên đơn / Đã gửi
}