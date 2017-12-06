/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the ADM_PARAMETRO_CATEGORIA database table.
 * 
 */
@Entity
@Table(name="ADM_PARAMETRO_CATEGORIA")
@NamedQueries({
	@NamedQuery(name = "AdmParametroCategoria.getDescricaoById", query = "SELECT c.descricao FROM AdmParametroCategoria c WHERE c.id = ?1"),
	@NamedQuery(name = "AdmParametroCategoria.countNovo", query = "SELECT COUNT(c) FROM AdmParametroCategoria c WHERE LOWER(c.descricao) = ?1"),
	@NamedQuery(name = "AdmParametroCategoria.countAntigo", query = "SELECT COUNT(c) FROM AdmParametroCategoria c WHERE LOWER(c.descricao) <> ?1 AND LOWER(c.descricao) = ?2")
})	
public class AdmParametroCategoria implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GenericGenerator(name = "ADM_PARAMETRO_CATEGORIA_ID_GENERATOR",
	strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    parameters = {
    	@Parameter(name = "sequence_name", value = "ADM_PARAMETRO_CATEGORIA_SEQ"),
        @Parameter(name = "initial_value", value = "1"),
        @Parameter(name = "increment_size", value = "1")
	})				
	@SequenceGenerator(name="ADM_PARAMETRO_CATEGORIA_ID_GENERATOR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADM_PARAMETRO_CATEGORIA_ID_GENERATOR")
	@Column(name="PMC_SEQ")
	private Long id;

	/** The descricao. */
	@NotNull
	@NotBlank
	@NotEmpty	
	@Column(name="PMC_DESCRICAO", unique = true)
	private String descricao;

	/** The ordem. */
	@Column(name="PMC_ORDEM")
	private Long ordem;

	/** The adm parametros. */
	//bi-directional many-to-one association to AdmParametro
	@OneToMany(mappedBy="admParametroCategoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<AdmParametro> admParametros;

	/**
	 * Instantiates a new adm parametro categoria.
	 */
	public AdmParametroCategoria() {
		this.admParametros = new ArrayList<AdmParametro>();
		limpar();
	}
	
	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = null;
		this.descricao = null;
		this.ordem = null;
		this.admParametros.clear();
	}

	/**
	 * Pega o the id.
	 *
	 * @return o the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Atribui o the id.
	 *
	 * @param id
	 *            o novo the id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Pega o the descricao.
	 *
	 * @return o the descricao
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Atribui o the descricao.
	 *
	 * @param descricao
	 *            o novo the descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Pega o the ordem.
	 *
	 * @return o the ordem
	 */
	public Long getOrdem() {
		return this.ordem;
	}

	/**
	 * Atribui o the ordem.
	 *
	 * @param ordem
	 *            o novo the ordem
	 */
	public void setOrdem(Long ordem) {
		this.ordem = ordem;
	}

	/**
	 * Pega o the adm parametros.
	 *
	 * @return o the adm parametros
	 */
	public List<AdmParametro> getAdmParametros() {
		return this.admParametros;
	}

	/**
	 * Atribui o the adm parametros.
	 *
	 * @param admParametros
	 *            o novo the adm parametros
	 */
	public void setAdmParametros(List<AdmParametro> admParametros) {
		this.admParametros = admParametros;
	}

	/**
	 * Adiciona o adm parametro.
	 *
	 * @param admParametro
	 *            the adm parametro
	 * @return the adm parametro
	 */
	public AdmParametro addAdmParametro(AdmParametro admParametro) {
		getAdmParametros().add(admParametro);
		admParametro.setAdmParametroCategoria(this);

		return admParametro;
	}

	/**
	 * Remove o adm parametro.
	 *
	 * @param admParametro
	 *            the adm parametro
	 * @return the adm parametro
	 */
	public AdmParametro removeAdmParametro(AdmParametro admParametro) {
		getAdmParametros().remove(admParametro);
		admParametro.setAdmParametroCategoria(null);

		return admParametro;
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
		AdmParametroCategoria other = (AdmParametroCategoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}