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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.hfsframework.security.model.PaginaVO;


/**
 * The persistent class for the ADM_PAGINA database table.
 * 
 */
@Entity
@Table(name="ADM_PAGINA")
@NamedQueries({
	@NamedQuery(name = "AdmPagina.getDescricaoById", query = "SELECT c.url FROM AdmPagina c WHERE c.id = ?1"),
	@NamedQuery(name = "AdmPagina.countNovo", query = "SELECT COUNT(c) FROM AdmPagina c WHERE LOWER(c.url) = ?1"),
	@NamedQuery(name = "AdmPagina.countAntigo", query = "SELECT COUNT(c) FROM AdmPagina c WHERE LOWER(c.url) <> ?1 AND LOWER(c.url) = ?2"),	
	@NamedQuery(name = "AdmPagina.findPerfisPorPagina", query="SELECT distinct p FROM AdmPagina pag inner join pag.admPerfils p where pag = ?1")
})
public class AdmPagina implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@SequenceGenerator(name="ADM_PAGINA_ID_GENERATOR", sequenceName="ADM_PAGINA_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADM_PAGINA_ID_GENERATOR")
	@Column(name="PAG_SEQ")
	private Long id;

	/** The descricao. */
	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name="PAG_DESCRICAO", unique = true)
	private String descricao;
	
	/** The url. */
	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name="PAG_URL", unique = true)
	private String url;

	/** The adm perfils. */ 
	//bi-directional many-to-many association to AdmPerfil
	//@ManyToMany(mappedBy="admPaginas", fetch = FetchType.LAZY) //, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ADM_PAGINA_PERFIL", joinColumns = { 
			@JoinColumn(name = "PGL_PAG_SEQ") }, inverseJoinColumns = {@JoinColumn(name = "PGL_PRF_SEQ") })
	private List<AdmPerfil> admPerfils;
	
	/** The adm menus. */
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "admPagina", fetch = FetchType.EAGER)	
	private List<AdmMenu> admMenus;	
	

	/**
	 * Instantiates a new adm pagina.
	 */
	public AdmPagina() {
		this.admPerfils = new ArrayList<AdmPerfil>();
		this.admMenus = new ArrayList<AdmMenu>();
		limpar();
	}
	
	/**
	 * Instantiates a new adm pagina.
	 *
	 * @param p
	 *            the p
	 */
	public AdmPagina(PaginaVO p) {
		this();
		
		this.id = p.getId();
		this.descricao = p.getDescricao();
		this.url = p.getUrl();
	}
	
	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = null;
		this.descricao = null;
		this.url = null;
		this.admPerfils.clear();
		this.admMenus.clear();
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
	 * Pega o the url.
	 *
	 * @return o the url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * Atribui o the url.
	 *
	 * @param url
	 *            o novo the url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Pega o the adm perfils.
	 *
	 * @return o the adm perfils
	 */
	public List<AdmPerfil> getAdmPerfils() {
		return this.admPerfils;
	}

	/**
	 * Atribui o the adm perfils.
	 *
	 * @param admPerfils
	 *            o novo the adm perfils
	 */
	public void setAdmPerfils(List<AdmPerfil> admPerfils) {
		this.admPerfils = admPerfils;
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
		AdmPagina other = (AdmPagina) obj;
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
		return this.url;
	}

	/**
	 * Gets the perfis pagina.
	 *
	 * @return the perfis pagina
	 */
	public String getPerfisPagina() {
		String ret = "";
		for (AdmPerfil item : getAdmPerfils()) {
			ret = ret.concat(item.getDescricao()).concat(", ");
		}
		if (ret != "") {
			ret = ret.substring(0, ret.length() - 2);
		}
		return ret;
	}
	
	/**
	 * To pagina VO.
	 *
	 * @return the pagina VO
	 */
	public PaginaVO toPaginaVO(){
		PaginaVO p = new PaginaVO();

		p.setId(id);
		p.setDescricao(descricao);
		p.setUrl(url);

		return p;
	}
}