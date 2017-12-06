/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.excel;

import org.apache.logging.log4j.Logger;

/**
 * The Class PdfException.
 */
public class ExcelException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new excel exception.
	 *
	 * @param message
	 *            the message
	 */
	public ExcelException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new excel exception.
	 *
	 * @param log
	 *            the log
	 * @param message
	 *            the message
	 */
	public ExcelException(Logger log, String message) {
		super(message);
		log.fatal(message);
	}

}
