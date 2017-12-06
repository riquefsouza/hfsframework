/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.ldap;

import org.apache.logging.log4j.Logger;

import br.com.hfsframework.util.ExcecaoUtil;

/**
 * The Class LdapLoginException.
 */
public class LdapLoginException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new ldap login exception.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public LdapLoginException(String mensagem) {
		super(mensagem);
	}

	/**
	 * Instantiates a new ldap login exception.
	 *
	 * @param causa
	 *            the causa
	 */
	public LdapLoginException(Throwable causa) {
		super(causa);
	}

	/**
	 * Instantiates a new ldap login exception.
	 *
	 * @param log
	 *            the log
	 * @param mensagem
	 *            the mensagem
	 * @param causa
	 *            the causa
	 */
	public LdapLoginException(Logger log, String mensagem, Throwable causa) {
		super(mensagem, causa);
		ExcecaoUtil.getErros(log, this, mensagem, true);
	}

	/**
	 * Instantiates a new ldap login exception.
	 *
	 * @param log
	 *            the log
	 * @param mensagem
	 *            the mensagem
	 */
	public LdapLoginException(Logger log, String mensagem) {
		this(log, mensagem, null);
	}
}
