/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The Class AdmUsuarioPK.
 */
@Embeddable
public class AdmUsuarioPK implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The matricula. */
	@Column(name = "USU_MATRICULA")
	private Long matricula;

	/** The ip. */
	@Column(name = "USU_IP")
	private String ip;

	/**
	 * Instantiates a new adm usuario PK.
	 */
	public AdmUsuarioPK() {
		super();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.matricula = 0L;
		this.ip = "";
	}

	/**
	 * Gets the matricula.
	 *
	 * @return the matricula
	 */
	public Long getMatricula() {
		return this.matricula;
	}

	/**
	 * Sets the matricula.
	 *
	 * @param matricula
	 *            the new matricula
	 */
	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return this.ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip
	 *            the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AdmUsuarioPK)) {
			return false;
		}
		AdmUsuarioPK castOther = (AdmUsuarioPK) other;
		return (this.matricula == castOther.matricula) && this.ip.equals(castOther.ip);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.matricula ^ (this.matricula >>> 32)));
		hash = hash * prime + this.ip.hashCode();

		return hash;
	}
}