package com.yu.spring.web.dao;

/**
 * Contains a row in the offers table.
 * @author xiaoy
 *
 */
public class Offer {
	private int id;
	private String name;
	private String email;
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
