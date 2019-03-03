/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admFuncionalidade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import org.primefaces.model.DualListModel;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import br.com.hfsframework.admin.business.AdmFuncionalidadeBC;
import br.com.hfsframework.admin.business.AdmPaginaBC;
import br.com.hfsframework.admin.business.AdmPerfilBC;
import br.com.hfsframework.admin.model.AdmFuncionalidade;
import br.com.hfsframework.admin.model.AdmPagina;
import br.com.hfsframework.admin.model.AdmPerfil;
import br.com.hfsframework.base.BaseViewCadastro;
import br.com.hfsframework.base.IBaseViewCadastro;
import br.com.hfsframework.security.Pages;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

/**
 * The Class AdmFuncionalidadeMB.
 */
@Named
@ViewAccessScoped
@TratamentoErrosEsperados
public class AdmFuncionalidadeMB extends
		BaseViewCadastro<AdmFuncionalidade, Long, AdmFuncionalidadeBC, Pages.Secure.ListarAdmFuncionalidade, Pages.Secure.EditarAdmFuncionalidade>
		implements IBaseViewCadastro<AdmFuncionalidade> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The adm perfil BC. */
	@Inject
	private AdmPerfilBC admPerfilBC;

	/** The dual list adm perfil. */
	private DualListModel<AdmPerfil> dualListAdmPerfil;

	/** The lista adm perfil. */
	private List<AdmPerfil> listaAdmPerfil;
	
	/** The adm pagina BC. */
	@Inject
	private AdmPaginaBC admPaginaBC;

	/** The dual list adm pagina. */
	private DualListModel<AdmPagina> dualListAdmPagina;

	/** The lista adm pagina. */
	private List<AdmPagina> listaAdmPagina;

	/** The lista adm pagina inicial. */
	private List<AdmPagina> listaAdmPaginaInicial;

	/* The adm menu BC. 
	@Inject
	private AdmMenuBC admMenuBC;

	** The dual list adm menu. *
	private DualListModel<AdmMenu> dualListAdmMenu;
	
	** The lista adm menu. *
	private List<AdmMenu> listaAdmMenu;
	*/

	/**
	 * Instantiates a new adm funcionalidade MB.
	 */
	public AdmFuncionalidadeMB() {
		super(Pages.Secure.ListarAdmFuncionalidade.class, Pages.Secure.EditarAdmFuncionalidade.class);
		
		listaAdmPaginaInicial = new ArrayList<AdmPagina>();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#init()
	 */
	@PostConstruct
	public void init() {
		listaAdmPaginaInicial = admPaginaBC.getRepositorio().findAll();
		atualizaListaDataTable();
		
		if (getBean().getAdmPaginaInicial() != null && listaAdmPaginaInicial.size() > 0) {
			getBean().getAdmPaginaInicial().setId(listaAdmPaginaInicial.get(0).getId());
			selectAdmPaginaInicial();
		}		
	}

	/**
	 * Select adm pagina inicial.
	 */
	public void selectAdmPaginaInicial() {
		AdmPagina AdmPaginaInicial = admPaginaBC.getRepositorio().findBy(getBean().getAdmPaginaInicial().getId());
		getBean().setAdmPaginaInicial(AdmPaginaInicial);
	}
	
	/**
	 * Carregar adm perfis.
	 */
	private void carregarAdmPerfis() {
		List<AdmPerfil> listaAdmPerfilSelecionado = this.getBean().getId() == null ? new ArrayList<AdmPerfil>()
				: this.getBusinessController().getRepositorio().findPerfisPorFuncionalidade(this.getBean());
		this.listaAdmPerfil = admPerfilBC.findAll();
		this.listaAdmPerfil.removeAll(listaAdmPerfilSelecionado);
		this.dualListAdmPerfil = new DualListModel<AdmPerfil>(this.listaAdmPerfil, listaAdmPerfilSelecionado);
	}

	/**
	 * Carregar adm paginas.
	 */
	private void carregarAdmPaginas() {
		List<AdmPagina> listaAdmPaginaSelecionado = this.getBean().getId() == null ? new ArrayList<AdmPagina>()
				: this.getBusinessController().getRepositorio().findPaginasPorFuncionalidade(this.getBean());
		this.listaAdmPagina = admPaginaBC.findAll();
		this.listaAdmPagina.removeAll(listaAdmPaginaSelecionado);
		this.dualListAdmPagina = new DualListModel<AdmPagina>(this.listaAdmPagina, listaAdmPaginaSelecionado);
	}

	/*
	 * Carregar adm menus.
	 *
	private void carregarAdmMenus() {
		List<AdmMenu> listaAdmMenuSelecionado = this.getBean().getId() == null ? new ArrayList<AdmMenu>()
				: this.getBusinessController().getRepositorio().findMenusPorFuncionalidade(this.getBean());
		this.listaAdmMenu = admMenuBC.findAll();
		this.listaAdmMenu.removeAll(listaAdmMenuSelecionado);
		this.dualListAdmMenu = new DualListModel<AdmMenu>(this.listaAdmMenu, listaAdmMenuSelecionado);
	}
	*/

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#onIncluir()
	 */
	@Override
	public String onIncluir() {
		this.listaAdmPerfil = admPerfilBC.findAll();
	    this.dualListAdmPerfil = new DualListModel<AdmPerfil>(this.listaAdmPerfil, new ArrayList<AdmPerfil>());

		this.listaAdmPagina = admPaginaBC.findAll();
	    this.dualListAdmPagina = new DualListModel<AdmPagina>(this.listaAdmPagina, new ArrayList<AdmPagina>());

		//this.listaAdmMenu = admMenuBC.findAll();
	    //this.dualListAdmMenu = new DualListModel<AdmMenu>(this.listaAdmMenu, new ArrayList<AdmMenu>());

		return super.onIncluir(new AdmFuncionalidade());
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseViewCadastro#onEditar(java.lang.Object)
	 */
	@Override
	public String onEditar(AdmFuncionalidade entidade) {
		String retorno = super.onEditar(entidade);
		if (entidade != null) {
			carregarAdmPerfis();
			carregarAdmPaginas();
			//carregarAdmMenus();
		}
		return retorno;
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#salvar()
	 */
	@Override
	public String salvar() {
		getBean().setAdmPerfils(this.dualListAdmPerfil.getTarget());	
		getBean().setAdmPaginas(this.dualListAdmPagina.getTarget());
		//getBean().setAdmMenus(this.dualListAdmMenu.getTarget());
		return super.salvar(getBean().getId(), getBean().getDescricao());
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseViewCadastro#excluir(java.lang.Object)
	 */
	@Override
	public void excluir(AdmFuncionalidade entidade) {
		super.excluir(entidade);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#preProcessPDF(java.lang.Object)
	 */
	@Override
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		super.preProcessPDF(document, "AdmFuncionalidade");
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getBean()
	 */
	@Override
	public AdmFuncionalidade getBean() {
		return super.getEntidade();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#setBean(java.lang.Object)
	 */
	@Override
	public void setBean(AdmFuncionalidade entidade) {
		super.setEntidade(entidade);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getListaBean()
	 */
	@Override
	public List<AdmFuncionalidade> getListaBean() {
		return super.getListaEntidade();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#setListaBean(java.util.List)
	 */
	@Override
	public void setListaBean(List<AdmFuncionalidade> listaEntidade) {
		super.setListaEntidade(listaEntidade);
	}

	/**
	 * Pega o the dual list adm perfil.
	 *
	 * @return o the dual list adm perfil
	 */
	public DualListModel<AdmPerfil> getDualListAdmPerfil() {
		return dualListAdmPerfil;
	}

	/**
	 * Atribui o the dual list adm perfil.
	 *
	 * @param dualListAdmPerfil
	 *            o novo the dual list adm perfil
	 */
	public void setDualListAdmPerfil(DualListModel<AdmPerfil> dualListAdmPerfil) {
		this.dualListAdmPerfil = dualListAdmPerfil;
	}

	/**
	 * Pega o the dual list adm pagina.
	 *
	 * @return o the dual list adm pagina
	 */
	public DualListModel<AdmPagina> getDualListAdmPagina() {
		return dualListAdmPagina;
	}
		
	/**
	 * Atribui o the dual list adm pagina.
	 *
	 * @param dualListAdmPagina
	 *            o novo the dual list adm pagina
	 */
	public void setDualListAdmPagina(DualListModel<AdmPagina> dualListAdmPagina) {
		this.dualListAdmPagina = dualListAdmPagina;
	}

	/**
	 * Pega o the lista adm pagina inicial.
	 *
	 * @return o the lista adm pagina inicial
	 */
	public List<AdmPagina> getListaAdmPaginaInicial() {
		return listaAdmPaginaInicial;
	}

	/**
	 * Atribui o the lista adm pagina inicial.
	 *
	 * @param listaAdmPaginaInicial
	 *            o novo the lista adm pagina inicial
	 */
	public void setListaAdmPaginaInicial(List<AdmPagina> listaAdmPaginaInicial) {
		this.listaAdmPaginaInicial = listaAdmPaginaInicial;
	}

	/*
	 * Pega o the dual list adm menu.
	 *
	 * @return o the dual list adm menu
	 *
	public DualListModel<AdmMenu> getDualListAdmMenu() {
		return dualListAdmMenu;
	}

	**
	 * Atribui o the dual list adm menu.
	 *
	 * @param dualListAdmMenu
	 *            o novo the dual list adm menu
	 *
	public void setDualListAdmMenu(DualListModel<AdmMenu> dualListAdmMenu) {
		this.dualListAdmMenu = dualListAdmMenu;
	}
	 */
}
