/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.hfsframework.converters.BooleanToStringConverter;

/**
 * The Class AdmUsuarioIp.
 */
@Entity
@Table(name = "ADM_USUARIO_IP")
public class AdmUsuarioIp implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@EmbeddedId
	private AdmUsuarioIpPK id;

	/** The ativo. */
	@Convert(converter=BooleanToStringConverter.class)
	@Column(name = "UIP_ATIVO")
	private Boolean ativo;
	
	/** The adm perfil. */
	// bi-directional many-to-one association to AdmUsuario
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "UIP_USU_SEQ", nullable=false, insertable = false, updatable = false)
	private AdmUsuario admUsuario;

	/**
	 * Instantiates a new adm usuario ip.
	 */
	public AdmUsuarioIp() {
		super();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = new AdmUsuarioIpPK();
		//this.admUsuario = new AdmUsuario();
		this.ativo = false;
	}

	/**
	 * Pega o the id.
	 *
	 * @return o the id
	 */
	public AdmUsuarioIpPK getId() {
		return this.id;
	}

	/**
	 * Atribui o the id.
	 *
	 * @param id
	 *            o novo the id
	 */
	public void setId(AdmUsuarioIpPK id) {
		this.id = id;
	}

	/**
	 * Pega o the ativo.
	 *
	 * @return o the ativo
	 */
	public Boolean getAtivo() {
		return this.ativo;
	}

	/**
	 * Atribui o the ativo.
	 *
	 * @param ativo
	 *            o novo the ativo
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	/**
	 * Pega o the adm usuario.
	 *
	 * @return o the adm usuario
	 */
	public AdmUsuario getAdmUsuario() {
		return this.admUsuario;
	}

	/**
	 * Atribui o the adm usuario.
	 *
	 * @param admUsuario
	 *            o novo the adm usuario
	 */
	public void setAdmUsuario(AdmUsuario admUsuario) {
		this.admUsuario = admUsuario;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdmUsuarioIp other = (AdmUsuarioIp) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return id.getIp();
	}

}