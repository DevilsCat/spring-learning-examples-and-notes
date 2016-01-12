package com.yu.spring;

public class Logger {
	
	// We pass the interface instead of concrete type.
	private LogWriter consoleWriter;
	private LogWriter fileWriter;
	
	/**
	 * Used to Autowired by the beans configure file.
	 * Note that it accepts an interface {@link LogWriter}
	 * @param consoleWriter
	 */
	public void setConsoleWriter(LogWriter writer) {
		this.consoleWriter = writer;
	}

	/**
	 * Used to Autowired by the beans configure file.
	 * Note that it accepts an interface {@link LogWriter}
	 * @param fileWriter
	 */
	public void setFileWriter(LogWriter writer) {
		this.fileWriter = writer;
	}
	
	public void writeConsole(String text) {
		consoleWriter.write(text);
	}
	
	public void writeFile(String text) {
		fileWriter.write(text);
	}
}
