/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admPagina;

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

import br.com.hfsframework.admin.AdmFuncionalidadeBC;
import br.com.hfsframework.admin.AdmPaginaBC;
import br.com.hfsframework.admin.AdmPerfilBC;
import br.com.hfsframework.admin.model.AdmFuncionalidade;
import br.com.hfsframework.admin.model.AdmPagina;
import br.com.hfsframework.admin.model.AdmPerfil;
import br.com.hfsframework.base.BaseViewCadastro;
import br.com.hfsframework.base.IBaseViewCadastro;
import br.com.hfsframework.security.Pages;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

/**
 * The Class AdmPaginaMB.
 */
@Named
@ViewAccessScoped
@TratamentoErrosEsperados
public class AdmPaginaMB extends
		BaseViewCadastro<AdmPagina, Long, AdmPaginaBC, Pages.Secure.ListarAdmPagina, Pages.Secure.EditarAdmPagina>
		implements IBaseViewCadastro<AdmPagina> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Inject
	private AdmPerfilBC admPerfilBC;

	private DualListModel<AdmPerfil> dualListAdmPerfil;

	private List<AdmPerfil> listaAdmPerfil;
	
	@Inject
	private AdmFuncionalidadeBC admFuncionalidadeBC;

	private DualListModel<AdmFuncionalidade> dualListAdmFuncionalidade;

	private List<AdmFuncionalidade> listaAdmFuncionalidade;
	

	/**
	 * Instantiates a new adm pagina MB.
	 */
	public AdmPaginaMB() {
		super(Pages.Secure.ListarAdmPagina.class, Pages.Secure.EditarAdmPagina.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hfsframework.base.IBaseViewCadastro#init()
	 */
	@PostConstruct
	public void init() {
		atualizaListaDataTable();
	}

	private void carregarAdmPerfis() {
		List<AdmPerfil> listaAdmPerfilSelecionado = this.getBean().getId() == null ? new ArrayList<AdmPerfil>()
				: this.getBusinessController().getRepositorio().findPerfisPorPagina(this.getBean());
		this.listaAdmPerfil = admPerfilBC.findAll();
		this.listaAdmPerfil.removeAll(listaAdmPerfilSelecionado);
		this.dualListAdmPerfil = new DualListModel<AdmPerfil>(this.listaAdmPerfil, listaAdmPerfilSelecionado);
	}

	private void carregarAdmFuncionalidades() {
		List<AdmFuncionalidade> listaAdmFuncionalidadeSelecionado = this.getBean().getId() == null ? new ArrayList<AdmFuncionalidade>()
				: this.getBusinessController().getRepositorio().findFuncionalidadesPorPagina(this.getBean());
		this.listaAdmFuncionalidade = admFuncionalidadeBC.findAll();
		this.listaAdmFuncionalidade.removeAll(listaAdmFuncionalidadeSelecionado);
		this.dualListAdmFuncionalidade = new DualListModel<AdmFuncionalidade>(this.listaAdmFuncionalidade, listaAdmFuncionalidadeSelecionado);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hfsframework.base.IBaseViewCadastro#onIncluir()
	 */
	@Override
	public String onIncluir() {
		this.listaAdmPerfil = admPerfilBC.findAll();
	    this.dualListAdmPerfil = new DualListModel<AdmPerfil>(this.listaAdmPerfil, new ArrayList<AdmPerfil>());
		
		this.listaAdmFuncionalidade = admFuncionalidadeBC.findAll();
	    this.dualListAdmFuncionalidade = new DualListModel<AdmFuncionalidade>(this.listaAdmFuncionalidade, new ArrayList<AdmFuncionalidade>());
	    
		return super.onIncluir(new AdmPagina());
	}

	@Override
	public String onEditar(AdmPagina entidade) {
		String retorno = super.onEditar(entidade);
		if (entidade != null) {
			carregarAdmPerfis();
			carregarAdmFuncionalidades();
		}
		return retorno;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hfsframework.base.IBaseViewCadastro#salvar()
	 */
	@Override
	public String salvar() {
		getBean().setAdmPerfils(this.dualListAdmPerfil.getTarget());
		getBean().setAdmFuncionalidades(this.dualListAdmFuncionalidade.getTarget());
		return super.salvar(getBean().getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hfsframework.base.BaseViewCadastro#excluir(java.lang.
	 * Object)
	 */
	@Override
	public void excluir(AdmPagina entidade) {
		super.excluir(entidade);
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
		super.preProcessPDF(document, "AdmPagina");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getBean()
	 */
	@Override
	public AdmPagina getBean() {
		return super.getEntidade();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hfsframework.base.IBaseViewCadastro#setBean(java.lang.
	 * Object)
	 */
	@Override
	public void setBean(AdmPagina entidade) {
		super.setEntidade(entidade);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getListaBean()
	 */
	@Override
	public List<AdmPagina> getListaBean() {
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
	public void setListaBean(List<AdmPagina> listaEntidade) {
		super.setListaEntidade(listaEntidade);
	}

	public DualListModel<AdmPerfil> getDualListAdmPerfil() {
		return dualListAdmPerfil;
	}
		
	public void setDualListAdmPerfil(DualListModel<AdmPerfil> dualListAdmPerfil) {
		this.dualListAdmPerfil = dualListAdmPerfil;
	}

	public DualListModel<AdmFuncionalidade> getDualListAdmFuncionalidade() {
		return dualListAdmFuncionalidade;
	}
		
	public void setDualListAdmFuncionalidade(DualListModel<AdmFuncionalidade> dualListAdmFuncionalidade) {
		this.dualListAdmFuncionalidade = dualListAdmFuncionalidade;
	}
	
}
