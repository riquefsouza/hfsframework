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
	@NamedQuery(name = "AdmMenu.findMenuRaiz", query = "SELECT m FROM AdmMenu m left join m.admMenuPai mp left join m.admPagina f WHERE m.admMenuPai IS NULL ORDER BY m.ordem, mp.ordem"),
	@NamedQuery(name = "AdmMenu.findMenuRaizByDescricao", query = "SELECT m FROM AdmMenu m left join m.admMenuPai mp left join m.admPagina f WHERE m.admMenuPai IS NULL AND m.descricao = ?1 ORDER BY m.ordem, mp.ordem"),
	@NamedQuery(name = "AdmMenu.countMenuByPagina", query = "SELECT COUNT(m) FROM AdmMenu m WHERE m.admPagina = ?1"),
	@NamedQuery(name = "AdmMenu.findMenusFilhos", query = "SELECT m FROM AdmMenu m left join m.admMenuPai mp WHERE m.admMenuPai = ?1 ORDER BY m.ordem, mp.ordem"),
	@NamedQuery(name = "AdmMenu.findAdminMenuPaiByPagina", query="SELECT t FROM AdmMenu t WHERE t.id IN (SELECT m.admMenuPai.id FROM AdmMenu m WHERE m.admPagina = ?1 AND m.admMenuPai IS NULL AND m.id <= 9) ORDER BY t.id, t.ordem"),
	@NamedQuery(name = "AdmMenu.findMenuPaiByPagina", query="SELECT t FROM AdmMenu t WHERE t.id IN (SELECT m.admMenuPai.id FROM AdmMenu m WHERE m.admPagina = ?1 AND m.admMenuPai IS NULL AND m.id > 9) ORDER BY t.id, t.ordem"),
	@NamedQuery(name = "AdmMenu.findPaginaByMenu", query="SELECT m.admPagina FROM AdmMenu m WHERE m.admPagina = ?1 AND m.id = ?2")
})
public class AdmMenu implements Serializable, Comparable<AdmMenu> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@SequenceGenerator(name = "ADM_MENU_ID_GENERATOR", sequenceName="ADM_MENU_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_MENU_ID_GENERATOR")
	@Column(name = "MNU_SEQ")
	private Long id;

	/** The descricao. */
	@Column(name = "MNU_DESCRICAO", unique = true)
	private String descricao;

	/** The ordem. */
	@Column(name = "MNU_ORDEM")
	private Integer ordem;

	/** The id pagina. */
	@Column(name = "MNU_PAG_SEQ", nullable = true)
	private Long idPagina;

	/** The adm pagina. */
	// bi-directional many-to-one association to AdmPagina
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "MNU_PAG_SEQ", nullable = false, insertable = false, updatable = false)
	private AdmPagina admPagina;

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
		this.idPagina = null;
		this.admPagina = new AdmPagina();
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
	 * Pega o the adm pagina.
	 *
	 * @return o the adm pagina
	 */
	public AdmPagina getAdmPagina() {
		return this.admPagina;
	}

	/**
	 * Atribui o the adm pagina.
	 *
	 * @param admPagina
	 *            o novo the adm pagina
	 */
	public void setAdmPagina(AdmPagina admPagina) {
		this.admPagina = admPagina;
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
		return getAdmPagina() == null;
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
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return this.admPagina != null ? this.admPagina.getUrl() : null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.descricao;
	}

	/**
	 * Pega o the id pagina.
	 *
	 * @return o the id pagina
	 */
	public Long getIdPagina() {
		return idPagina;
	}

	/**
	 * Atribui o the id pagina.
	 *
	 * @param idPagina
	 *            o novo the id pagina
	 */
	public void setIdPagina(Long idPagina) {
		this.idPagina = idPagina;
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
		m.setIdPagina(idPagina);
		if (admPagina!=null) {
				m.setPagina(admPagina.toPaginaVO());
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