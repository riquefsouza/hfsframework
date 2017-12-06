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
 * The persistent class for the ADM_CARGO_PERFIL database table.
 * 
 */
@Entity
@Table(name = "ADM_CARGO_PERFIL")
@NamedQueries({
	@NamedQuery(name = "AdmCargoPerfil.findPerfilByCodCargo", query = "SELECT cp.admPerfil FROM AdmCargoPerfil cp WHERE cp.id.codCargo=?1"),
	@NamedQuery(name = "AdmCargoPerfil.deleteByPerfil", query = "DELETE FROM AdmCargoPerfil cp WHERE cp.id.perfilSeq = ?1"),
	@NamedQuery(name = "AdmCargoPerfil.deleteByCodCargo", query = "DELETE FROM AdmCargoPerfil cp WHERE cp.id.codCargo=?1")
})
public class AdmCargoPerfil implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@EmbeddedId
	private AdmCargoPerfilPK id;

	/** The adm perfil. */
	// bi-directional many-to-one association to AdmPerfil
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "CGP_PRF_SEQ", nullable=false, insertable = false, updatable = false)
	private AdmPerfil admPerfil;

	/**
	 * Instantiates a new adm cargo perfil.
	 */
	public AdmCargoPerfil() {
		limpar();
	}
	
	/**
	 * Limpar.
	 */
	public void limpar(){
		this.id = new AdmCargoPerfilPK();
		//this.admPerfil = new AdmPerfil();
	}

	/**
	 * Pega o the id.
	 *
	 * @return o the id
	 */
	public AdmCargoPerfilPK getId() {
		return this.id;
	}

	/**
	 * Atribui o the id.
	 *
	 * @param id
	 *            o novo the id
	 */
	public void setId(AdmCargoPerfilPK id) {
		this.id = id;
	}

	/**
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
		AdmCargoPerfil other = (AdmCargoPerfil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}