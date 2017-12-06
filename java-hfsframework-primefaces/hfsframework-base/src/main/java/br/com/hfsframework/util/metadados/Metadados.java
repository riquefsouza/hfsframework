/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.metadados;

import java.io.Serializable;
import java.util.List;

/**
 * The Class Metadados.
 */
public class Metadados implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String versaoDriverJDBC;

	private String versaoBancoDeDados;

	private boolean suportaSelectForUpdate;

	private boolean suportaTransacoes;

	private List<MetadadosObjeto> objetos;

	public Metadados() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Metadados(String versaoDriverJDBC, String versaoBancoDeDados, boolean suportaSelectForUpdate,
			boolean suportaTransacoes, List<MetadadosObjeto> objetos) {
		super();
		this.versaoDriverJDBC = versaoDriverJDBC;
		this.versaoBancoDeDados = versaoBancoDeDados;
		this.suportaSelectForUpdate = suportaSelectForUpdate;
		this.suportaTransacoes = suportaTransacoes;
		this.objetos = objetos;
	}

	public String getVersaoDriverJDBC() {
		return versaoDriverJDBC;
	}

	public void setVersaoDriverJDBC(String versaoDriverJDBC) {
		this.versaoDriverJDBC = versaoDriverJDBC;
	}

	public String getVersaoBancoDeDados() {
		return versaoBancoDeDados;
	}

	public void setVersaoBancoDeDados(String versaoBancoDeDados) {
		this.versaoBancoDeDados = versaoBancoDeDados;
	}

	public boolean isSuportaSelectForUpdate() {
		return suportaSelectForUpdate;
	}

	public void setSuportaSelectForUpdate(boolean suportaSelectForUpdate) {
		this.suportaSelectForUpdate = suportaSelectForUpdate;
	}

	public boolean isSuportaTransacoes() {
		return suportaTransacoes;
	}

	public void setSuportaTransacoes(boolean suportaTransacoes) {
		this.suportaTransacoes = suportaTransacoes;
	}

	public List<MetadadosObjeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(List<MetadadosObjeto> objetos) {
		this.objetos = objetos;
	}

	@Override
	public String toString() {
		return "Metadados [\nversaoDriverJDBC=" + versaoDriverJDBC + ", \nversaoBancoDeDados=" + versaoBancoDeDados
				+ ", \nsuportaSelectForUpdate=" + suportaSelectForUpdate + ", \nsuportaTransacoes=" + suportaTransacoes
				+ ", \nobjetos=" + objetos + "]";
	}

}
