/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admMenu;

import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.hfsframework.admin.business.AdmMenuBC;
import br.com.hfsframework.admin.model.AdmMenu;
import br.com.hfsframework.base.IBaseViewRelatorio;
import br.com.hfsframework.base.relatorio.BaseViewRelatorio;
import br.com.hfsframework.base.relatorio.IBaseRelatorio;
import br.com.hfsframework.base.relatorio.RelatorioPath;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

/**
 * The Class AdmMenuRelMB.
 */
@Named
@ViewScoped
@TratamentoErrosEsperados
public class AdmMenuRelMB 
	extends BaseViewRelatorio<AdmMenu, Long, AdmMenuBC>
		implements IBaseViewRelatorio {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The forcar download. */
	private Boolean forcarDownload;
	
	/** The relatorio. */
	@Inject
	@RelatorioPath("AdmMenu")
	private IBaseRelatorio relatorio;

	/**
	 * Instantiates a new adm menu rel MB.
	 */
	public AdmMenuRelMB() {
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
