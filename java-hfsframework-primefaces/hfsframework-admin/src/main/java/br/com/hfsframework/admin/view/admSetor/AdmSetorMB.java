/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admSetor;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import br.com.hfsframework.admin.business.AdmSetorBC;
import br.com.hfsframework.admin.model.AdmSetor;
import br.com.hfsframework.base.BaseViewCadastro;
import br.com.hfsframework.base.IBaseViewCadastro;
import br.com.hfsframework.security.Pages;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

/**
 * The Class AdmSetorMB.
 */
@Named
@ViewAccessScoped
@TratamentoErrosEsperados
public class AdmSetorMB extends
		BaseViewCadastro<AdmSetor, String, AdmSetorBC, 
		Pages.Secure.ListarAdmSetor, Pages.Secure.EditarAdmSetor>
		implements IBaseViewCadastro<AdmSetor> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new vw adm setor MB.
	 */
	public AdmSetorMB() {
		super(Pages.Secure.ListarAdmSetor.class, Pages.Secure.EditarAdmSetor.class);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#init()
	 */
	@PostConstruct
	public void init() {
		atualizaListaDataTable();
	}
	
	@Override
	public String onIncluir() {
		return super.onIncluir(new AdmSetor());
	}

	@Override
	public String salvar() {
		return super.salvar(getBean().getId(), getBean().getNomeSetor());
	}

	@Override
	public void excluir(AdmSetor entidade) {
		super.excluir(entidade);
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#preProcessPDF(java.lang.Object)
	 */
	@Override
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		super.preProcessPDF(document, "AdmSetor");
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getBean()
	 */
	@Override
	public AdmSetor getBean() {
		return super.getEntidade();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#setBean(java.lang.Object)
	 */
	@Override
	public void setBean(AdmSetor entidade) {
		super.setEntidade(entidade);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getListaBean()
	 */
	@Override
	public List<AdmSetor> getListaBean() {
		return super.getListaEntidade();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#setListaBean(java.util.List)
	 */
	@Override
	public void setListaBean(List<AdmSetor> listaEntidade) {
		super.setListaEntidade(listaEntidade);		
	}

}
