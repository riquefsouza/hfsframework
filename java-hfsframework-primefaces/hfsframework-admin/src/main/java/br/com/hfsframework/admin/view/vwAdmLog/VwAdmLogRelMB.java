/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.vwAdmLog;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.hfsframework.admin.business.VwAdmLogBC;
import br.com.hfsframework.admin.business.VwAdmLogValorBC;
import br.com.hfsframework.admin.model.VwAdmLog;
import br.com.hfsframework.base.IBaseViewRelatorio;
import br.com.hfsframework.base.relatorio.BaseViewRelatorio;
import br.com.hfsframework.base.relatorio.IBaseRelatorio;
import br.com.hfsframework.base.relatorio.RelatorioPath;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

/**
 * The Class VwAdmLogRelMB.
 */
@Named
@ViewScoped
@TratamentoErrosEsperados
public class VwAdmLogRelMB 
	extends BaseViewRelatorio<VwAdmLog, Long, VwAdmLogBC>
		implements IBaseViewRelatorio {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The forcar download. */
	private Boolean forcarDownload;
	
	/** The relatorio. */
	@Inject
	@RelatorioPath("VwAdmLog")
	private IBaseRelatorio relatorio;

	@Inject
	private VwAdmLogValorBC vwAdmLogValorBC;

	/**
	 * Instantiates a new vw adm log rel MB.
	 */
	public VwAdmLogRelMB() {
		super();
		this.forcarDownload = false;
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewRelatorio#exportar()
	 */
	public void exportar() {
		Map<String, Object> params = getParametros();
		params.put("PARAMETRO1", "");

		List<VwAdmLog> lista = getBusinessController().findAll();
		for (VwAdmLog bean : lista) {
			if (bean.getUsuario()!=null) {
				bean.setListaLogValor(vwAdmLogValorBC.getRepositorio().findByFiltros(
					bean.getUsuario(), bean.getDataNumero(), bean.getOperacao(), 
					bean.getIp(), bean.getEntidade(), bean.getTabela(), bean.getChave()));
			}
		}
		
		super.exportar(relatorio, lista, params, forcarDownload);
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
