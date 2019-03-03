/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admLogColuna;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import br.com.hfsframework.admin.business.AdmLogColunaBC;
import br.com.hfsframework.admin.model.AdmLogColuna;
import br.com.hfsframework.base.BaseViewCadastro;
import br.com.hfsframework.base.IBaseViewCadastro;
import br.com.hfsframework.security.Pages;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

/**
 * The Class AdmLogColunaMB.
 */
@Named
@ViewAccessScoped
@TratamentoErrosEsperados
public class AdmLogColunaMB extends
		BaseViewCadastro<AdmLogColuna, String, AdmLogColunaBC, 
		Pages.Secure.ListarAdmLogColuna, Pages.Secure.EditarAdmLogColuna>
		implements IBaseViewCadastro<AdmLogColuna> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new adm log coluna MB.
	 */
	public AdmLogColunaMB() {
		super(Pages.Secure.ListarAdmLogColuna.class, Pages.Secure.EditarAdmLogColuna.class);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#init()
	 */
	@PostConstruct
	public void init() {
		atualizaListaDataTable();
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#onIncluir()
	 */
	@Override
	public String onIncluir() {
		return super.onIncluir(new AdmLogColuna());
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#salvar()
	 */
	@Override
	public String salvar() {
		return super.salvar(getBean().getNome());
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseViewCadastro#excluir(java.lang.Object)
	 */
	@Override
	public void excluir(AdmLogColuna entidade) {
		super.excluir(entidade);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#preProcessPDF(java.lang.Object)
	 */
	@Override
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		super.preProcessPDF(document, "AdmLogColuna");
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getBean()
	 */
	@Override
	public AdmLogColuna getBean() {
		return super.getEntidade();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#setBean(java.lang.Object)
	 */
	@Override
	public void setBean(AdmLogColuna entidade) {
		super.setEntidade(entidade);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getListaBean()
	 */
	@Override
	public List<AdmLogColuna> getListaBean() {
		return super.getListaEntidade();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#setListaBean(java.util.List)
	 */
	@Override
	public void setListaBean(List<AdmLogColuna> listaEntidade) {
		super.setListaEntidade(listaEntidade);		
	}

}
