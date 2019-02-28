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
import javax.persistence.ManyToOne;
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

import br.com.hfsframework.security.model.FuncionalidadeVO;


/**
 * The persistent class for the ADM_FUNCIONALIDADE database table.
 * 
 */
@Entity
@Table(name="ADM_FUNCIONALIDADE")
@NamedQueries({
	@NamedQuery(name="AdmFuncionalidade.getDescricaoById", query = "SELECT c.descricao FROM AdmFuncionalidade c WHERE c.id = ?1"),
	@NamedQuery(name="AdmFuncionalidade.countNovo", query = "SELECT COUNT(c) FROM AdmFuncionalidade c WHERE LOWER(c.descricao) = ?1"),
	@NamedQuery(name="AdmFuncionalidade.countAntigo", query = "SELECT COUNT(c) FROM AdmFuncionalidade c WHERE LOWER(c.descricao) <> ?1 AND LOWER(c.descricao) = ?2"),
	@NamedQuery(name="AdmFuncionalidade.findPerfisPorFuncionalidade", query="SELECT distinct p FROM AdmFuncionalidade f inner join f.admPerfils p where f = ?1"),
	@NamedQuery(name="AdmFuncionalidade.findPaginasPorFuncionalidade", query="SELECT distinct p FROM AdmFuncionalidade f inner join f.admPaginas p where f = ?1"),
	//@NamedQuery(name="AdmFuncionalidade.findMenusPorFuncionalidade", query="SELECT distinct m FROM AdmFuncionalidade f inner join f.admMenus m where f = ?1"),
	@NamedQuery(name="AdmFuncionalidade.findPerfisPorURL", query="SELECT distinct p FROM AdmFuncionalidade f inner join f.admPerfils p left join f.admPaginaInicial i left join f.admPaginas pag WHERE i.url = ?1 or pag.url = ?1")
})
public class AdmFuncionalidade implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@SequenceGenerator(name="ADM_FUNCIONALIDADE_ID_GENERATOR", sequenceName="ADM_FUNCIONALIDADE_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADM_FUNCIONALIDADE_ID_GENERATOR")
	@Column(name="FUN_SEQ")
	private Long id;

	/** The descricao. */
	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name="FUN_DESCRICAO", unique = true)
	private String descricao;

	/** The adm pagina inicial. */
	//bi-directional many-to-one association to AdmPagina
	@ManyToOne(optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name="FUN_PAG_SEQ", nullable=false)
	private AdmPagina admPaginaInicial;

	/** The adm paginas. */
	//bi-directional many-to-many association to AdmPagina
	//@ManyToMany(mappedBy="admFuncionalidades", fetch=FetchType.LAZY) //, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="ADM_FUNCIONALIDADE_PAGINA", joinColumns={
				@JoinColumn(name="FPG_FUN_SEQ")	} , inverseJoinColumns={@JoinColumn(name="FPG_PAG_SEQ") })
	private List<AdmPagina> admPaginas;

	/** The adm perfils. */
	//bi-directional many-to-many association to AdmPerfil	
	//@ManyToMany(mappedBy="admFuncionalidades", cascade = CascadeType.ALL, fetch = FetchType.LAZY)	
	@ManyToMany(fetch=FetchType.LAZY) //, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})		
	@JoinTable(name = "ADM_FUNCIONALIDADE_PERFIL", joinColumns = {
			@JoinColumn(name = "FPL_FUN_SEQ") }, inverseJoinColumns = { @JoinColumn(name = "FPL_PRF_SEQ") })	
	private List<AdmPerfil> admPerfils;

	/** The adm menus. */ 
	//bi-directional many-to-one association to AdmMenu	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy="admFuncionalidade", fetch = FetchType.LAZY) //, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	private List<AdmMenu> admMenus;


	/**
	 * Instantiates a new adm funcionalidade.
	 */
	public AdmFuncionalidade() {
		this.admPaginas = new ArrayList<AdmPagina>();
		this.admPerfils = new ArrayList<AdmPerfil>();
		this.admMenus = new ArrayList<AdmMenu>();
		limpar();
	}
	
	/**
	 * Instantiates a new adm funcionalidade.
	 *
	 * @param f
	 *            the f
	 */
	public AdmFuncionalidade(FuncionalidadeVO f) {
		this();
		
		this.id = f.getId();
		this.descricao = f.getDescricao();
		this.admPaginaInicial = new AdmPagina(f.getPaginaInicial());
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = null;
		this.descricao = null;
		this.admPaginaInicial = new AdmPagina();
		this.admPaginas.clear();
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
	 * Pega o the adm pagina inicial.
	 *
	 * @return o the adm pagina inicial
	 */
	public AdmPagina getAdmPaginaInicial() {
		return this.admPaginaInicial;
	}

	/**
	 * Atribui o the adm pagina inicial.
	 *
	 * @param admPaginaInicial
	 *            o novo the adm pagina inicial
	 */
	public void setAdmPaginaInicial(AdmPagina admPaginaInicial) {
		this.admPaginaInicial = admPaginaInicial;
	}

	/**
	 * Pega o the adm paginas.
	 *
	 * @return o the adm paginas
	 */
	public List<AdmPagina> getAdmPaginas() {
		return this.admPaginas;
	}

	/**
	 * Atribui o the adm paginas.
	 *
	 * @param admPaginas
	 *            o novo the adm paginas
	 */
	public void setAdmPaginas(List<AdmPagina> admPaginas) {
		this.admPaginas = admPaginas;
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

	/**
	 * Pega o the adm menus.
	 *
	 * @return o the adm menus
	 */
	public List<AdmMenu> getAdmMenus() {
		return this.admMenus;
	}

	
	/**
	 * Atribui o the adm menus.
	 *
	 * @param admMenus
	 *            o novo the adm menus
	 */
	public void setAdmMenus(List<AdmMenu> admMenus) {
		this.admMenus = admMenus;
	}	

	/**
	 * Adiciona o adm menus.
	 *
	 * @param admMenus
	 *            the adm menus
	 * @return the adm menu
	 */
	public AdmMenu addAdmMenus(AdmMenu admMenus) {
		getAdmMenus().add(admMenus);
		admMenus.setAdmFuncionalidade(this);

		return admMenus;
	}

	/**
	 * Remove o adm menus.
	 *
	 * @param admMenus
	 *            the adm menus
	 * @return the adm menu
	 */
	public AdmMenu removeAdmMenus(AdmMenu admMenus) {
		getAdmMenus().remove(admMenus);
		admMenus.setAdmFuncionalidade(null);

		return admMenus;
	}
	
	/**
	 * Gets the perfis funcionalidade.
	 *
	 * @return the perfis funcionalidade
	 */
	public String getPerfisFuncionalidade() {
		String ret = "";
		for (AdmPerfil item : getAdmPerfils()) {
			ret = ret.concat(item.getDescricao()).concat(", ");
		}
		if (ret != "") {
			ret = ret.substring(0, ret.length() - 2);
		}
		return ret;
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
		AdmFuncionalidade other = (AdmFuncionalidade) obj;
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
		return descricao;
	}
	
	/**
	 * To funcionalidade VO.
	 *
	 * @return the funcionalidade VO
	 */
	public FuncionalidadeVO toFuncionalidadeVO(){
		FuncionalidadeVO f = new FuncionalidadeVO();

		f.setId(id);
		f.setDescricao(descricao);
		if (admPaginaInicial!=null) {
			f.setPaginaInicial(admPaginaInicial.toPaginaVO());
		}
		
		/*
		for (AdmPagina admPagina : admPaginas) {
			f.getPaginas().add(admPagina.toPaginaVO());
		}
		
		for (AdmPerfil admPerfil : admPerfils) {
			f.getPerfils().add(admPerfil.toPerfilVO());
		}			
		for (AdmMenu admMenu : admMenus) {
			f.getMenus().add(admMenu.toMenuVO());
		}
		*/
		
		return f;
	}
	
}