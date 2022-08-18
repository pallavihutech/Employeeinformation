package com.crud.api.model;

import java.io.Serializable;
import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ticket")
public class Ticket implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ticket() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int amount;

	private String category;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Ticket(int id, int amount, String category,String name) {
		super();
		this.id = id;
		this.amount = amount;
		this.category = category;
		this.name=name;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", amount=" + amount + ", category=" + category + "]";
	}

	public String base64Encode(String PIData) {
		String data = Base64.getEncoder().encodeToString(PIData.getBytes());
		return data;
	}

	public String base64Decode(String PIData) {
		byte[] data = Base64.getDecoder().decode(PIData);
		String decodedData = new String(data);
		return decodedData;
	}
}
