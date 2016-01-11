package com.yu.spring;

public class FileWriter implements LogWriter {

	public void write(String text) {
		System.out.println("dummy write to file: " + text);
	}
}
