package com.yu.spring;

public class ConsoleWriter implements LogWriter {
	
	public void write(String text) {
		System.out.println("dummy write to console: " + text);
	}
}
