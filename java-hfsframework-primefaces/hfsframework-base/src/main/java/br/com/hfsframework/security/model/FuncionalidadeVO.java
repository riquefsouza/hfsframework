/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class FuncionalidadeVO.
 */
public class FuncionalidadeVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The descricao. */
	private String descricao;

	/** The pagina inicial. */
	private PaginaVO paginaInicial;

	/** The paginas. */
	private List<PaginaVO> paginas;

	/** The perfils. */
	private List<PerfilVO> perfils;

	/** The menus. */
	private List<MenuVO> menus;

	/**
	 * Instantiates a new funcionalidade VO.
	 */
	public FuncionalidadeVO() {
		this.paginas = new ArrayList<PaginaVO>();
		this.perfils = new ArrayList<PerfilVO>();
		this.menus = new ArrayList<MenuVO>();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = null;
		this.descricao = null;
		this.paginaInicial = new PaginaVO();
		this.paginas.clear();
		this.perfils.clear();
		this.menus.clear();		
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the descricao.
	 *
	 * @return the descricao
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Sets the descricao.
	 *
	 * @param descricao
	 *            the new descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Gets the pagina inicial.
	 *
	 * @return the pagina inicial
	 */
	public PaginaVO getPaginaInicial() {
		return this.paginaInicial;
	}

	/**
	 * Sets the pagina inicial.
	 *
	 * @param paginaInicial
	 *            the new pagina inicial
	 */
	public void setPaginaInicial(PaginaVO paginaInicial) {
		this.paginaInicial = paginaInicial;
	}

	/**
	 * Gets the paginas.
	 *
	 * @return the paginas
	 */
	public List<PaginaVO> getPaginas() {
		return this.paginas;
	}

	/**
	 * Sets the paginas.
	 *
	 * @param paginas
	 *            the new paginas
	 */
	public void setPaginas(List<PaginaVO> paginas) {
		this.paginas = paginas;
	}

	/**
	 * Gets the perfils.
	 *
	 * @return the perfils
	 */
	public List<PerfilVO> getPerfils() {
		return this.perfils;
	}

	/**
	 * Sets the perfils.
	 *
	 * @param perfils
	 *            the new perfils
	 */
	public void setPerfils(List<PerfilVO> perfils) {
		this.perfils = perfils;
	}

	/**
	 * Gets the menus.
	 *
	 * @return the menus
	 */
	public List<MenuVO> getMenus() {
		return this.menus;
	}

	
	/**
	 * Sets the menus.
	 *
	 * @param menus
	 *            the new menus
	 */
	public void setMenus(List<MenuVO> menus) {
		this.menus = menus;
	}	

	/**
	 * Adiciona o adm menus.
	 *
	 * @param menus
	 *            the adm menus
	 * @return the adm menu
	 */
	public MenuVO addMenus(MenuVO menus) {
		getMenus().add(menus);
		menus.setFuncionalidade(this);

		return menus;
	}

	/**
	 * Remove o adm menus.
	 *
	 * @param menus
	 *            the adm menus
	 * @return the adm menu
	 */
	public MenuVO removeMenus(MenuVO menus) {
		getMenus().remove(menus);
		menus.setFuncionalidade(null);

		return menus;
	}
	
	/**
	 * Gets the perfis funcionalidade.
	 *
	 * @return the perfis funcionalidade
	 */
	public String getPerfisFuncionalidade() {
		String ret = "";
		for (PerfilVO item : getPerfils()) {
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
		FuncionalidadeVO other = (FuncionalidadeVO) obj;
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

}
