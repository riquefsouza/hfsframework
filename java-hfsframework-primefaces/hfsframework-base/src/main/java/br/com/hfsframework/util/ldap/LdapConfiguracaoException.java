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
 * The Class LdapConfiguracaoException.
 */
public class LdapConfiguracaoException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new ldap configuracao exception.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public LdapConfiguracaoException(String mensagem) {
		super(mensagem);
	}

	/**
	 * Instantiates a new ldap configuracao exception.
	 *
	 * @param causa
	 *            the causa
	 */
	public LdapConfiguracaoException(Throwable causa) {
		super(causa);
	}

	/**
	 * Instantiates a new ldap configuracao exception.
	 *
	 * @param log
	 *            the log
	 * @param mensagem
	 *            the mensagem
	 * @param causa
	 *            the causa
	 */
	public LdapConfiguracaoException(Logger log, String mensagem, Throwable causa) {
		super(mensagem, causa);
		ExcecaoUtil.getErros(log, this, mensagem, true);
	}

	/**
	 * Instantiates a new ldap configuracao exception.
	 *
	 * @param log
	 *            the log
	 * @param mensagem
	 *            the mensagem
	 */
	public LdapConfiguracaoException(Logger log, String mensagem) {
		this(log, mensagem, null);
	}

}
