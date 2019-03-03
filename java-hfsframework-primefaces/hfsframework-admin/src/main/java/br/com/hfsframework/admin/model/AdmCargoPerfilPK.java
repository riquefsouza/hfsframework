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
 * The primary key class for the ADM_CARGO_PERFIL database table.
 * 
 */
@Embeddable
public class AdmCargoPerfilPK implements Serializable {
	
	/** The Constant serialVersionUID. */
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/** The cod cargo. */
	@Column(name = "CGP_CAR_SEQ")
	private Long codCargo;

	/** The perfil seq. */
	@Column(name = "CGP_PRF_SEQ", insertable = false, updatable = false)
	private Long perfilSeq;

	/**
	 * Instantiates a new adm cargo perfil PK.
	 */
	public AdmCargoPerfilPK() {
		super();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.codCargo = 0L;
		this.perfilSeq = 0L;
	}

	/**
	 * Pega o the cod cargo.
	 *
	 * @return o the cod cargo
	 */
	public Long getCodCargo() {
		return codCargo;
	}

	/**
	 * Atribui o the cod cargo.
	 *
	 * @param codCargo
	 *            o novo the cod cargo
	 */
	public void setCodCargo(Long codCargo) {
		this.codCargo = codCargo;
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
		if (!(other instanceof AdmCargoPerfilPK)) {
			return false;
		}
		AdmCargoPerfilPK castOther = (AdmCargoPerfilPK) other;
		return (this.codCargo == castOther.codCargo) && (this.perfilSeq == castOther.perfilSeq);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.codCargo ^ (this.codCargo >>> 32)));
		hash = hash * prime + ((int) (this.perfilSeq ^ (this.perfilSeq >>> 32)));

		return hash;
	}

}