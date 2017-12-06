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

import br.com.hfsframework.admin.AdmCargoBC;
import br.com.hfsframework.admin.AdmFuncionalidadeBC;
import br.com.hfsframework.admin.AdmFuncionarioBC;
import br.com.hfsframework.admin.AdmPaginaBC;
import br.com.hfsframework.admin.AdmPerfilBC;
import br.com.hfsframework.admin.model.AdmCargo;
import br.com.hfsframework.admin.model.AdmFuncionalidade;
import br.com.hfsframework.admin.model.AdmFuncionario;
import br.com.hfsframework.admin.model.AdmPagina;
import br.com.hfsframework.admin.model.AdmPerfil;
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

	/** The adm cargo BC. */
	@Inject
	private AdmCargoBC admCargoBC;

	/** The dual list adm cargo. */
	private DualListModel<AdmCargo> dualListAdmCargo;

	/** The lista adm cargo. */
	private List<AdmCargo> listaAdmCargo;
		
	/** The vw adm funcionario BC. */
	@Inject
	private AdmFuncionarioBC admFuncionarioBC;

	/** The dual list adm funcionario. */
	private DualListModel<AdmFuncionario> dualListAdmFuncionario;

	/** The lista adm funcionario. */
	private List<AdmFuncionario> listaAdmFuncionario;
		
	/** The adm funcionalidade BC. */
	@Inject
	private AdmFuncionalidadeBC admFuncionalidadeBC;

	/** The dual list adm funcionalidade. */
	private DualListModel<AdmFuncionalidade> dualListAdmFuncionalidade;

	/** The lista adm funcionalidade. */
	private List<AdmFuncionalidade> listaAdmFuncionalidade;
	
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
	 * Carregar adm funcionarios.
	 */
	private void carregarAdmFuncionarios() {
		List<AdmFuncionario> listaAdmFuncionarioSelecionado = this.getBean().getId() == null ? new ArrayList<AdmFuncionario>()
				: this.getBusinessController().findFuncionariosPorPerfil(this.getBean());
		this.listaAdmFuncionario = admFuncionarioBC.findAll();
		this.listaAdmFuncionario.removeAll(listaAdmFuncionarioSelecionado);
		this.dualListAdmFuncionario = new DualListModel<AdmFuncionario>(this.listaAdmFuncionario, listaAdmFuncionarioSelecionado);
	}
	
	/**
	 * Carregar adm cargos.
	 */
	private void carregarAdmCargos() {
		List<AdmCargo> listaAdmCargoSelecionado = this.getBean().getId() == null ? new ArrayList<AdmCargo>()
				: this.getBusinessController().findCargosPorPerfil(this.getBean());
		this.listaAdmCargo = admCargoBC.findAll();
		this.listaAdmCargo.removeAll(listaAdmCargoSelecionado);
		this.dualListAdmCargo = new DualListModel<AdmCargo>(this.listaAdmCargo, listaAdmCargoSelecionado);
	}
		
	/**
	 * Carregar adm funcionalidades.
	 */
	private void carregarAdmFuncionalidades() {
		List<AdmFuncionalidade> listaAdmFuncionalidadeSelecionado = this.getBean().getId() == null ? new ArrayList<AdmFuncionalidade>()
				: this.getBusinessController().getRepositorio().findFuncionalidadesPorPerfil(this.getBean());
		this.listaAdmFuncionalidade = admFuncionalidadeBC.findAll();
		this.listaAdmFuncionalidade.removeAll(listaAdmFuncionalidadeSelecionado);
		this.dualListAdmFuncionalidade = new DualListModel<AdmFuncionalidade>(this.listaAdmFuncionalidade, listaAdmFuncionalidadeSelecionado);
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#onIncluir()
	 */
	@Override
	public String onIncluir() {
		this.listaAdmPagina = admPaginaBC.findAll();
	    this.dualListAdmPagina = new DualListModel<AdmPagina>(this.listaAdmPagina, new ArrayList<AdmPagina>());

		this.listaAdmFuncionalidade = admFuncionalidadeBC.findAll();
	    this.dualListAdmFuncionalidade = new DualListModel<AdmFuncionalidade>(this.listaAdmFuncionalidade, new ArrayList<AdmFuncionalidade>());

		this.listaAdmFuncionario = admFuncionarioBC.findAll();
	    this.dualListAdmFuncionario = new DualListModel<AdmFuncionario>(this.listaAdmFuncionario, new ArrayList<AdmFuncionario>());
	    
		this.listaAdmCargo = admCargoBC.findAll();
		this.dualListAdmCargo = new DualListModel<AdmCargo>(this.listaAdmCargo, new ArrayList<AdmCargo>());

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
			carregarAdmFuncionalidades();
			carregarAdmFuncionarios();
			carregarAdmCargos();			
		}
		return retorno;
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#salvar()
	 */
	@Override
	public String salvar() {	
		getBean().setAdmPaginas(this.dualListAdmPagina.getTarget());
		getBean().setAdmFuncionalidades(this.dualListAdmFuncionalidade.getTarget());
		getBean().setAdmFuncionarios(this.dualListAdmFuncionario.getTarget());		
		getBean().setAdmCargos(this.dualListAdmCargo.getTarget());		
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
	 * Pega o the dual list adm cargo.
	 *
	 * @return o the dual list adm cargo
	 */
	public DualListModel<AdmCargo> getDualListAdmCargo() {
		return dualListAdmCargo;
	}

	/**
	 * Atribui o the dual list adm cargo.
	 *
	 * @param dualListAdmCargo
	 *            o novo the dual list adm cargo
	 */
	public void setDualListAdmCargo(DualListModel<AdmCargo> dualListAdmCargo) {
		this.dualListAdmCargo = dualListAdmCargo;
	}

	/**
	 * Gets the dual list adm funcionario.
	 *
	 * @return the dual list adm funcionario
	 */
	public DualListModel<AdmFuncionario> getDualListAdmFuncionario() {
		return dualListAdmFuncionario;
	}
		
	/**
	 * Sets the dual list adm funcionario.
	 *
	 * @param dualListAdmFuncionario
	 *            the new dual list adm funcionario
	 */
	public void setDualListAdmFuncionario(DualListModel<AdmFuncionario> dualListAdmFuncionario) {
		this.dualListAdmFuncionario = dualListAdmFuncionario;
	}
		
	/**
	 * Gets the dual list adm funcionalidade.
	 *
	 * @return the dual list adm funcionalidade
	 */
	public DualListModel<AdmFuncionalidade> getDualListAdmFuncionalidade() {
		return dualListAdmFuncionalidade;
	}
		
	/**
	 * Sets the dual list adm funcionalidade.
	 *
	 * @param dualListAdmFuncionalidade
	 *            the new dual list adm funcionalidade
	 */
	public void setDualListAdmFuncionalidade(DualListModel<AdmFuncionalidade> dualListAdmFuncionalidade) {
		this.dualListAdmFuncionalidade = dualListAdmFuncionalidade;
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
