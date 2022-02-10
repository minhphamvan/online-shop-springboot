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
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "image")
	private String image;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "id_category", nullable = false) // mapping qua field id_category là khóa ngoại trong table
														// product
	private Category category;

	@Column(name = "price")
	private int price;

	@Column(name = "sale")
	private int sale;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "rating")
	private int rating;

	@Column(name = "description")
	private String description;

}
