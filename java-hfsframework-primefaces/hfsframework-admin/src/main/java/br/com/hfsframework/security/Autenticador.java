/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.security;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.Logger;
import org.picketlink.Identity;
import org.picketlink.annotations.PicketLink;
import org.picketlink.authentication.Authenticator;
import org.picketlink.authentication.BaseAuthenticator;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.credential.Credentials;
import org.picketlink.idm.model.basic.User;

import br.com.hfsframework.AplicacaoUtil;
import br.com.hfsframework.util.ExcecaoUtil;

/**
 * The Class Autenticador.
 */
@Named
@PicketLink
public class Autenticador extends BaseAuthenticator implements Authenticator, Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/** The credentials. */
	@Inject
	private DefaultLoginCredentials credentials;
	
	/** The identity. */
	@Inject
	private Identity identity;
	
	/** The aplicacao util. */
	@Inject
	private AplicacaoUtil aplicacaoUtil;
	
	/** The sistema MB. */
	@Inject
	private SistemaMB sistemaMB;
	
	/** The log. */
	@Inject
	protected transient Logger log;
	
	/**
	 * Gerar mensagem erro.
	 *
	 * @param e
	 *            the e
	 * @param mensagem
	 *            the mensagem
	 */
	public void gerarMensagemErro(Exception e, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, null, ExcecaoUtil.getErros(log, e, mensagem)));
	}

	/**
	 * Gerar mensagem informativa.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public void gerarMensagemInformativa(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, null, mensagem));
	}

	/* (non-Javadoc)
	 * @see org.picketlink.authentication.Authenticator#authenticate()
	 */
	public void authenticate() {
		String usuario = this.credentials.getUserId();
		String senha = this.credentials.getPassword();
		try {
			boolean autenticacao = sistemaMB.autenticar(usuario, senha);
			//boolean autenticacao = true;
			if (autenticacao) {
				setStatus(Authenticator.AuthenticationStatus.SUCCESS);
				setAccount(new User(usuario));
			} else {
				gerarMensagemInformativa("Usuário ou Senha inválida.");
				setStatus(Authenticator.AuthenticationStatus.FAILURE);
				redirecionaPaginaLogin();
			}
		} catch (Exception e) {
			this.log.error("Erro autenticação do usuário", e);
			gerarMensagemInformativa(e.getMessage());
			setStatus(Authenticator.AuthenticationStatus.FAILURE);
			redirecionaPaginaLogin();
		}
	}

	/**
	 * Redireciona pagina login.
	 */
	private void redirecionaPaginaLogin() {
		FacesContext fc = FacesContext.getCurrentInstance();
		NavigationHandler nav = fc.getApplication().getNavigationHandler();
		nav.handleNavigation(fc, null, "PAGINA_LOGIN");
	}

	/**
	 * Redireciona pagina inicial.
	 */
	public void redirecionaPaginaInicial() {
		if (this.identity.isLoggedIn()) {
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nav = fc.getApplication().getNavigationHandler();
			nav.handleNavigation(fc, null, "PAGINA_INICIAL_LOGADO");
		}
	}

	/* (non-Javadoc)
	 * @see org.picketlink.authentication.BaseAuthenticator#postAuthenticate()
	 */
	public void postAuthenticate() {
	}

	/**
	 * Log out.
	 *
	 * @return the string
	 */
	public String logOut() {
		try {
			aplicacaoUtil.removeUsuarioAutenticado();
			
			this.identity.logout();

			sistemaMB.logout();
			
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			return "SUCESSO";
		} catch (Exception e) {
		}
		return "ERRO";
	}

	/**
	 * Pega o the credentials.
	 *
	 * @return o the credentials
	 */
	public Credentials getCredentials() {
		return this.credentials;
	}

	/**
	 * Atribui o the credentials.
	 *
	 * @param credentials
	 *            o novo the credentials
	 */
	public void setCredentials(DefaultLoginCredentials credentials) {
		this.credentials = credentials;
	}
}
