/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The Class AdmFuncionarioPerfilPK.
 */
@Embeddable
public class AdmFuncionarioPerfilPK implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cod funcionario. */
	@Column(name = "USP_FUN_CODIGO")
	private Long codFuncionario;

	/** The perfil seq. */
	@Column(name = "USP_PRF_SEQ", insertable = false, updatable = false)
	private Long perfilSeq;

	/**
	 * Instantiates a new adm funcionario perfil PK.
	 */
	public AdmFuncionarioPerfilPK() {
		super();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.codFuncionario = 0L;
		this.perfilSeq = 0L;
	}

	/**
	 * Pega o the cod funcionario.
	 *
	 * @return o the cod funcionario
	 */
	public Long getCodFuncionario() {
		return codFuncionario;
	}

	/**
	 * Atribui o the cod funcionario.
	 *
	 * @param codFuncionario
	 *            o novo the cod funcionario
	 */
	public void setCodFuncionario(Long codFuncionario) {
		this.codFuncionario = codFuncionario;
	}

	/**
	 * Pega o the perfil seq.
	 *
	 * @return o the perfil seq
	 */
	public Long getPerfilSeq() {
		return perfilSeq;
	}

	/**
	 * Atribui o the perfil seq.
	 *
	 * @param perfilSeq
	 *            o novo the perfil seq
	 */
	public void setPerfilSeq(Long perfilSeq) {
		this.perfilSeq = perfilSeq;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AdmFuncionarioPerfilPK)) {
			return false;
		}
		AdmFuncionarioPerfilPK castOther = (AdmFuncionarioPerfilPK) other;
		return (this.codFuncionario == castOther.codFuncionario) && (this.perfilSeq == castOther.perfilSeq);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.codFuncionario ^ (this.codFuncionario >>> 32)));
		hash = hash * prime + ((int) (this.perfilSeq ^ (this.perfilSeq >>> 32)));

		return hash;
	}


}