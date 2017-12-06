/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * The Class MessageContext.
 */
public class MessageContext implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Adiciona o.
	 *
	 * @param message
	 *            the message
	 * @param params
	 *            the params
	 */
	public void add(String message, Object... params) {
		FacesContext context = FacesContext.getCurrentInstance();

		if (message != null) {
			String resultado = new String(message);

			context.addMessage(null, new FacesMessage(resultado, resultado));
		}

		if (params != null && message != null) {
			for (int i = 0; i < params.length; i++) {
				if (params[i] != null) {
					context.addMessage(null, new FacesMessage(params[i].toString(), params[i].toString()));
				}
			}
		}

	}

}
