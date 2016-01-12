package com.yu.spring;

import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Qualifier uniquely determine the identifier the spring need to
 * look at once other place refer to this qualifier.
 * @author xiaoy
 *
 */
@Qualifier("filewriter")
public class FileWriter implements LogWriter {

	public void write(String text) {
		System.out.println("dummy write to file: " + text);
	}
}
