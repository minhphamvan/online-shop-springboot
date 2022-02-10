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
@Table(name = "bill_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_bill")
	private Bill bill;

	@ManyToOne
	@JoinColumn(name = "id_product")
	private Product product;

	@Column(name = "quantity")
	private int quantity; // Số lượng sản phẩm

	@Column(name = "sub_price")
	private int subPrice; // Giá tiền * số lượng sản phẩm
}