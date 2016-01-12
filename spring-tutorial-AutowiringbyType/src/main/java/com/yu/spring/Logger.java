package com.yu.spring;

public class Logger {
	/**
	 * Associate with bean id "consolewriter".
	 */
	private ConsoleWriter consoleWriter;
	/**
	 * Associate with bean id "filewriter".
	 */
	private FileWriter fileWriter;
	
	/**
	 * Used to Autowired by the beans configure file.
	 * @param consoleWriter
	 */
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}

	/**
	 * Used to Autowired by the beans configure file.
	 * @param fileWriter
	 */
	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	
	public void writeConsole(String text) {
		consoleWriter.write(text);
	}
	
	public void writeFile(String text) {
		fileWriter.write(text);
	}
}
