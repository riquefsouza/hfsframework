/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admPerfil;

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

import br.com.hfsframework.admin.business.AdmPaginaBC;
import br.com.hfsframework.admin.business.AdmPerfilBC;
import br.com.hfsframework.admin.business.AdmUsuarioBC;
import br.com.hfsframework.admin.model.AdmPagina;
import br.com.hfsframework.admin.model.AdmPerfil;
import br.com.hfsframework.admin.model.AdmUsuario;
import br.com.hfsframework.base.BaseViewCadastro;
import br.com.hfsframework.base.IBaseViewCadastro;
import br.com.hfsframework.security.Pages;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

/**
 * The Class AdmPerfilMB.
 */
@Named
@ViewAccessScoped
@TratamentoErrosEsperados
public class AdmPerfilMB extends
		BaseViewCadastro<AdmPerfil, Long, AdmPerfilBC, 
		Pages.Secure.ListarAdmPerfil, Pages.Secure.EditarAdmPerfil>
		implements IBaseViewCadastro<AdmPerfil> {
	
	/** The vw adm usuario BC. */
	@Inject
	private AdmUsuarioBC admUsuarioBC;

	/** The dual list adm usuario. */
	private DualListModel<AdmUsuario> dualListAdmUsuario;

	/** The lista adm usuario. */
	private List<AdmUsuario> listaAdmUsuario;

	/** The adm pagina BC. */
	@Inject
	private AdmPaginaBC admPaginaBC;

	/** The dual list adm pagina. */
	private DualListModel<AdmPagina> dualListAdmPagina;

	/** The lista adm pagina. */
	private List<AdmPagina> listaAdmPagina;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new adm perfil MB.
	 */
	public AdmPerfilMB() {
		super(Pages.Secure.ListarAdmPerfil.class, Pages.Secure.EditarAdmPerfil.class);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#init()
	 */
	@PostConstruct
	public void init() {
		atualizaListaDataTable();
	}
	
	/**
	 * Carregar adm paginas.
	 */
	private void carregarAdmPaginas() {
		List<AdmPagina> listaAdmPaginaSelecionado = this.getBean().getId() == null ? new ArrayList<AdmPagina>()
				: this.getBusinessController().getRepositorio().findPaginasPorPerfil(this.getBean());
		this.listaAdmPagina = admPaginaBC.findAll();
		this.listaAdmPagina.removeAll(listaAdmPaginaSelecionado);
		this.dualListAdmPagina = new DualListModel<AdmPagina>(this.listaAdmPagina, listaAdmPaginaSelecionado);
	}

	/**
	 * Carregar adm usuarios.
	 */
	private void carregarAdmUsuarios() {
		List<AdmUsuario> listaAdmUsuarioSelecionado = this.getBean().getId() == null ? new ArrayList<AdmUsuario>()
				: this.getBusinessController().findUsuariosPorPerfil(this.getBean());
		this.listaAdmUsuario = admUsuarioBC.findAll();
		this.listaAdmUsuario.removeAll(listaAdmUsuarioSelecionado);
		this.dualListAdmUsuario = new DualListModel<AdmUsuario>(this.listaAdmUsuario, listaAdmUsuarioSelecionado);
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#onIncluir()
	 */
	@Override
	public String onIncluir() {
		this.listaAdmPagina = admPaginaBC.findAll();
	    this.dualListAdmPagina = new DualListModel<AdmPagina>(this.listaAdmPagina, new ArrayList<AdmPagina>());

		this.listaAdmUsuario = admUsuarioBC.findAll();
	    this.dualListAdmUsuario = new DualListModel<AdmUsuario>(this.listaAdmUsuario, new ArrayList<AdmUsuario>());

		return super.onIncluir(new AdmPerfil());
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseViewCadastro#onEditar(java.lang.Object)
	 */
	@Override
	public String onEditar(AdmPerfil entidade) {
		String retorno = super.onEditar(entidade);
		if (entidade != null) {
			carregarAdmPaginas();
			carregarAdmUsuarios();
		}
		return retorno;
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#salvar()
	 */
	@Override
	public String salvar() {	
		getBean().setAdmPaginas(this.dualListAdmPagina.getTarget());
		getBean().setAdmUsuarios(this.dualListAdmUsuario.getTarget());			
		return super.salvar(getBean().getId(), getBean().getDescricao());
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseViewCadastro#excluir(java.lang.Object)
	 */
	@Override
	public void excluir(AdmPerfil entidade) {
		super.excluir(entidade);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#preProcessPDF(java.lang.Object)
	 */
	@Override
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		super.preProcessPDF(document, "AdmPerfil");
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getBean()
	 */
	@Override
	public AdmPerfil getBean() {
		return super.getEntidade();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#setBean(java.lang.Object)
	 */
	@Override
	public void setBean(AdmPerfil entidade) {
		super.setEntidade(entidade);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getListaBean()
	 */
	@Override
	public List<AdmPerfil> getListaBean() {
		return super.getListaEntidade();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#setListaBean(java.util.List)
	 */
	@Override
	public void setListaBean(List<AdmPerfil> listaEntidade) {
		super.setListaEntidade(listaEntidade);		
	}

	/**
	 * Gets the dual list adm usuario.
	 *
	 * @return the dual list adm usuario
	 */
	public DualListModel<AdmUsuario> getDualListAdmUsuario() {
		return dualListAdmUsuario;
	}
		
	/**
	 * Sets the dual list adm usuario.
	 *
	 * @param dualListAdmUsuario
	 *            the new dual list adm usuario
	 */
	public void setDualListAdmUsuario(DualListModel<AdmUsuario> dualListAdmUsuario) {
		this.dualListAdmUsuario = dualListAdmUsuario;
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
	
}
