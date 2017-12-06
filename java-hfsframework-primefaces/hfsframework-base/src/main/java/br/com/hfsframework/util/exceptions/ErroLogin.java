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
 * The Class ErroLogin.
 */
public class ErroLogin extends RuntimeException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new erro login.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public ErroLogin(String message, Exception cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new erro login.
	 *
	 * @param message
	 *            the message
	 */
	public ErroLogin(String message) {
		super(message);
	}

	/**
	 * Instantiates a new erro login.
	 *
	 * @param e
	 *            the e
	 */
	public ErroLogin(Throwable e) {
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
