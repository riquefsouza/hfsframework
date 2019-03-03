/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The Class AdmFuncionarioPerfil.
 */
@Entity
@Table(name = "ADM_FUNCIONARIO_PERFIL")
@NamedQueries({
	@NamedQuery(name = "AdmFuncionarioPerfil.deleteByPerfil", query = "DELETE FROM AdmFuncionarioPerfil fp WHERE fp.id.perfilSeq = ?1"),
	@NamedQuery(name = "AdmFuncionarioPerfil.deleteByCodFuncionario", query = "DELETE FROM AdmFuncionarioPerfil fp WHERE fp.id.codFuncionario = ?1")
})	
public class AdmFuncionarioPerfil implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@EmbeddedId
	private AdmFuncionarioPerfilPK id;

	/* The adm perfil. */
	// bi-directional many-to-one association to AdmPerfil
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "USP_PRF_SEQ", nullable=false, insertable = false, updatable = false)
	private AdmPerfil admPerfil;

	/**
	 * Instantiates a new adm cargo perfil.
	 */
	public AdmFuncionarioPerfil() {
		limpar();
	}
	
	/**
	 * Limpar.
	 */
	public void limpar(){
		this.id = new AdmFuncionarioPerfilPK();
		//this.admPerfil = new AdmPerfil();
	}

	/**
	 * Pega o the id.
	 *
	 * @return o the id
	 */
	public AdmFuncionarioPerfilPK getId() {
		return this.id;
	}

	/**
	 * Atribui o the id.
	 *
	 * @param id
	 *            o novo the id
	 */
	public void setId(AdmFuncionarioPerfilPK id) {
		this.id = id;
	}

	/*
	 * Pega o the adm perfil.
	 *
	 * @return o the adm perfil
	 */
	public AdmPerfil getAdmPerfil() {
		return this.admPerfil;
	}

	/**
	 * Atribui o the adm perfil.
	 *
	 * @param admPerfil
	 *            o novo the adm perfil
	 */
	public void setAdmPerfil(AdmPerfil admPerfil) {
		this.admPerfil = admPerfil;
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
		AdmFuncionarioPerfil other = (AdmFuncionarioPerfil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}