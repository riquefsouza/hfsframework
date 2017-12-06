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
 * The Class ErroEsperado.
 */
public class ErroEsperado extends RuntimeException {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant SEPARADOR. */
	private static final String SEPARADOR = ", ";
	
	/** The mensagens. */
	private List<String> mensagens = new ArrayList<String>();

	/**
	 * Instantiates a new erro esperado.
	 */
	public ErroEsperado() {
	}

	/**
	 * Instantiates a new erro esperado.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public ErroEsperado(String mensagem) {
		this.mensagens.add(mensagem);
	}

	/**
	 * Instantiates a new erro esperado.
	 *
	 * @param mensagem
	 *            the mensagem
	 * @param cause
	 *            the cause
	 */
	public ErroEsperado(String mensagem, Throwable cause) {
		super(cause);
		this.mensagens.add(mensagem);
	}

	/**
	 * Pega o the mensagens.
	 *
	 * @return o the mensagens
	 */
	public List<String> getMensagens() {
		return this.mensagens;
	}

	/**
	 * Adicionar mensagem.
	 *
	 * @param mensagem
	 *            the mensagem
	 * @return the erro esperado
	 */
	public ErroEsperado adicionarMensagem(String mensagem) {
		this.mensagens.add(mensagem);
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		StringBuilder message = new StringBuilder();
		if (this.mensagens.size() > 0) {
			for (int i = 0; i < this.mensagens.size() - 1; i++) {
				String m = (String) this.mensagens.get(i);
				message.append(m);
				message.append(SEPARADOR);
			}
			message.append((String) this.mensagens.get(this.mensagens.size() - 1));
		}
		return message.toString();
	}
}
