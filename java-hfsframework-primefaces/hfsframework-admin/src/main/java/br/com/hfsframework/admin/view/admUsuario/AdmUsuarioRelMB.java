/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admUsuario;

import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.hfsframework.admin.AdmUsuarioBC;
import br.com.hfsframework.admin.model.AdmUsuario;
import br.com.hfsframework.admin.model.AdmUsuarioPK;
import br.com.hfsframework.base.IBaseViewRelatorio;
import br.com.hfsframework.base.relatorio.BaseViewRelatorio;
import br.com.hfsframework.base.relatorio.IBaseRelatorio;
import br.com.hfsframework.base.relatorio.RelatorioPath;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

@Named
@ViewScoped
@TratamentoErrosEsperados
public class AdmUsuarioRelMB 
	extends BaseViewRelatorio<AdmUsuario, AdmUsuarioPK, AdmUsuarioBC>
		implements IBaseViewRelatorio {

	private static final long serialVersionUID = 1L;

	private Boolean forcarDownload;
	
	@Inject
	@RelatorioPath("AdmUsuario")
	private IBaseRelatorio relatorio;

	public AdmUsuarioRelMB() {
		super();
		this.forcarDownload = false;
	}
	
	public void exportar() {
		Map<String, Object> params = getParametros();
		params.put("PARAMETRO1", "");

		super.exportar(relatorio, getBusinessController().findAll(), params, forcarDownload);
	}

	public Boolean getForcarDownload() {
		return forcarDownload;
	}

	public void setForcarDownload(Boolean forcarDownload) {
		this.forcarDownload = forcarDownload;
	}

}
