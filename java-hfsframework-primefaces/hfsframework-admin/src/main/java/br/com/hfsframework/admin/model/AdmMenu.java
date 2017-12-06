/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.com.hfsframework.security.model.MenuVO;

/**
 * The persistent class for the ADM_MENU database table.
 * 
 */
@Entity
@Table(name = "ADM_MENU")
@NamedQueries({
	@NamedQuery(name = "AdmMenu.getDescricaoById", query = "SELECT c.descricao FROM AdmMenu c WHERE c.id = ?1"),
	@NamedQuery(name = "AdmMenu.countNovo", query = "SELECT COUNT(c) FROM AdmMenu c WHERE LOWER(c.descricao) = ?1"),
	@NamedQuery(name = "AdmMenu.countAntigo", query = "SELECT COUNT(c) FROM AdmMenu c WHERE LOWER(c.descricao) <> ?1 AND LOWER(c.descricao) = ?2"),	
	@NamedQuery(name = "AdmMenu.findMenuRaiz", query = "SELECT m FROM AdmMenu m left join m.admMenuPai mp left join m.admFuncionalidade f WHERE m.admMenuPai IS NULL ORDER BY m.ordem, mp.ordem"),
	@NamedQuery(name = "AdmMenu.findMenuRaizByDescricao", query = "SELECT m FROM AdmMenu m left join m.admMenuPai mp left join m.admFuncionalidade f WHERE m.admMenuPai IS NULL AND m.descricao = ?1 ORDER BY m.ordem, mp.ordem"),
	@NamedQuery(name = "AdmMenu.countMenuByFuncionalidade", query = "SELECT COUNT(m) FROM AdmMenu m WHERE m.admFuncionalidade = ?1"),
	@NamedQuery(name = "AdmMenu.findMenusFilhos", query = "SELECT m FROM AdmMenu m left join m.admMenuPai mp WHERE m.admMenuPai = ?1 ORDER BY m.ordem, mp.ordem"),
	@NamedQuery(name = "AdmMenu.findAdminMenuPaiByFuncionalidade", query="SELECT t FROM AdmMenu t WHERE t.id IN (SELECT m.admMenuPai.id FROM AdmMenu m WHERE m.admFuncionalidade = ?1 AND m.admMenuPai IS NULL AND m.id <= 14) ORDER BY t.id, t.ordem"),
	@NamedQuery(name = "AdmMenu.findMenuPaiByFuncionalidade", query="SELECT t FROM AdmMenu t WHERE t.id IN (SELECT m.admMenuPai.id FROM AdmMenu m WHERE m.admFuncionalidade = ?1 AND m.admMenuPai IS NULL AND m.id > 14) ORDER BY t.id, t.ordem"),
	@NamedQuery(name = "AdmMenu.findPaginaByMenu", query="SELECT m.admFuncionalidade.admPaginaInicial FROM AdmMenu m WHERE m.admFuncionalidade = ?1 AND m.id = ?2")
	//@NamedQuery(name = "AdmMenu.findFuncionariosPorMenu", query = "SELECT distinct f FROM AdmMenu m inner join m.vwAdmFuncionarios f where m = ?1"),
})
public class AdmMenu implements Serializable, Comparable<AdmMenu> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GenericGenerator(name = "ADM_MENU_ID_GENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "ADM_MENU_SEQ"), @Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@SequenceGenerator(name = "ADM_MENU_ID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_MENU_ID_GENERATOR")
	@Column(name = "MNU_SEQ")
	private Long id;

	/** The descricao. */
	@Column(name = "MNU_DESCRICAO", unique = true)
	private String descricao;

	/** The ordem. */
	@Column(name = "MNU_ORDEM")
	private Integer ordem;

	/** The id funcionalidade. */
	@Column(name = "MNU_FUN_SEQ", nullable = true)
	private Long idFuncionalidade;

	/** The adm funcionalidade. */
	// bi-directional many-to-one association to AdmFuncionalidade
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "MNU_FUN_SEQ", nullable = false, insertable = false, updatable = false)
	private AdmFuncionalidade admFuncionalidade;

	/** The adm menu. */
	// bi-directional many-to-one association to AdmMenu
	@ManyToOne(fetch = FetchType.LAZY) //(cascade={CascadeType.ALL}) //
	@JoinColumn(name = "MNU_PAI_SEQ") //, nullable = false, insertable = false, updatable = false)
	private AdmMenu admMenuPai;

	/** The adm menus. */
	// bi-directional many-to-one association to AdmMenu
	//@OrderBy("ordem")
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "admMenuPai", fetch = FetchType.EAGER)	
	private List<AdmMenu> admSubMenus;

	/* The adm menu funcionarios. *
	// bi-directional many-to-one association to AdmMenuFuncionario
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "admMenu", fetch = FetchType.LAZY)	
	private List<AdmMenuFuncionario> admMenuFuncionarios;

	** The adm funcionarios. *
	// bi-directional many-to-many association to AdmFuncionario
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ADM_MENU_FUNCIONARIO", joinColumns = {
			@JoinColumn(name = "MNF_MNU_SEQ") }, inverseJoinColumns = { @JoinColumn(name = "MNF_COD_FUNCIONARIO") })
	private List<AdmFuncionarioDTO> vwAdmFuncionarios;
	*/
	
	/**
	 * Instantiates a new adm menu.
	 */
	public AdmMenu() {
		this.admSubMenus = new ArrayList<AdmMenu>();
		//this.vwAdmFuncionarios = new ArrayList<AdmFuncionarioDTO>();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = null;
		this.descricao = null;
		this.ordem = null;
		this.idFuncionalidade = null;
		this.admFuncionalidade = new AdmFuncionalidade();
		this.admMenuPai = null; // new AdmMenu();
		this.admSubMenus.clear();
		//this.admMenuFuncionarios.clear();		
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
	public Integer getOrdem() {
		return this.ordem;
	}

	/**
	 * Atribui o the ordem.
	 *
	 * @param ordem
	 *            o novo the ordem
	 */
	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	/**
	 * Pega o the adm funcionalidade.
	 *
	 * @return o the adm funcionalidade
	 */
	public AdmFuncionalidade getAdmFuncionalidade() {
		return this.admFuncionalidade;
	}

	/**
	 * Atribui o the adm funcionalidade.
	 *
	 * @param admFuncionalidade
	 *            o novo the adm funcionalidade
	 */
	public void setAdmFuncionalidade(AdmFuncionalidade admFuncionalidade) {
		this.admFuncionalidade = admFuncionalidade;
	}

	/**
	 * Pega o the adm menu.
	 *
	 * @return o the adm menu
	 */
	public AdmMenu getAdmMenuPai() {
		return this.admMenuPai;
	}

	/**
	 * Atribui o the adm menu.
	 *
	 * @param admMenuPai
	 *            o novo the adm menu
	 */
	public void setAdmMenuPai(AdmMenu admMenuPai) {
		this.admMenuPai = admMenuPai;
	}

	/**
	 * Pega o the adm menus.
	 *
	 * @return o the adm menus
	 */
	public List<AdmMenu> getAdmSubMenus() {
		if (this.admSubMenus!=null && !this.admSubMenus.isEmpty()){
			Collections.sort(this.admSubMenus, new Comparator<AdmMenu>() {
				@Override
				public int compare(AdmMenu o1, AdmMenu o2) {
					return o1.getOrdem().compareTo(o2.getOrdem());
				}
			});
		}
		return this.admSubMenus;
	}

	/**
	 * Atribui o the adm menus.
	 *
	 * @param admSubMenus
	 *            o novo the adm menus
	 */
	public void setAdmSubMenus(List<AdmMenu> admSubMenus) {
		this.admSubMenus = admSubMenus;
	}

	/**
	 * Adiciona o adm sub menus.
	 *
	 * @param admSubMenus
	 *            the adm sub menus
	 * @return the adm menu
	 */
	public AdmMenu addAdmSubMenus(AdmMenu admSubMenus) {
		getAdmSubMenus().add(admSubMenus);
		admSubMenus.setAdmMenuPai(this);

		return admSubMenus;
	}

	/**
	 * Remove o adm sub menus.
	 *
	 * @param admSubMenus
	 *            the adm sub menus
	 * @return the adm menu
	 */
	public AdmMenu removeAdmSubMenus(AdmMenu admSubMenus) {
		getAdmSubMenus().remove(admSubMenus);
		admSubMenus.setAdmMenuPai(null);

		return admSubMenus;
	}

	/*
	 * Pega o the adm menu funcionarios.
	 *
	 * @return o the adm menu funcionarios
	 *
	public List<AdmMenuFuncionario> getAdmMenuFuncionarios() {
		return this.admMenuFuncionarios;
	}

	**
	 * Atribui o the adm menu funcionarios.
	 *
	 * @param admMenuFuncionarios
	 *            o novo the adm menu funcionarios
	 *
	public void setAdmMenuFuncionarios(List<AdmMenuFuncionario> admMenuFuncionarios) {
		this.admMenuFuncionarios = admMenuFuncionarios;
	}

	**
	 * Adiciona o adm menu funcionario.
	 *
	 * @param admMenuFuncionario
	 *            the adm menu funcionario
	 * @return the adm menu funcionario
	 *
	public AdmMenuFuncionario addAdmMenuFuncionario(AdmMenuFuncionario admMenuFuncionario) {
		getAdmMenuFuncionarios().add(admMenuFuncionario);
		admMenuFuncionario.setAdmMenu(this);

		return admMenuFuncionario;
	}

	**
	 * Remove o adm menu funcionario.
	 *
	 * @param admMenuFuncionario
	 *            the adm menu funcionario
	 * @return the adm menu funcionario
	 *
	public AdmMenuFuncionario removeAdmMenuFuncionario(AdmMenuFuncionario admMenuFuncionario) {
		getAdmMenuFuncionarios().remove(admMenuFuncionario);
		admMenuFuncionario.setAdmMenu(null);

		return admMenuFuncionario;
	}
	 */
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admMenuPai == null) ? 0 : admMenuPai.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		AdmMenu other = (AdmMenu) obj;
		if (admMenuPai == null) {
			if (other.admMenuPai != null)
				return false;
		} else if (!admMenuPai.equals(other.admMenuPai))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * Checks if is sub menu.
	 *
	 * @return true, if is sub menu
	 */
	public boolean isSubMenu() {
		return getAdmFuncionalidade() == null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(AdmMenu m) {
		return getDescricao().compareTo(m.getDescricao());
	}

	/**
	 * Gets the managed bean.
	 *
	 * @return the managed bean
	 */
	public String getManagedBean() {
		return this.admFuncionalidade != null ? this.admFuncionalidade.getAdmPaginaInicial().getManagedBean() : null;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return this.admFuncionalidade != null ? this.admFuncionalidade.getAdmPaginaInicial().getUrl() : null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.descricao;
	}

	/**
	 * Pega o the id funcionalidade.
	 *
	 * @return o the id funcionalidade
	 */
	public Long getIdFuncionalidade() {
		return idFuncionalidade;
	}

	/**
	 * Atribui o the id funcionalidade.
	 *
	 * @param idFuncionalidade
	 *            o novo the id funcionalidade
	 */
	public void setIdFuncionalidade(Long idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}

	/**
	 * To menu VO.
	 *
	 * @return the menu VO
	 */
	public MenuVO toMenuVO(){
		MenuVO m = new MenuVO();
		
		m.setId(id);
		m.setDescricao(descricao);
		m.setOrdem(ordem);
		m.setIdFuncionalidade(idFuncionalidade);
		if (admFuncionalidade!=null) {
				m.setFuncionalidade(admFuncionalidade.toFuncionalidadeVO());
		}
		/*
		if (admMenuPai!=null) {
			m.setMenuPai(admMenuPai.toMenuVO());
		}
		*/
		
		for (AdmMenu admSubMenu : admSubMenus) {
			m.getSubMenus().add(admSubMenu.toMenuVO());
		}
		
		return m;
	}
	
}