/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admParametro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import br.com.hfsframework.admin.AdmParametroBC;
import br.com.hfsframework.admin.data.AdmParametroCategoriaRepository;
import br.com.hfsframework.admin.model.AdmParametro;
import br.com.hfsframework.admin.model.AdmParametroCategoria;
import br.com.hfsframework.base.BaseViewCadastro;
import br.com.hfsframework.base.IBaseViewCadastro;
import br.com.hfsframework.security.Pages;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

/**
 * The Class AdmParametroMB.
 */
@Named
@ViewAccessScoped
@TratamentoErrosEsperados
public class AdmParametroMB extends
		BaseViewCadastro<AdmParametro, Long, AdmParametroBC, Pages.Secure.ListarAdmParametro, Pages.Secure.EditarAdmParametro>
		implements IBaseViewCadastro<AdmParametro> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The adm parametro categoria repository. */
	@Inject
	private AdmParametroCategoriaRepository admParametroCategoriaRepository;

	/** The lista adm parametro categoria. */
	private List<AdmParametroCategoria> listaAdmParametroCategoria;

	/**
	 * Instantiates a new adm parametro MB.
	 */
	public AdmParametroMB() {
		super(Pages.Secure.ListarAdmParametro.class, Pages.Secure.EditarAdmParametro.class);

		listaAdmParametroCategoria = new ArrayList<AdmParametroCategoria>();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#init()
	 */
	@PostConstruct
	public void init() {
		listaAdmParametroCategoria = admParametroCategoriaRepository.findAll();
		atualizaListaDataTable();

		if (getBean().getAdmParametroCategoria() != null && listaAdmParametroCategoria.size() > 0) {
			getBean().getAdmParametroCategoria().setId(listaAdmParametroCategoria.get(0).getId());
			selectAdmParametroCategoria();
		}
	}

	/**
	 * Select adm parametro categoria.
	 */
	public void selectAdmParametroCategoria() {
		AdmParametroCategoria admParametroCategoria = admParametroCategoriaRepository
				.findBy(getBean().getAdmParametroCategoria().getId());
		getBean().setAdmParametroCategoria(admParametroCategoria);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#onIncluir()
	 */
	@Override
	public String onIncluir() {
		return super.onIncluir(new AdmParametro());
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#salvar()
	 */
	@Override
	public String salvar() {
		getBean().setIdAdmParametroCategoria(getBean().getAdmParametroCategoria().getId());
		return super.salvar(getBean().getId(), getBean().getDescricao());
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseViewCadastro#excluir(java.lang.Object)
	 */
	@Override
	public void excluir(AdmParametro entidade) {
		super.excluir(entidade);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#preProcessPDF(java.lang.Object)
	 */
	@Override
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		super.preProcessPDF(document, "AdmParametro");
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getBean()
	 */
	@Override
	public AdmParametro getBean() {
		return super.getEntidade();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#setBean(java.lang.Object)
	 */
	@Override
	public void setBean(AdmParametro entidade) {
		super.setEntidade(entidade);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getListaBean()
	 */
	@Override
	public List<AdmParametro> getListaBean() {
		return super.getListaEntidade();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#setListaBean(java.util.List)
	 */
	@Override
	public void setListaBean(List<AdmParametro> listaEntidade) {
		super.setListaEntidade(listaEntidade);
	}

	/**
	 * Gets the lista adm parametro categoria.
	 *
	 * @return the lista adm parametro categoria
	 */
	public List<AdmParametroCategoria> getListaAdmParametroCategoria() {
		return listaAdmParametroCategoria;
	}

	/**
	 * Sets the lista adm parametro categoria.
	 *
	 * @param listaAdmParametroCategoria
	 *            the new lista adm parametro categoria
	 */
	public void setListaAdmParametroCategoria(List<AdmParametroCategoria> listaAdmParametroCategoria) {
		this.listaAdmParametroCategoria = listaAdmParametroCategoria;
	}

}
