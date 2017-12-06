/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ErroInesperado.
 */
public class ErroInesperado extends RuntimeException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new erro inesperado.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public ErroInesperado(String message, Exception cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new erro inesperado.
	 *
	 * @param message
	 *            the message
	 */
	public ErroInesperado(String message) {
		super(message);
	}

	/**
	 * Instantiates a new erro inesperado.
	 *
	 * @param e
	 *            the e
	 */
	public ErroInesperado(Throwable e) {
		super(e);
	}

	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	public List<String> getMessages() {
		List<String> messages = new ArrayList<String>();
		messages.add(getMessage());
		return messages;
	}
}
