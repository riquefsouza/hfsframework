/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admUsuario;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import br.com.hfsframework.admin.business.AdmUsuarioBC;
import br.com.hfsframework.admin.model.AdmUsuario;
import br.com.hfsframework.base.BaseViewCadastro;
import br.com.hfsframework.base.IBaseViewCadastro;
import br.com.hfsframework.security.Pages;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

@Named
@ViewAccessScoped
@TratamentoErrosEsperados
public class AdmUsuarioMB extends
		BaseViewCadastro<AdmUsuario, Long, AdmUsuarioBC, 
		Pages.Secure.ListarAdmUsuario, Pages.Secure.EditarAdmUsuario>
		implements IBaseViewCadastro<AdmUsuario> {

	private static final long serialVersionUID = 1L;

	public AdmUsuarioMB() {
		super(Pages.Secure.ListarAdmUsuario.class, Pages.Secure.EditarAdmUsuario.class);
	}

	@PostConstruct
	public void init() {
		atualizaListaDataTable();
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#onIncluir()
	 */
	@Override
	public String onIncluir() {
		AdmUsuario usuario = new AdmUsuario();
		usuario.setCreatedDate(Calendar.getInstance());
		usuario.setCreatedBy(getUsuarioAutenticado().getUserName());
		return super.onIncluir(usuario);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#salvar()
	 */
	@Override
	public String salvar() {
		getBean().setSenha(DigestUtils.shaHex("abcd1234"));
		getBean().setModifiedDate(Calendar.getInstance());
		getBean().setModifiedBy(getUsuarioAutenticado().getUserName());
		return super.salvar(getBean().getId(), getBean().getNome());
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseViewCadastro#excluir(java.lang.Object)
	 */
	@Override
	public void excluir(AdmUsuario entidade) {
		super.excluir(entidade);
	}	
	
	@Override
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		super.preProcessPDF(document, "AdmUsuario");
	}

	@Override
	public AdmUsuario getBean() {
		return super.getEntidade();
	}

	@Override
	public void setBean(AdmUsuario entidade) {
		super.setEntidade(entidade);
	}

	@Override
	public List<AdmUsuario> getListaBean() {
		return super.getListaEntidade();
	}

	@Override
	public void setListaBean(List<AdmUsuario> listaEntidade) {
		super.setListaEntidade(listaEntidade);		
	}

}
