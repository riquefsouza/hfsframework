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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	@NamedQuery(name = "AdmPagina.findPerfisPorPagina", query="SELECT distinct p FROM AdmPagina pag inner join pag.admPerfils p where pag = ?1"),
	@NamedQuery(name = "AdmPagina.findFuncionalidadesPorPagina", query="SELECT distinct f FROM AdmPagina pag inner join pag.admFuncionalidades f where pag = ?1"),
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

	/** The managed bean. */
	@Column(name="PAG_MB")
	private String managedBean;

	/** The url. */
	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name="PAG_URL", unique = true)
	private String url;

	/** The adm funcionalidades. */
	//bi-directional many-to-many association to AdmFuncionalidade
	@ManyToMany(fetch = FetchType.LAZY) //, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinTable(name="ADM_FUNCIONALIDADE_PAGINA", joinColumns={
			@JoinColumn(name="FPG_PAG_SEQ")}, inverseJoinColumns={@JoinColumn(name="FPG_FUN_SEQ")})
	private List<AdmFuncionalidade> admFuncionalidades;

	/** The adm perfils. */ 
	//bi-directional many-to-many association to AdmPerfil
	//@ManyToMany(mappedBy="admPaginas", fetch = FetchType.LAZY) //, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ADM_PAGINA_PERFIL", joinColumns = { 
			@JoinColumn(name = "PGL_PAG_SEQ") }, inverseJoinColumns = {@JoinColumn(name = "PGL_PRF_SEQ") })
	private List<AdmPerfil> admPerfils;
	

	/**
	 * Instantiates a new adm pagina.
	 */
	public AdmPagina() {
		this.admFuncionalidades = new ArrayList<AdmFuncionalidade>();
		this.admPerfils = new ArrayList<AdmPerfil>();
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
		this.managedBean = p.getManagedBean();
		this.url = p.getUrl();
	}
	
	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = null;
		this.managedBean = null;
		this.url = null;
		this.admFuncionalidades.clear();
		this.admPerfils.clear();
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
	 * Pega o the managed bean.
	 *
	 * @return o the managed bean
	 */
	public String getManagedBean() {
		return this.managedBean;
	}

	/**
	 * Atribui o the managed bean.
	 *
	 * @param managedBean
	 *            o novo the managed bean
	 */
	public void setManagedBean(String managedBean) {
		this.managedBean = managedBean;
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
	 * Pega o the adm funcionalidades.
	 *
	 * @return o the adm funcionalidades
	 */
	public List<AdmFuncionalidade> getAdmFuncionalidades() {
		return this.admFuncionalidades;
	}

	/**
	 * Atribui o the adm funcionalidades.
	 *
	 * @param admFuncionalidades
	 *            o novo the adm funcionalidades
	 */
	public void setAdmFuncionalidades(List<AdmFuncionalidade> admFuncionalidades) {
		this.admFuncionalidades = admFuncionalidades;
	}

	/**
	 * Adiciona o adm funcionalidades 1.
	 *
	 * @param admFuncionalidades
	 *            the adm funcionalidades
	 * @return the adm funcionalidade
	 */
	public AdmFuncionalidade addAdmFuncionalidades(AdmFuncionalidade admFuncionalidades) {
		getAdmFuncionalidades().add(admFuncionalidades);
		admFuncionalidades.setAdmPaginaInicial(this);

		return admFuncionalidades;
	}

	/**
	 * Remove o adm funcionalidades.
	 *
	 * @param admFuncionalidades
	 *            the adm funcionalidades
	 * @return the adm funcionalidade
	 */
	public AdmFuncionalidade removeAdmFuncionalidades(AdmFuncionalidade admFuncionalidades) {
		getAdmFuncionalidades().remove(admFuncionalidades);
		admFuncionalidades.setAdmPaginaInicial(null);

		return admFuncionalidades;
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
		p.setManagedBean(managedBean);
		p.setUrl(url);

		/*
		for (AdmFuncionalidade admFuncionalidade : admFuncionalidades) {
			p.getFuncionalidades().add(admFuncionalidade.toFuncionalidadeVO());
		}
		for (AdmPerfil admPerfil : admPerfils) {
			p.getPerfils().add(admPerfil.toPerfilVO());
		}
		*/

		return p;
	}
}