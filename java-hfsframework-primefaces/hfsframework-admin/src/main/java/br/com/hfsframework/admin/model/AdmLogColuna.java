/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The Class AdmLogColuna.
 */
@Entity
@Table(name = "ADM_LOG_COLUNA")
@NamedQuery(name = "AdmLogColuna.findAll", query = "SELECT c FROM AdmLogColuna c")
public class AdmLogColuna implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The nome. */
	@Id
	@Column(name = "COL_NOME", length = 30)
	private String nome;

	/** The nome exibicao. */
	@Column(name = "COL_NOME_EXIBICAO")
	private String nomeExibicao;

	/** The comando. */
	@Column(name = "COL_COMANDO")
	private String comando;

	/**
	 * Instantiates a new adm log coluna.
	 */
	public AdmLogColuna() {
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.nome = null;
		this.nomeExibicao = null;
		this.comando = null;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome
	 *            the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the nome exibicao.
	 *
	 * @return the nome exibicao
	 */
	public String getNomeExibicao() {
		return nomeExibicao;
	}

	/**
	 * Sets the nome exibicao.
	 *
	 * @param nomeExibicao
	 *            the new nome exibicao
	 */
	public void setNomeExibicao(String nomeExibicao) {
		this.nomeExibicao = nomeExibicao;
	}

	/**
	 * Gets the comando.
	 *
	 * @return the comando
	 */
	public String getComando() {
		return comando;
	}

	/**
	 * Sets the comando.
	 *
	 * @param comando
	 *            the new comando
	 */
	public void setComando(String comando) {
		this.comando = comando;
	}
}
