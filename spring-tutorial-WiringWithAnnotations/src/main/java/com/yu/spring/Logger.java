package com.yu.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.*;
/**
 * The Autowired annotation can put above setters, member variables, constructor
 * or even mix as long as they're not ambiguous. The are referred by type.
 * <p>
 * Autowire even doesn't need getter, setter or constructor!
 * @author xiaoy
 *
 */
public class Logger {
	// @Autowired
	private ConsoleWriter consoleWriter;

	// @Autowired
	private LogWriter fileWriter;

	// @Autowired
	/*public Logger(ConsoleWriter consoleWriter, FileWriter fileWriter) {
		this.consoleWriter = consoleWriter;
		this.fileWriter = fileWriter;
	}*/

	/**
	 * Use Qualifier to wire to given qualifier
	 * @param consoleWriter
	 */
	@Autowired
	@Qualifier("toconsole")
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}

	/**
	 * The LogWriter is potentially ambiguous since it's just an
	 * interface. Thus we can use Qualifier to wire to another class
	 * directly.
	 * @param fileWriter
	 */
	@Autowired
	@Qualifier("filewriter")
	public void setFileWriter(LogWriter fileWriter) {
		this.fileWriter = fileWriter;
	}

	public void writeConsole(String text) {
		if (consoleWriter != null)  // If bean missing, spring will not complain.
			consoleWriter.write(text);
	}

	public void writeFile(String text) {
		if (consoleWriter != null)
			fileWriter.write(text);
	}
	
	/**
	 * We can also add life cycle method using annotation
	 * "PostConstruct" and "PreDestroy"
	 */
	@SuppressWarnings("restriction")
	@PostConstruct
	public void init() {
		System.out.println("init");
	}
	
	@SuppressWarnings("restriction")
	@PreDestroy
	public void destroy() {
		System.out.println("destroy");
	}
}
