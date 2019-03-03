/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admCargo;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import br.com.hfsframework.admin.business.AdmCargoBC;
import br.com.hfsframework.admin.business.AdmCargoFuncionarioBC;
import br.com.hfsframework.admin.business.AdmCargoPerfilBC;
import br.com.hfsframework.admin.model.AdmCargo;
import br.com.hfsframework.admin.model.AdmFuncionario;
import br.com.hfsframework.admin.model.AdmPerfil;
import br.com.hfsframework.base.BaseViewCadastro;
import br.com.hfsframework.base.IBaseViewCadastro;
import br.com.hfsframework.security.Pages;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

/**
 * The Class AdmCargoMB.
 */
@Named
@ViewAccessScoped
@TratamentoErrosEsperados
public class AdmCargoMB extends
		BaseViewCadastro<AdmCargo, Long, AdmCargoBC, 
		Pages.Secure.ListarAdmCargo, Pages.Secure.EditarAdmCargo>
		implements IBaseViewCadastro<AdmCargo> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The adm cargo perfil BC. */
	@Inject
	private AdmCargoPerfilBC admCargoPerfilBC; 

	/** The adm cargo funcionario BC. */
	@Inject
	private AdmCargoFuncionarioBC admCargoFuncionarioBC; 

	/**
	 * Instantiates a new vw adm cargo MB.
	 */
	public AdmCargoMB() {
		super(Pages.Secure.ListarAdmCargo.class, Pages.Secure.EditarAdmCargo.class);
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
		return super.onIncluir(new AdmCargo());
	}

	@Override
	public String salvar() {
		return super.salvar(getBean().getId(), getBean().getNomeCargo());
	}

	@Override
	public void excluir(AdmCargo entidade) {
		super.excluir(entidade);
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#preProcessPDF(java.lang.Object)
	 */
	@Override
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		super.preProcessPDF(document, "AdmCargo");
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getBean()
	 */
	@Override
	public AdmCargo getBean() {
		return super.getEntidade();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#setBean(java.lang.Object)
	 */
	@Override
	public void setBean(AdmCargo entidade) {
		super.setEntidade(entidade);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getListaBean()
	 */
	@Override
	public List<AdmCargo> getListaBean() {
		return super.getListaEntidade();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#setListaBean(java.util.List)
	 */
	@Override
	public void setListaBean(List<AdmCargo> listaEntidade) {
		super.setListaEntidade(listaEntidade);		
	}

	/**
	 * Gets the adm perfils.
	 *
	 * @return the adm perfils
	 */
	public List<AdmPerfil> getAdmPerfils(){
		if (this.getBean()!=null && this.getBean().getId()!=null)
			return admCargoPerfilBC.findPerfilByCodCargo(this.getBean().getId());
		else 
			return null;
	}
	
	/**
	 * Gets the adm funcionarios.
	 *
	 * @return the adm funcionarios
	 */
	public List<AdmFuncionario> getAdmFuncionarios(){
		if (this.getBean()!=null && this.getBean().getId()!=null)
			return admCargoFuncionarioBC.findFuncionarioByCodCargo(this.getBean().getId());
		else 
			return null;
	}
}
