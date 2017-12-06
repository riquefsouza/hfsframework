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
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.hfsframework.security.model.CargoVO;

@Entity
@Table(name = "ADM_CARGO")
@NamedQuery(name = "AdmCargo.findAll", query = "SELECT v FROM AdmCargo v")
public class AdmCargo implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "CAR_SEQ")
	private Long id;

	/** The nome cargo. */
	@Column(name = "CAR_DESCRICAO")
	private String nomeCargo;

	/* The adm perfils. */
	/* bi-directional many-to-many association to AdmPerfil
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "ADM_CARGO_PERFIL", joinColumns = {
			@JoinColumn(name = "CGP_COD_CARGO", referencedColumnName = "CAR_SEQ") }, inverseJoinColumns = {
					@JoinColumn(name = "CGP_PRF_SEQ") })
	private List<AdmPerfil> admPerfils;
	*/
	
	/* The vw adm funcionarios. */
	/* bi-directional many-to-many association to AdmFuncionario
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "ADM_CARGO_FUNCIONARIO", joinColumns = {
			@JoinColumn(name = "CFN_CAR_SEQ", referencedColumnName = "CAR_SEQ") }, inverseJoinColumns = {
					@JoinColumn(name = "CFN_FUN_CODIGO" , referencedColumnName = "FUN_CODIGO") })
	private List<AdmFuncionario> vwAdmFuncionarios;
	*/

	//@Transient
	//private List<AdmFuncionarioDTO> admFuncionarios;

	/**
	 * Instantiates a new vw adm cargo.
	 */
	public AdmCargo() {
		super();
		//this.admFuncionarios = new ArrayList<AdmFuncionarioDTO>();
		limpar();
	}

	/**
	 * Instantiates a new vw adm cargo.
	 *
	 * @param c
	 *            the c
	 */
	public AdmCargo(CargoVO c) {
		this();
		
		this.id = c.getId();
		this.nomeCargo = c.getNomeCargo();
		
		/*
		for (PerfilVO perfil : c.getPerfils()) {
			this.admPerfils.add(new AdmPerfil(perfil));	
		}
		for (FuncionarioVO funcionario : c.getFuncionarios()) {
			this.vwAdmFuncionarios.add(new AdmFuncionario(funcionario));
		}
		*/
	}
	
	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = null;
		this.nomeCargo = null;
		//this.admFuncionarios.clear();
	}


	/**
	 * Pega o the nome cargo.
	 *
	 * @return o the nome cargo
	 */
	public String getNomeCargo() {
		return this.nomeCargo;
	}

	/**
	 * Atribui o the nome cargo.
	 *
	 * @param nomeCargo
	 *            o novo the nome cargo
	 */
	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

	/*
	 * Pega o the adm perfils.
	 *
	 * @return o the adm perfils
	 *
	public List<AdmPerfil> getAdmPerfils() {
		return this.admPerfils;
	}

	**
	 * Atribui o the adm perfils.
	 *
	 * @param admPerfils
	 *            o novo the adm perfils
	 *
	public void setAdmPerfils(List<AdmPerfil> admPerfils) {
		this.admPerfils = admPerfils;
	}
*/
	/**
	 * Pega o the id.
	 *
	 * @return o the id
	 */
	public Long getId() {
		return id;
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

	/*
	 * Pega o the adm funcionarios.
	 *
	 * @return o the adm funcionarios
	 *
	public List<AdmFuncionarioDTO> getAdmFuncionarios() {
		return admFuncionarios;
	}

	**
	 * Atribui o the adm funcionarios.
	 *
	 * @param admFuncionarios
	 *            o novo the adm funcionarios
	 *
	public void setAdmFuncionarios(List<AdmFuncionarioDTO> admFuncionarios) {
		this.admFuncionarios = admFuncionarios;
	}
	*/	

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
		AdmCargo other = (AdmCargo) obj;
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

	/**
	 * To cargo VO.
	 *
	 * @return the cargo VO
	 */
	public CargoVO toCargoVO(){
		CargoVO c = new CargoVO();
		c.setId(id);
		c.setNomeCargo(nomeCargo);
		
		/*
		for (AdmPerfil admPerfil : admPerfils) {
			c.getPerfils().add(admPerfil.toPerfilVO());	
		}
		
		for (AdmFuncionario admFuncionario : vwAdmFuncionarios) {
			c.getFuncionarios().add(admFuncionario.toFuncionarioVO());
		}
		*/
		
		return c;
	}

}