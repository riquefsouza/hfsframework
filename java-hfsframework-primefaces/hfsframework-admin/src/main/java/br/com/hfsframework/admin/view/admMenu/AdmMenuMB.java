/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import br.com.hfsframework.admin.business.AdmPaginaBC;
import br.com.hfsframework.admin.business.AdmMenuBC;
import br.com.hfsframework.admin.model.AdmPagina;
import br.com.hfsframework.admin.model.AdmMenu;
import br.com.hfsframework.base.BaseViewConsulta;
import br.com.hfsframework.base.IBaseViewConsulta;
import br.com.hfsframework.security.Pages;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

/**
 * The Class AdmMenuMB.
 */
@Named
@ViewAccessScoped
@TratamentoErrosEsperados
public class AdmMenuMB
		extends BaseViewConsulta<AdmMenu, Long, AdmMenuBC, Pages.Secure.ListarAdmMenu>
		implements IBaseViewConsulta<AdmMenu> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The adm pagina BC. */
	@Inject
	private AdmPaginaBC admPaginaBC;

	/** The menu root. */
	private TreeNode menuRoot;

	/** The menu selecionado. */
	private TreeNode menuSelecionado;

	/** The novo item menu. */
	private AdmMenu novoItemMenu;
	
	/** The lista adm pagina. */
	private List<AdmPagina> listaAdmPagina;

	/** The lista menus pai. */
	private List<AdmMenu> listaMenusPai;
	
	/**
	 * Instantiates a new adm menu MB.
	 */
	public AdmMenuMB() {
		super(Pages.Secure.ListarAdmMenu.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hfsframework.base.IBaseViewCadastro#init()
	 */
	@PostConstruct
	public void init() {
		//atualizaListaDataTable();

		atualizarArvoreMenus();
		initListaPagina();
		initListaMenusPai();
		this.novoItemMenu = new AdmMenu();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hfsframework.base.BaseViewCadastro#excluir(java.lang.
	 * Object)
	 */
	public void excluir(AdmMenu entidade) {
		// super.excluir(entidade);
		if (this.menuSelecionado == null) {
			gerarMensagemErro("Favor selecionar um item de menu para continuar.");
			return;
		}
		AdmMenu m = (AdmMenu) this.menuSelecionado.getData();
		this.getBusinessController().apagar(m);
		atualizarArvoreMenus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.hfsframework.base.IBaseViewCadastro#preProcessPDF(java.lang
	 * .Object)
	 */
	@Override
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		super.preProcessPDF(document, "AdmMenu");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getBean()
	 */
	@Override
	public AdmMenu getBean() {
		return super.getEntidade();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hfsframework.base.IBaseViewCadastro#setBean(java.lang.
	 * Object)
	 */
	@Override
	public void setBean(AdmMenu entidade) {
		super.setEntidade(entidade);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getListaBean()
	 */
	@Override
	public List<AdmMenu> getListaBean() {
		return super.getListaEntidade();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.hfsframework.base.IBaseViewCadastro#setListaBean(java.util.
	 * List)
	 */
	@Override
	public void setListaBean(List<AdmMenu> listaEntidade) {
		super.setListaEntidade(listaEntidade);
	}

	/**
	 * Pega o the menu root.
	 *
	 * @return o the menu root
	 */
	public TreeNode getMenuRoot() {
		return menuRoot;
	}

	/**
	 * Atribui o the menu root.
	 *
	 * @param menuRoot
	 *            o novo the menu root
	 */
	public void setMenuRoot(TreeNode menuRoot) {
		this.menuRoot = menuRoot;
	}

	/**
	 * Pega o the menu selecionado.
	 *
	 * @return o the menu selecionado
	 */
	public TreeNode getMenuSelecionado() {
		return menuSelecionado;
	}

	/**
	 * Atribui o the menu selecionado.
	 *
	 * @param menuSelecionado
	 *            o novo the menu selecionado
	 */
	public void setMenuSelecionado(TreeNode menuSelecionado) {
		this.menuSelecionado = menuSelecionado;
	}

	/**
	 * Pega o the novo item menu.
	 *
	 * @return o the novo item menu
	 */
	public AdmMenu getNovoItemMenu() {
		return novoItemMenu;
	}

	/**
	 * Atribui o the novo item menu.
	 *
	 * @param novoItemMenu
	 *            o novo the novo item menu
	 */
	public void setNovoItemMenu(AdmMenu novoItemMenu) {
		this.novoItemMenu = novoItemMenu;
	}

	/**
	 * Pega o the lista adm pagina.
	 *
	 * @return o the lista adm pagina
	 */
	public List<AdmPagina> getListaAdmPagina() {
		return listaAdmPagina;
	}

	/**
	 * Atribui o the lista adm pagina.
	 *
	 * @param listaAdmPagina
	 *            o novo the lista adm pagina
	 */
	public void setListaAdmPagina(List<AdmPagina> listaAdmPagina) {
		this.listaAdmPagina = listaAdmPagina;
	}

	/**
	 * Pega o the lista menus pai.
	 *
	 * @return o the lista menus pai
	 */
	public List<AdmMenu> getListaMenusPai() {
		return listaMenusPai;
	}

	/**
	 * Atribui o the lista menus pai.
	 *
	 * @param listaMenusPai
	 *            o novo the lista menus pai
	 */
	public void setListaMenusPai(List<AdmMenu> listaMenusPai) {
		this.listaMenusPai = listaMenusPai;
	}

	/**
	 * Inicia o lista menus pai.
	 */
	private void initListaMenusPai() {
		this.listaMenusPai = new ArrayList<AdmMenu>();
		List<AdmMenu> registrosCadastrados = this.getBusinessController().findAll();
		for (AdmMenu menu : registrosCadastrados) {
			if ((menu.getAdmSubMenus() != null) && (menu.getAdmPagina() == null)) {
				this.listaMenusPai.add(menu);
			}
		}
	}

	/**
	 * Inicia o lista pagina.
	 */
	private void initListaPagina() {
		this.listaAdmPagina = admPaginaBC.findAll();
	}

	/**
	 * Atualizar arvore menus.
	 */
	public void atualizarArvoreMenus() {
		List<AdmMenu> listaMenus = this.getBusinessController().getRepositorio().findMenuRaiz();

		AdmMenu menu = new AdmMenu();
		menu.setDescricao("Menu do Sistema");
		this.menuRoot = new DefaultTreeNode(menu, null);
		for (AdmMenu itemMenu : listaMenus) {
			TreeNode m = new DefaultTreeNode(itemMenu, this.menuRoot);
			montaSubMenu(itemMenu, m);
		}
	}

	/**
	 * Monta sub menu.
	 *
	 * @param menu
	 *            the menu
	 * @param pMenu
	 *            the menu
	 * @return the list
	 */
	private List<TreeNode> montaSubMenu(AdmMenu menu, TreeNode pMenu) {
		List<TreeNode> lstSubMenu = new ArrayList<TreeNode>();
		if (menu.getAdmSubMenus() != null) {
			for (AdmMenu subMenu : menu.getAdmSubMenus()) {
				if (subMenu.isSubMenu()) {
					TreeNode m = new DefaultTreeNode(subMenu, pMenu);
					montaSubMenu(subMenu, m);
				} else {
					new DefaultTreeNode(subMenu, pMenu);
				}
			}
		}
		return lstSubMenu;
	}

	/**
	 * Sugerir nome item menu.
	 */
	public void sugerirNomeItemMenu() {
		if ((getNovoItemMenu().getAdmPagina() != null) && (getNovoItemMenu().getDescricao() == null)) {
			getNovoItemMenu().setDescricao(getNovoItemMenu().getAdmPagina().getDescricao());
		}
	}

	/**
	 * On drag drop.
	 *
	 * @param event
	 *            the event
	 */
	public void onDragDrop(TreeDragDropEvent event) {
		TreeNode dragNode = event.getDragNode();
		TreeNode dropNode = event.getDropNode();
		int dropIndex = event.getDropIndex();

		AdmMenu menuMover = (AdmMenu) dragNode.getData();
		AdmMenu menuPai = (AdmMenu) dropNode.getData();

		int ordem = 1;
		for (TreeNode tn : dropNode.getChildren()) {
			AdmMenu menu = (AdmMenu) tn.getData();
			menu.setOrdem(Integer.valueOf(ordem));
			ordem++;
		}
		ordem = 1;
		menuMover.setOrdem(Integer.valueOf(dropIndex + 1));
		if ((menuMover.getAdmMenuPai() != null) && (!menuMover.getAdmMenuPai().equals(menuPai))) {
			for (AdmMenu menu : menuMover.getAdmMenuPai().getAdmSubMenus()) {
				menu.setOrdem(Integer.valueOf(ordem));
				ordem++;
			}
		}
		if (menuPai.getId() == null) {
			menuPai = null;
		}
		this.getBusinessController().salvarOuAtualizarDragReordenando(menuMover.getAdmMenuPai(), menuPai, menuMover);

		atualizarArvoreMenus();
	}

	/**
	 * Gets the nome recursivo.
	 *
	 * @param m
	 *            the m
	 * @return the nome recursivo
	 */
	public static String getNomeRecursivo(AdmMenu m) {
		return m.getAdmPagina() == null ? m.getDescricao()
				: m.getAdmMenuPai() != null ? getNomeRecursivo(m.getAdmMenuPai()) + " -> " + m.getDescricao() : "";
	}

	/**
	 * Listener menu selecionado.
	 *
	 * @param event
	 *            the event
	 */
	public void listenerMenuSelecionado(NodeSelectEvent event) {
		this.menuSelecionado = event.getTreeNode();
	}
	
	/*
	 * Gets the dual list vw adm funcionario.
	 *
	 * @return the dual list vw adm funcionario
	 *
	public DualListModel<AdmFuncionarioDTO> getDualListVwAdmFuncionario() {
		return dualListVwAdmFuncionario;
	}
		
	**
	 * Sets the dual list vw adm funcionario.
	 *
	 * @param dualListVwAdmFuncionario
	 *            the new dual list vw adm funcionario
	 *
	public void setDualListVwAdmFuncionario(DualListModel<AdmFuncionarioDTO> dualListVwAdmFuncionario) {
		this.dualListVwAdmFuncionario = dualListVwAdmFuncionario;
	}
	*/
	
	public void onIncluirItemMenu() {
		this.menuSelecionado = null;
		this.novoItemMenu = new AdmMenu();
		atualizarArvoreMenus();
		initListaMenusPai();
	}

	public void onEditarItemMenu() {
		atualizarArvoreMenus();
		initListaMenusPai();
		if (this.menuSelecionado == null) {
			gerarMensagemErro("Favor selecionar um item de menu para proceder esta ação.");
			context.validationFailed();
		} else {
			this.novoItemMenu = ((AdmMenu) this.getBusinessController()
					.load(((AdmMenu) this.menuSelecionado.getData()).getId().longValue()));
		}
	}

	public String salvarItemMenu() {
		int ordem;
		
		if ((this.novoItemMenu.getDescricao() == null) || (this.novoItemMenu.getDescricao().trim().isEmpty())) {
			gerarMensagemErro("Erro. Nome do Item de Menu. Campo Obrigatório.");
			context.validationFailed();
		} else {
			/*
			if (getBusinessController().existeNovo(this.novoItemMenu.getDescricao())){
				gerarMensagemErro("Nome do Item de Menu já existe.");
				return null;					
			}
			*/
			try {
				if (this.novoItemMenu.getAdmMenuPai()!=null)
					ordem = this.novoItemMenu.getAdmMenuPai().getAdmSubMenus().size() + 2;
				else
					ordem = 1;
				
				this.novoItemMenu.setOrdem(ordem);
				if (this.novoItemMenu.getAdmPagina()!=null){
					this.novoItemMenu.setIdPagina(this.novoItemMenu.getAdmPagina().getId());
				} else {
					this.novoItemMenu.setIdPagina(null);
				}
				this.getBusinessController().salvarOuAtualizar(this.novoItemMenu);
				this.novoItemMenu = new AdmMenu();
				atualizarArvoreMenus();
			} catch (Exception e) {
				gerarMensagemErro(e, ERRO_SALVAR);
				return null;
			}			
		}
		return getPaginaListar();
	}

	public void excluirItemMenu() {
		if (this.menuSelecionado == null) {
			gerarMensagemErro("Favor selecionar um item de menu para proceder esta ação.");
			return;
		}
		try {
			AdmMenu m = (AdmMenu) this.menuSelecionado.getData();
			this.getBusinessController().apagar(m);
			atualizarArvoreMenus();
		} catch (Exception e) {
			gerarMensagemErro(e, ERRO_DELETE);
			return;
		}
	}

}
