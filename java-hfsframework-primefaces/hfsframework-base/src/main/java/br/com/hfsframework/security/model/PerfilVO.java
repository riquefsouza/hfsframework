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
 * The Class PerfilVO.
 */
public class PerfilVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The administrador. */
	private Boolean administrador;

	/** The descricao. */
	private String descricao;

	/** The geral. */
	private Boolean geral;

	/** The funcionalidades. */
	private List<FuncionalidadeVO> funcionalidades;

	/** The paginas. */
	private List<PaginaVO> paginas;
	
	/** The cargos. */
	private List<CargoVO> cargos;
	
	/** The funcionarios. */
	private List<FuncionarioVO> funcionarios;
		
	/**
	 * Instantiates a new perfil VO.
	 */
	public PerfilVO() {
		this.funcionalidades = new ArrayList<FuncionalidadeVO>();
		this.paginas = new ArrayList<PaginaVO>();
		this.funcionarios = new ArrayList<FuncionarioVO>();
		this.cargos = new ArrayList<CargoVO>();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = null;
		this.administrador = null;
		this.descricao = null;
		this.geral = null;
		this.funcionalidades.clear();
		this.paginas.clear();
		this.funcionarios.clear();
		this.cargos.clear();
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
	 * Gets the administrador.
	 *
	 * @return the administrador
	 */
	public Boolean getAdministrador() {
		return this.administrador;
	}

	/**
	 * Sets the administrador.
	 *
	 * @param administrador
	 *            the new administrador
	 */
	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
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
	 * Gets the geral.
	 *
	 * @return the geral
	 */
	public Boolean getGeral() {
		return this.geral;
	}

	/**
	 * Sets the geral.
	 *
	 * @param geral
	 *            the new geral
	 */
	public void setGeral(Boolean geral) {
		this.geral = geral;
	}

	/**
	 * Gets the funcionalidades.
	 *
	 * @return the funcionalidades
	 */
	public List<FuncionalidadeVO> getFuncionalidades() {
		return funcionalidades;
	}

	/**
	 * Sets the funcionalidades.
	 *
	 * @param funcionalidades
	 *            the new funcionalidades
	 */
	public void setFuncionalidades(List<FuncionalidadeVO> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	/**
	 * Gets the paginas.
	 *
	 * @return the paginas
	 */
	public List<PaginaVO> getPaginas() {
		return paginas;
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
	 * Gets the cargos.
	 *
	 * @return the cargos
	 */
	public List<CargoVO> getCargos() {
		return cargos;
	}

	/**
	 * Sets the cargos.
	 *
	 * @param cargos
	 *            the new cargos
	 */
	public void setCargos(List<CargoVO> cargos) {
		this.cargos = cargos;
	}

	/**
	 * Gets the funcionarios.
	 *
	 * @return the funcionarios
	 */
	public List<FuncionarioVO> getFuncionarios() {
		return funcionarios;
	}

	/**
	 * Sets the funcionarios.
	 *
	 * @param funcionarios
	 *            the new funcionarios
	 */
	public void setFuncionarios(List<FuncionarioVO> funcionarios) {
		this.funcionarios = funcionarios;
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
		PerfilVO other = (PerfilVO) obj;
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
