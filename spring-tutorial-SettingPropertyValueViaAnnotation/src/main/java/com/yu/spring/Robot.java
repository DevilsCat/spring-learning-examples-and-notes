package com.yu.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Robot {

	/**
	 * These are hard coded properties
	 */
	private int id = 0;
	private String speech = "hello";
	
	public void speak() {
		System.out.println(id + ": " + speech);
	}
	
	/**
	 * The value is set to another hard coded value 1138
	 * (the string value will automatically change to Integer)
	 * @param id
	 */
	@Autowired
	public void setId(@Value("1138")int id) {
		this.id = id;
	}
	
	/**
	 * The same issue.
	 * @param speech
	 */
	@Autowired
	public void setSpeech(@Value("I'll be back.")String speech) {
		this.speech = speech;
	}
	
}
