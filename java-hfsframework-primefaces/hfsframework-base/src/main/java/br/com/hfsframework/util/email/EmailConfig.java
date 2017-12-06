/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.email;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * The Class EmailConfig.
 */
public class EmailConfig implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The email tipo. */
	private String emailTipo;
	
	/** The email usuario. */
	private String emailUsuario;
	
	/** The email senha. */
	private String emailSenha;
	
	/** The email server host. */
	private String emailServerHost = "localhost";
	
	/** The email server port. */
	private int emailServerPort = 25;
	
	/** The email nome dominio. */
	private String emailNomeDominio;
	
	/** The email habilita TLS. */
	private boolean emailHabilitaTLS = false;
	
	/** The email requer TLS. */
	private boolean emailRequerTLS = false;
	
	/** The email habilita SSL. */
	private boolean emailHabilitaSSL = false;
	
	/** The email autentica. */
	private boolean emailAutentica = false;

	/** The bundle. */
	private ResourceBundle bundle;

	/**
	 * Instantiates a new email config.
	 */
	public EmailConfig() {
		super();

		FacesContext context = FacesContext.getCurrentInstance();
		Locale locale = context.getViewRoot().getLocale();

		// locale = new Locale("pt", "BR");
		bundle = ResourceBundle.getBundle("email", locale);

		carregar();
	}

	/**
	 * Instantiates a new email config.
	 *
	 * @param bundle
	 *            the bundle
	 */
	public EmailConfig(ResourceBundle bundle) {
		this.bundle = bundle;

		carregar();
	}

	/**
	 * Carregar.
	 */
	private void carregar() {
		if (bundle != null) {
			this.emailTipo = bundle.getString("emailTipo");
			this.emailUsuario = bundle.getString("emailUsuario");
			this.emailSenha = bundle.getString("emailSenha");
			this.emailServerHost = bundle.getString("emailServerHost");
			this.emailServerPort = Integer.parseInt(bundle.getString("emailServerPort"));
			this.emailNomeDominio = bundle.getString("emailNomeDominio");
			this.emailHabilitaTLS = Boolean.parseBoolean(bundle.getString("emailHabilitaTLS"));
			this.emailRequerTLS = Boolean.parseBoolean(bundle.getString("emailRequerTLS"));
			this.emailHabilitaSSL = Boolean.parseBoolean(bundle.getString("emailHabilitaSSL"));
			this.emailAutentica = Boolean.parseBoolean(bundle.getString("emailAutentica"));
		}
	}

	/**
	 * Gets the authenticator.
	 *
	 * @return the authenticator
	 */
	public Authenticator getAuthenticator() {
		if ((this.emailUsuario == null) || (this.emailSenha == null)) {
			return null;
		}
		final PasswordAuthentication pa = new PasswordAuthentication(this.emailUsuario, this.emailSenha);
		return new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return pa;
			}
		};
	}

	/**
	 * Gets the email tipo.
	 *
	 * @return the email tipo
	 */
	public String getEmailTipo() {
		return emailTipo;
	}

	/**
	 * Sets the email tipo.
	 *
	 * @param emailTipo
	 *            the new email tipo
	 */
	public void setEmailTipo(String emailTipo) {
		this.emailTipo = emailTipo;
	}

	/**
	 * Gets the email usuario.
	 *
	 * @return the email usuario
	 */
	public String getEmailUsuario() {
		return emailUsuario;
	}

	/**
	 * Sets the email usuario.
	 *
	 * @param emailUsuario
	 *            the new email usuario
	 */
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	/**
	 * Gets the email senha.
	 *
	 * @return the email senha
	 */
	public String getEmailSenha() {
		return emailSenha;
	}

	/**
	 * Sets the email senha.
	 *
	 * @param emailSenha
	 *            the new email senha
	 */
	public void setEmailSenha(String emailSenha) {
		this.emailSenha = emailSenha;
	}

	/**
	 * Gets the email server host.
	 *
	 * @return the email server host
	 */
	public String getEmailServerHost() {
		return emailServerHost;
	}

	/**
	 * Sets the email server host.
	 *
	 * @param emailServerHost
	 *            the new email server host
	 */
	public void setEmailServerHost(String emailServerHost) {
		this.emailServerHost = emailServerHost;
	}

	/**
	 * Gets the email server port.
	 *
	 * @return the email server port
	 */
	public int getEmailServerPort() {
		return emailServerPort;
	}

	/**
	 * Sets the email server port.
	 *
	 * @param emailServerPort
	 *            the new email server port
	 */
	public void setEmailServerPort(int emailServerPort) {
		this.emailServerPort = emailServerPort;
	}

	/**
	 * Gets the email nome dominio.
	 *
	 * @return the email nome dominio
	 */
	public String getEmailNomeDominio() {
		return emailNomeDominio;
	}

	/**
	 * Sets the email nome dominio.
	 *
	 * @param emailNomeDominio
	 *            the new email nome dominio
	 */
	public void setEmailNomeDominio(String emailNomeDominio) {
		this.emailNomeDominio = emailNomeDominio;
	}

	/**
	 * Checks if is email habilita TLS.
	 *
	 * @return true, if is email habilita TLS
	 */
	public boolean isEmailHabilitaTLS() {
		return emailHabilitaTLS;
	}

	/**
	 * Sets the email habilita TLS.
	 *
	 * @param emailHabilitaTLS
	 *            the new email habilita TLS
	 */
	public void setEmailHabilitaTLS(boolean emailHabilitaTLS) {
		this.emailHabilitaTLS = emailHabilitaTLS;
	}

	/**
	 * Checks if is email requer TLS.
	 *
	 * @return true, if is email requer TLS
	 */
	public boolean isEmailRequerTLS() {
		return emailRequerTLS;
	}

	/**
	 * Sets the email requer TLS.
	 *
	 * @param emailRequerTLS
	 *            the new email requer TLS
	 */
	public void setEmailRequerTLS(boolean emailRequerTLS) {
		this.emailRequerTLS = emailRequerTLS;
	}

	/**
	 * Checks if is email habilita SSL.
	 *
	 * @return true, if is email habilita SSL
	 */
	public boolean isEmailHabilitaSSL() {
		return emailHabilitaSSL;
	}

	/**
	 * Sets the email habilita SSL.
	 *
	 * @param emailHabilitaSSL
	 *            the new email habilita SSL
	 */
	public void setEmailHabilitaSSL(boolean emailHabilitaSSL) {
		this.emailHabilitaSSL = emailHabilitaSSL;
	}

	/**
	 * Checks if is email autentica.
	 *
	 * @return true, if is email autentica
	 */
	public boolean isEmailAutentica() {
		return emailAutentica;
	}

	/**
	 * Sets the email autentica.
	 *
	 * @param emailAutentica
	 *            the new email autentica
	 */
	public void setEmailAutentica(boolean emailAutentica) {
		this.emailAutentica = emailAutentica;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailConfig [emailTipo=" + emailTipo + ", emailUsuario=" + emailUsuario + ", emailSenha=" + emailSenha
				+ ", emailServerHost=" + emailServerHost + ", emailServerPort=" + emailServerPort
				+ ", emailNomeDominio=" + emailNomeDominio + ", emailHabilitaTLS=" + emailHabilitaTLS
				+ ", emailRequerTLS=" + emailRequerTLS + ", emailHabilitaSSL=" + emailHabilitaSSL + ", emailAutentica="
				+ emailAutentica + "]";
	}

}
