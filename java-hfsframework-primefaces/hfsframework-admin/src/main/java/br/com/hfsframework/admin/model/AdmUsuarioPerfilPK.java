/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0.2
 * @since 2019
 */
package br.com.hfsframework.admin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// TODO: Auto-generated Javadoc
/**
 * The Class AdmUsuarioPerfilPK.
 */
@Embeddable
public class AdmUsuarioPerfilPK implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cod usuario. */
	@Column(name = "USP_USU_SEQ")
	private Long usuarioSeq;

	/** The perfil seq. */
	@Column(name = "USP_PRF_SEQ", insertable = false, updatable = false)
	private Long perfilSeq;

	/**
	 * Instantiates a new adm usuario perfil PK.
	 */
	public AdmUsuarioPerfilPK() {
		super();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.usuarioSeq = 0L;
		this.perfilSeq = 0L;
	}

	/**
	 * Gets the usuario seq.
	 *
	 * @return the usuario seq
	 */
	public Long getUsuarioSeq() {
		return usuarioSeq;
	}

	/**
	 * Sets the usuario seq.
	 *
	 * @param usuarioSeq the new usuario seq
	 */
	public void setUsuarioSeq(Long usuarioSeq) {
		this.usuarioSeq = usuarioSeq;
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
		if (!(other instanceof AdmUsuarioPerfilPK)) {
			return false;
		}
		AdmUsuarioPerfilPK castOther = (AdmUsuarioPerfilPK) other;
		return (this.usuarioSeq == castOther.usuarioSeq) && (this.perfilSeq == castOther.perfilSeq);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.usuarioSeq ^ (this.usuarioSeq >>> 32)));
		hash = hash * prime + ((int) (this.perfilSeq ^ (this.perfilSeq >>> 32)));

		return hash;
	}


}