/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admParametroCategoria;

import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.hfsframework.admin.business.AdmParametroCategoriaBC;
import br.com.hfsframework.admin.model.AdmParametroCategoria;
import br.com.hfsframework.base.IBaseViewRelatorio;
import br.com.hfsframework.base.relatorio.BaseViewRelatorio;
import br.com.hfsframework.base.relatorio.IBaseRelatorio;
import br.com.hfsframework.base.relatorio.RelatorioPath;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

/**
 * The Class AdmParametroCategoriaRelMB.
 */
@Named
@ViewScoped
@TratamentoErrosEsperados
public class AdmParametroCategoriaRelMB 
	extends BaseViewRelatorio<AdmParametroCategoria, Long, AdmParametroCategoriaBC>
		implements IBaseViewRelatorio {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The forcar download. */
	private Boolean forcarDownload;
	
	/** The relatorio. */
	@Inject
	@RelatorioPath("AdmParametroCategoria")
	private IBaseRelatorio relatorio;

	/**
	 * Instantiates a new adm parametro categoria rel MB.
	 */
	public AdmParametroCategoriaRelMB() {
		super();
		this.forcarDownload = false;
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewRelatorio#exportar()
	 */
	public void exportar() {
		Map<String, Object> params = getParametros();
		params.put("PARAMETRO1", "");

		super.exportar(relatorio, getBusinessController().findAll(), params, forcarDownload);
	}

	/**
	 * Gets the forcar download.
	 *
	 * @return the forcar download
	 */
	public Boolean getForcarDownload() {
		return forcarDownload;
	}

	/**
	 * Sets the forcar download.
	 *
	 * @param forcarDownload
	 *            the new forcar download
	 */
	public void setForcarDownload(Boolean forcarDownload) {
		this.forcarDownload = forcarDownload;
	}

}
