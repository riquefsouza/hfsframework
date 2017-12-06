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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="ADM_SETOR")
@NamedQueries({
	@NamedQuery(name="AdmSetor.findBySetorPai", query="SELECT DISTINCT v FROM AdmSetor v WHERE v.paiSetor = ?1 ORDER BY v.paiSetor, v.id"),
	@NamedQuery(name="AdmSetor.findBySetor", query="SELECT DISTINCT v FROM AdmSetor v WHERE v.id = ?1 ORDER BY v.paiSetor, v.id")
})	
public class AdmSetor implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The setor. */
	@Id
	@Column(name="SET_SIGLA")
	private String id;

	/** The nome setor. */
	@Column(name="SET_NOME")
	private String nomeSetor;

	/** The pai setor. */
	@Column(name="SET_PAI")
	private String paiSetor;

	/** The tipo setor. */
	@Column(name="SET_TIPO")
	private String tipoSetor;

	/**
	 * Instantiates a new vw adm setor.
	 */
	public AdmSetor() {
	}

	/**
	 * Pega o the nome setor.
	 *
	 * @return o the nome setor
	 */
	public String getNomeSetor() {
		return this.nomeSetor;
	}

	/**
	 * Atribui o the nome setor.
	 *
	 * @param nomeSetor
	 *            o novo the nome setor
	 */
	public void setNomeSetor(String nomeSetor) {
		this.nomeSetor = nomeSetor;
	}

	/**
	 * Pega o the pai setor.
	 *
	 * @return o the pai setor
	 */
	public String getPaiSetor() {
		return this.paiSetor;
	}

	/**
	 * Atribui o the pai setor.
	 *
	 * @param paiSetor
	 *            o novo the pai setor
	 */
	public void setPaiSetor(String paiSetor) {
		this.paiSetor = paiSetor;
	}

	/**
	 * Pega o the setor.
	 *
	 * @return o the setor
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Atribui o the setor.
	 *
	 * @param id
	 *            o novo the setor
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Pega o the tipo setor.
	 *
	 * @return o the tipo setor
	 */
	public String getTipoSetor() {
		return this.tipoSetor;
	}

	/**
	 * Atribui o the tipo setor.
	 *
	 * @param tipoSetor
	 *            o novo the tipo setor
	 */
	public void setTipoSetor(String tipoSetor) {
		this.tipoSetor = tipoSetor;
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
		AdmSetor other = (AdmSetor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}