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
 * The Class AdmCargoFuncionarioPK.
 */
@Embeddable
public class AdmCargoFuncionarioPK implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cod cargo. */
	@Column(name = "CFN_CAR_SEQ")
	private Long codCargo;

	/** The cod funcionario. */
	@Column(name = "CFN_FUN_CODIGO")
	private Long codFuncionario;

	/**
	 * Instantiates a new vw adm cargo funcionario PK.
	 */
	public AdmCargoFuncionarioPK() {
		super();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.codCargo = 0L;
		this.codFuncionario = 0L;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AdmCargoFuncionarioPK)) {
			return false;
		}
		AdmCargoFuncionarioPK castOther = (AdmCargoFuncionarioPK) other;
		return (this.codCargo == castOther.codCargo) && (this.codFuncionario == castOther.codFuncionario);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.codCargo ^ (this.codCargo >>> 32)));
		hash = hash * prime + ((int) (this.codFuncionario ^ (this.codFuncionario >>> 32)));

		return hash;
	}

}