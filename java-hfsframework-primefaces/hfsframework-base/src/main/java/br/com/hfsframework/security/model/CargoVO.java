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
 * The Class CargoVO.
 */
public class CargoVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The nome cargo. */
	private String nomeCargo;

	/** The perfils. */
	private List<PerfilVO> perfils;

	/* The funcionarios. */
	//private List<FuncionarioVO> funcionarios;

	/**
	 * Instantiates a new vw adm cargo.
	 */
	public CargoVO() {
		super();
		perfils = new ArrayList<PerfilVO>();
		//funcionarios = new ArrayList<FuncionarioVO>(); 
	}

	/**
	 * Gets the nome cargo.
	 *
	 * @return the nome cargo
	 */
	public String getNomeCargo() {
		return this.nomeCargo;
	}

	/**
	 * Sets the nome cargo.
	 *
	 * @param nomeCargo
	 *            the new nome cargo
	 */
	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
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
	 * Gets the perfils.
	 *
	 * @return the perfils
	 */
	public List<PerfilVO> getPerfils() {
		return perfils;
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

	/*
	 * Gets the funcionarios.
	 *
	 * @return the funcionarios
	 *
	public List<FuncionarioVO> getFuncionarios() {
		return funcionarios;
	}

	**
	 * Sets the funcionarios.
	 *
	 * @param funcionarios
	 *            the new funcionarios
	 *
	public void setFuncionarios(List<FuncionarioVO> funcionarios) {
		this.funcionarios = funcionarios;
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
		CargoVO other = (CargoVO) obj;
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
		return this.nomeCargo;
	}


}
