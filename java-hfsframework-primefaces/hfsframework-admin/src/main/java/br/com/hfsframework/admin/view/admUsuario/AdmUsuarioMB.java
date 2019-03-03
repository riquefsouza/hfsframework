/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admUsuario;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import br.com.hfsframework.admin.business.AdmUsuarioBC;
import br.com.hfsframework.admin.model.AdmUsuario;
import br.com.hfsframework.admin.model.AdmUsuarioPK;
import br.com.hfsframework.base.BaseViewConsulta;
import br.com.hfsframework.base.IBaseViewConsulta;
import br.com.hfsframework.security.Pages;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

@Named
@ViewAccessScoped
@TratamentoErrosEsperados
public class AdmUsuarioMB extends
		BaseViewConsulta<AdmUsuario, AdmUsuarioPK, AdmUsuarioBC, 
		Pages.Secure.ListarAdmUsuario>
		implements IBaseViewConsulta<AdmUsuario> {

	private static final long serialVersionUID = 1L;

	public AdmUsuarioMB() {
		super(Pages.Secure.ListarAdmUsuario.class);
	}

	@PostConstruct
	public void init() {
		atualizaListaDataTable();
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
