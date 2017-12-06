/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.hfsframework.base.IBaseViewRelatorio;
import br.com.hfsframework.base.relatorio.BaseViewRelatorioController;
import br.com.hfsframework.base.relatorio.IBaseRelatorio;
import br.com.hfsframework.base.relatorio.RelatorioPath;
import br.com.hfsframework.util.arquivo.ArquivoUtil;
import br.com.hfsframework.util.arquivo.VisualizarArqLogServidorVO;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

/**
 * The Class VisualizarArqLogServidorRelMB.
 */
@Named
@ViewScoped
@TratamentoErrosEsperados
public class VisualizarArqLogServidorRelMB extends BaseViewRelatorioController implements IBaseViewRelatorio {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The forcar download. */
	private Boolean forcarDownload;
	
	/** The relatorio. */
	@Inject
	@RelatorioPath("ArqLogServidor")
	private IBaseRelatorio relatorio;

	@Inject
	private VisualizarArqLogServidorMB visualizarArqLogServidorMB;
	
	/**
	 * Instantiates a new visualizar arq log servidor rel MB.
	 */
	public VisualizarArqLogServidorRelMB() {
		super();
		this.forcarDownload = false;
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewRelatorio#exportar()
	 */
	public void exportar() {
		Map<String, Object> params = getParametros();
		params.put("PARAMETRO1", "");

		List<VisualizarArqLogServidorVO> lista = new ArrayList<VisualizarArqLogServidorVO>();

		String arquivoServerLog = System.getProperty("jboss.server.log.dir") + "/server.log";
		File arquivoLog = new File(arquivoServerLog);
		if (arquivoLog.exists()) {
			//ArquivoUtil.lerLinhas(arquivoLog, lista);
			ArquivoUtil.lerNumeroLinhas(arquivoLog, lista, visualizarArqLogServidorMB.getNumeroLinhas().intValue());
			super.exportar(relatorio, lista, params, forcarDownload);
		} else {
			gerarMensagemInformativa(String.format("Arquivo de log %s não encontrado", new Object[] { arquivoLog }));
		}
	}

	/**
	 * Pega o the forcar download.
	 *
	 * @return o the forcar download
	 */
	public Boolean getForcarDownload() {
		return forcarDownload;
	}

	/**
	 * Atribui o the forcar download.
	 *
	 * @param forcarDownload
	 *            o novo the forcar download
	 */
	public void setForcarDownload(Boolean forcarDownload) {
		this.forcarDownload = forcarDownload;
	}



}
