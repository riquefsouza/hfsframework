/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.hfsframework.AplicacaoConfig;
import br.com.hfsframework.AplicacaoUtil;
import br.com.hfsframework.admin.business.AdmPerfilBC;
import br.com.hfsframework.admin.business.AdmUsuarioBC;
import br.com.hfsframework.admin.business.ModoTesteBC;
import br.com.hfsframework.admin.model.AdmUsuario;
import br.com.hfsframework.base.BaseViewController;
import br.com.hfsframework.security.model.MenuVO;
import br.com.hfsframework.security.model.PaginaVO;
import br.com.hfsframework.security.model.PermissaoVO;
import br.com.hfsframework.security.model.UsuarioAutenticadoVO;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;
import br.com.hfsframework.util.ldap.LdapConfig;
import br.com.hfsframework.util.ldap.LdapUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class SistemaMB.
 */
@Named
@ViewScoped
@TratamentoErrosEsperados
public class SistemaMB extends BaseViewController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ldap util. */
	@Inject	
	private LdapUtil ldapUtil;
	
	/** The adm perfil BC. */
	@Inject
	private AdmPerfilBC admPerfilBC;

	/** The aplicacao config. */
	@Inject
	private AplicacaoConfig aplicacaoConfig;

	/** The aplicacao util. */
	@Inject
	private AplicacaoUtil aplicacaoUtil;

	/** The adm usuario BC. */
	@Inject
	private AdmUsuarioBC admUsuarioBC;

	/** The usuario autenticado. */
	@Inject
	protected UsuarioAutenticadoVO usuarioAutenticado;
	
	/** The usuario logado. */
	private AdmUsuario usuarioLogado;
	
	@Inject
	private ModoTesteBC modoTesteBC;
	
	/**
	 * Inicia o.
	 */
	@PostConstruct
	public void init() {
		// this.log.debug("userName: " + this.usuarioAutenticado.getUserName());
	}

	/**
	 * Segura sessao.
	 */
	public void seguraSessao() {
		this.log.warn(this.usuarioAutenticado.getDisplayName() + " continua conectado na sessão");
	}

	/**
	 * Autenticar.
	 *
	 * @param login
	 *            the login
	 * @param senha
	 *            the senha
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public boolean autenticar(String login, String senha) throws Exception {
		if (aplicacaoConfig.isHabilitarLDAP()) {
			if (autenticarViaLDAP(login, senha)) {
				setPropriedades(login);
				return true;
			}
		} else {
			if (autenticarViaBanco(login, senha)) {
				setPropriedades(login);
				return true;
			}
		}
		return false;
	}

	/**
	 * Logout.
	 */
	public void logout(){
		//ldapLogin.logout();
	}
	
	/**
	 * Sets the propriedades.
	 *
	 * @param login
	 *            the new propriedades
	 * @throws Exception
	 *             the exception
	 */
	private void setPropriedades(String login) throws Exception {
		this.usuarioAutenticado.setUserName(login);
		if (!aplicacaoUtil.isDebugMode() && aplicacaoConfig.isHabilitarControlePerfil()) {
			
			if (aplicacaoConfig.isHabilitarLDAP()) 
				this.usuarioAutenticado.setUsuario(admUsuarioBC.load(ldapUtil.getMatricula()).toUsuarioVO());
			else
				this.usuarioAutenticado.setUsuario(admUsuarioBC.load(usuarioLogado.getId()).toUsuarioVO());
				
			this.usuarioAutenticado.setDisplayName(this.usuarioAutenticado.getUsuario().getNome());
			this.usuarioAutenticado.setEmail(this.usuarioAutenticado.getUsuario().getEmail());
			this.usuarioAutenticado.setListaPermissao(admPerfilBC.getPermissao(usuarioAutenticado));

			if (!this.usuarioAutenticado.getListaPermissao().isEmpty()){
				
				List<Long> listaIdPerfis = new ArrayList<Long>();
				for (PermissaoVO permissao: this.usuarioAutenticado.getListaPermissao()) {
					listaIdPerfis.add(permissao.getPerfil().getId());
				}

				this.usuarioAutenticado.setListaMenus(
						admPerfilBC.findMenuPaiByPerfil(listaIdPerfis));
				
				this.usuarioAutenticado.setListaAdminMenus(
						admPerfilBC.findAdminMenuPaiByPerfil(listaIdPerfis));
				
			} else {
				throw new Exception("Usuário sem perfil associado!");
			}
			
			try {
				
				if (aplicacaoConfig.isHabilitarLDAP()) {
					this.usuarioAutenticado.setUsuario(admUsuarioBC.getUsuario(
							ldapUtil.getMatricula(),
							ldapUtil.getLogin(), ldapUtil.getNome(),
							this.usuarioAutenticado.getUsuario().getCpf(),
							ldapUtil.getEmail(), ldapUtil.getLdapDN()).toUsuarioVO());
				} else {
					this.usuarioAutenticado.setUsuario(admUsuarioBC.getUsuario(
							usuarioAutenticado.getUsuario().getId(),
							usuarioLogado.getLogin(), usuarioAutenticado.getUsuario().getNome(),
							this.usuarioAutenticado.getUsuario().getCpf(),
							usuarioAutenticado.getUsuario().getEmail(), "").toUsuarioVO());
				}
				
				this.usuarioAutenticado = modoTesteBC.iniciar(this.usuarioAutenticado);				
					
				aplicacaoUtil.setUsuarioAutenticado(this.usuarioAutenticado);
				
			} catch (Exception e) {
				gerarMensagemErro(e, e.getMessage());
			}
			
			this.log.info(this.usuarioAutenticado.getUserName() + ", Perfis: "
					+ this.usuarioAutenticado.getListaPermissao().toString());
			mostrarPerfilURL();
			mostrarMenus();
		}
	}

	/**
	 * Mostrar perfil URL.
	 */
	public void mostrarPerfilURL() {
		for (PermissaoVO permissao : this.usuarioAutenticado.getListaPermissao()) {
			for (PaginaVO admPagina : permissao.getPaginas()) {
				log.info("Perfil: " + permissao.getPerfil().getDescricao() + " -> Pagina URL: " + admPagina.getUrl());
			}
		}
	}

	/**
	 * Mostrar menus.
	 */
	public void mostrarMenus() {		
		for (MenuVO menu : this.usuarioAutenticado.getListaMenus()) {
			log.info("Menu: " + menu.toString());
		}
		for (MenuVO menu : this.usuarioAutenticado.getListaAdminMenus()) {
			log.info("Menu Admin: " + menu.toString());
		}		
	}
	
	/**
	 * Gets the lista menus.
	 *
	 * @return the lista menus
	 */
	public List<MenuVO> getListaMenus() {
		return this.usuarioAutenticado.getListaMenus();
	}

	/**
	 * Gets the lista admin menus.
	 *
	 * @return the lista admin menus
	 */
	public List<MenuVO> getListaAdminMenus() {
		return this.usuarioAutenticado.getListaAdminMenus();
	}

	/**
	 * Gets the pagina.
	 *
	 * @param idMenu
	 *            the id menu
	 * @return the pagina
	 */
	public PaginaVO getPagina(Long idMenu) {
		return this.usuarioAutenticado.getPaginaByMenu(idMenu);
	}

	/**
	 * Autenticar via banco.
	 *
	 * @param login the login
	 * @param senha the senha
	 * @return true, if successful
	 */
	private boolean autenticarViaBanco(String login, String senha) {
		if (!aplicacaoUtil.isDebugMode()) {
			
			String csenha = DigestUtils.shaHex(senha);
			
			if (!login.isEmpty() && !csenha.isEmpty()) {
				usuarioLogado = admUsuarioBC.login(login, csenha);
				return (usuarioLogado!=null);
			}
		} else {
			return true;
		}
		return false;
	}
	
	/**
	 * Autenticar.
	 *
	 * @param login
	 *            the login
	 * @param senha
	 *            the senha
	 * @return true, if successful
	 */
	private boolean autenticarViaLDAP(String login, String senha) {
		if (!aplicacaoUtil.isDebugMode()) {
			if (!login.isEmpty() && !senha.isEmpty()) {
				LdapConfig config = new LdapConfig();
				log.info(config.toString());
				ldapUtil.configurar(config);
				ldapUtil.setListaLdapAtributo(ldapUtil.getAtributos(login));
				
				return ldapUtil.login(login, senha);
			}
		} else {
			return true;
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseViewController#getUsuarioAutenticado()
	 */
	public UsuarioAutenticadoVO getUsuarioAutenticado() {
		return usuarioAutenticado;
	}
	
}
