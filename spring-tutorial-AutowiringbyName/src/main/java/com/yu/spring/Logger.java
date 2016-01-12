package com.yu.spring;

public class Logger {
	
	// We pass the interface instead of concrete type.
	private LogWriter consoleWriter;
	private LogWriter fileWriter;
	
	/**
	 * autowiring using this constructor.
	 * @param consoleWriter
	 * @param fileWriter
	 */
	public Logger(ConsoleWriter consoleWriter, FileWriter fileWriter) {
		this.consoleWriter = consoleWriter;
		this.fileWriter = fileWriter;
	}
	
	public void setConsoleWriter(LogWriter writer) {
		this.consoleWriter = writer;
	}

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
