package com.yu.spring.web.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.yu.spring.web.validations.ValidEmail;

/**
 * Contains a row in the offers table.
 * @author xiaoy
 *
 */
public class Offer {
	private int id;
	
	/**
	 * message can appear on the form.
	 */
	@Size(min=5, max=100, message="Name must be between 5 and 100 characters.")
	private String name;
	
	@NotNull
	@ValidEmail(min=6, message="This email address is invalid.")
	private String email;
	
	@Size(min=20, max=100, message="Text must be between 20 and 255 characters")
	private String text;

	public Offer() {
		
	}
	
	/**
	 * Exclude id.
	 * @param name
	 * @param email
	 * @param text
	 */
	public Offer(String name, String email, String text) {
		super();
		this.name = name;
		this.email = email;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", name=" + name + ", email=" + email
				+ ", text=" + text + "]";
	}

}
