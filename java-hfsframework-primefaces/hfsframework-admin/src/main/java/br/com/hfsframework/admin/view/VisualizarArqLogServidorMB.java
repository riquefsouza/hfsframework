/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view;

import java.io.File;
import java.io.Serializable;

import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import br.com.hfsframework.base.BaseViewController;
import br.com.hfsframework.util.arquivo.ArquivoUtil;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

/**
 * The Class VisualizarArqLogServidorMB.
 */
@Named
@ViewAccessScoped
@TratamentoErrosEsperados
public class VisualizarArqLogServidorMB extends BaseViewController implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The numero linhas. */
	private Integer numeroLinhas;

	/** The conteudo arquivo log. */
	private String conteudoArquivoLog;

	/**
	 * Instantiates a new visualizar arq log servidor MB.
	 */
	public VisualizarArqLogServidorMB() {
		this.numeroLinhas = 100;
		this.conteudoArquivoLog = "";
	}

	/**
	 * On pesquisar.
	 */
	public void onPesquisar() {
		String arquivoServerLog = System.getProperty("jboss.server.log.dir") + "/server.log";
		File arquivoLog = new File(arquivoServerLog);
		if (arquivoLog.exists()) {
			this.conteudoArquivoLog = ArquivoUtil.lerNumeroLinhas(arquivoLog, getNumeroLinhas().intValue());
		} else {
			this.conteudoArquivoLog = String.format("Arquivo de log %s não encontrado", new Object[] { arquivoLog });
		}
	}

	/**
	 * On limpar pesquisa.
	 */
	public void onLimparPesquisa() {
		this.numeroLinhas = 100;
		this.conteudoArquivoLog = "";
	}

	/**
	 * Cancelar.
	 *
	 * @return the string
	 */
	public String cancelar() {
		return getPaginaDesktop();
	}

	/**
	 * Gets the numero linhas.
	 *
	 * @return the numero linhas
	 */
	public Integer getNumeroLinhas() {
		return numeroLinhas;
	}

	/**
	 * Sets the numero linhas.
	 *
	 * @param numeroLinhas
	 *            the new numero linhas
	 */
	public void setNumeroLinhas(Integer numeroLinhas) {
		this.numeroLinhas = numeroLinhas;
	}

	/**
	 * Gets the conteudo arquivo log.
	 *
	 * @return the conteudo arquivo log
	 */
	public String getConteudoArquivoLog() {
		return conteudoArquivoLog;
	}

	/**
	 * Sets the conteudo arquivo log.
	 *
	 * @param conteudoArquivoLog
	 *            the new conteudo arquivo log
	 */
	public void setConteudoArquivoLog(String conteudoArquivoLog) {
		this.conteudoArquivoLog = conteudoArquivoLog;
	}

}
