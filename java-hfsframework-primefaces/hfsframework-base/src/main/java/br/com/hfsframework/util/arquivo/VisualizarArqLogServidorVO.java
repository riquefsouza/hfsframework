/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.arquivo;

import java.io.Serializable;

public class VisualizarArqLogServidorVO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private String linha;

	public VisualizarArqLogServidorVO() {
		super();
		linha = "";
	}
	
	public VisualizarArqLogServidorVO(String linha) {
		super();
		this.linha = linha;
	}

	public String getLinha() {
		return linha;
	}

	public void setLinha(String linha) {
		this.linha = linha;
	}

}
