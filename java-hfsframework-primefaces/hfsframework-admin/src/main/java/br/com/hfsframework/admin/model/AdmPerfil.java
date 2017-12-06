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
import javax.persistence.Convert;
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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.hfsframework.converters.BooleanToStringConverter;
import br.com.hfsframework.security.model.CargoVO;
import br.com.hfsframework.security.model.FuncionalidadeVO;
import br.com.hfsframework.security.model.FuncionarioVO;
import br.com.hfsframework.security.model.PaginaVO;
import br.com.hfsframework.security.model.PerfilVO;

/**
 * The persistent class for the ADM_PERFIL database table.
 * 
 */
@Entity
@Table(name = "ADM_PERFIL")
@NamedQueries({
	@NamedQuery(name = "AdmPerfil.getDescricaoById", query = "SELECT c.descricao FROM AdmPerfil c WHERE c.id = ?1"),
	@NamedQuery(name = "AdmPerfil.countNovo", query = "SELECT COUNT(c) FROM AdmPerfil c WHERE LOWER(c.descricao) = ?1"),
	@NamedQuery(name = "AdmPerfil.countAntigo", query = "SELECT COUNT(c) FROM AdmPerfil c WHERE LOWER(c.descricao) <> ?1 AND LOWER(c.descricao) = ?2"),
	@NamedQuery(name = "AdmPerfil.findCargosPorPerfil", query = "SELECT distinct cp.id.codCargo FROM AdmPerfil p, AdmCargoPerfil cp where p.id = cp.id.perfilSeq AND p = ?1"),
	@NamedQuery(name = "AdmPerfil.findPaginasPorPerfil", query = "SELECT distinct pag FROM AdmPerfil p inner join p.admPaginas pag where p = ?1"),
	@NamedQuery(name = "AdmPerfil.findFuncionariosPorPerfil", query = "SELECT distinct fp.id.codFuncionario FROM AdmPerfil p, AdmFuncionarioPerfil fp where p.id = fp.id.perfilSeq AND p = ?1"),
	@NamedQuery(name = "AdmPerfil.findFuncionalidadesPorPerfil", query = "SELECT distinct f FROM AdmPerfil p inner join p.admFuncionalidades f  where p = ?1"),
	@NamedQuery(name = "AdmPerfil.findPerfisPorFuncionario", query = "SELECT distinct p FROM AdmPerfil p, AdmFuncionarioPerfil fp where p.id = fp.id.perfilSeq AND fp.id.codFuncionario = ?1"),	
	@NamedQuery(name = "AdmPerfil.findAdminMenuPaiByPerfil", query="SELECT DISTINCT t FROM AdmMenu t WHERE t.id IN (SELECT m.admMenuPai.id FROM AdmPerfil p INNER JOIN p.admFuncionalidades f INNER JOIN f.admMenus m WHERE p = ?1 AND m.id <= 14) ORDER BY t.id, t.ordem"),
	@NamedQuery(name = "AdmPerfil.findMenuPaiByPerfil", query="SELECT DISTINCT t FROM AdmMenu t WHERE t.id IN (SELECT m.admMenuPai.id FROM AdmPerfil p INNER JOIN p.admFuncionalidades f INNER JOIN f.admMenus m WHERE p = ?1 AND m.id > 14) ORDER BY t.id, t.ordem"),
	@NamedQuery(name = "AdmPerfil.findAdminMenuByPerfil", query="SELECT DISTINCT m FROM AdmPerfil p INNER JOIN p.admFuncionalidades f INNER JOIN f.admMenus m WHERE p = ?1 AND m.id <= 14 AND m.admMenuPai = ?2 ORDER BY m.id, m.ordem"),
	@NamedQuery(name = "AdmPerfil.findMenuByPerfil", query="SELECT DISTINCT m FROM AdmPerfil p INNER JOIN p.admFuncionalidades f INNER JOIN f.admMenus m WHERE p = ?1 AND m.id > 14 AND m.admMenuPai = ?2 ORDER BY m.id, m.ordem")
})
public class AdmPerfil implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GenericGenerator(name = "ADM_PERFIL_ID_GENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "ADM_PERFIL_SEQ"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@SequenceGenerator(name = "ADM_PERFIL_ID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_PERFIL_ID_GENERATOR")
	@Column(name = "PRF_SEQ")
	private Long id;

	/** The administrador. */
	@Convert(converter = BooleanToStringConverter.class)
	@Column(name = "PRF_ADMINISTRADOR")
	private Boolean administrador;

	/** The descricao. */
	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name = "PRF_DESCRICAO", unique = true)
	private String descricao;

	/** The geral. */
	@Convert(converter = BooleanToStringConverter.class)
	@Column(name = "PRF_GERAL")
	private Boolean geral;

	/** The adm cargo perfils. */
	// bi-directional many-to-one association to AdmCargoPerfil
	//@Fetch(FetchMode.SUBSELECT)
	//@OneToMany(mappedBy = "admPerfil", fetch = FetchType.LAZY) //, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})	
	//private List<AdmCargoPerfil> admCargoPerfils;

	@ManyToMany(fetch = FetchType.LAZY) //, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinTable(name = "ADM_FUNCIONALIDADE_PERFIL", joinColumns = {
			@JoinColumn(name = "FPL_PRF_SEQ") }, inverseJoinColumns = { @JoinColumn(name = "FPL_FUN_SEQ") })
	private List<AdmFuncionalidade> admFuncionalidades;

	/** The adm paginas. */
	@ManyToMany(fetch = FetchType.LAZY) //, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinTable(name = "ADM_PAGINA_PERFIL", joinColumns = { 
			@JoinColumn(name = "PGL_PRF_SEQ") }, inverseJoinColumns = {	@JoinColumn(name = "PGL_PAG_SEQ") })
	private List<AdmPagina> admPaginas;
	
	/** The adm cargos. */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ADM_CARGO_PERFIL", joinColumns = {
			@JoinColumn(name = "CGP_PRF_SEQ") }, inverseJoinColumns = {
					@JoinColumn(name = "CGP_CAR_SEQ", referencedColumnName = "CAR_SEQ") })				
	private List<AdmCargo> admCargos;
	
	/** The adm funcionarios. */
	@ManyToMany(fetch = FetchType.LAZY) //, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinTable(name = "ADM_FUNCIONARIO_PERFIL", joinColumns = {
			@JoinColumn(name = "USP_PRF_SEQ") }, inverseJoinColumns = { @JoinColumn(name = "USP_FUN_CODIGO") })
	private List<AdmFuncionario> admFuncionarios;
		

	/**
	 * Instantiates a new adm perfil.
	 */
	public AdmPerfil() {
		super();
		//this.admCargoPerfils = new ArrayList<AdmCargoPerfil>();
		this.admFuncionalidades = new ArrayList<AdmFuncionalidade>();
		this.admPaginas = new ArrayList<AdmPagina>();
		this.admFuncionarios = new ArrayList<AdmFuncionario>();
		this.admCargos = new ArrayList<AdmCargo>();
		/*
		this.admFuncionarioPerfils = new ArrayList<AdmFuncionarioPerfil>();						
		*/
		limpar();
	}
	
	/**
	 * Instantiates a new adm perfil.
	 *
	 * @param p
	 *            the p
	 */
	public AdmPerfil(PerfilVO p) {
		this();
		
		this.id = p.getId();
		this.administrador = p.getAdministrador();
		this.descricao = p.getDescricao();
		this.geral = p.getGeral();
		
		for (FuncionalidadeVO funcionalidade : p.getFuncionalidades()) {
			this.admFuncionalidades.add(new AdmFuncionalidade(funcionalidade));	
		}
		for (PaginaVO pagina : p.getPaginas()) {
			this.admPaginas.add(new AdmPagina(pagina));	
		}
		for (FuncionarioVO funcionario : p.getFuncionarios()) {
			this.admFuncionarios.add(new AdmFuncionario(funcionario));	
		}
		for (CargoVO cargo : p.getCargos()) {
			this.admCargos.add(new AdmCargo(cargo));	
		}
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = null;
		this.administrador = null;
		this.descricao = null;
		this.geral = null;
		//this.admCargoPerfils.clear();
		this.admFuncionalidades.clear();
		this.admPaginas.clear();
		this.admFuncionarios.clear();
		this.admCargos.clear();
		//this.admFuncionarioPerfils.clear();		
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
	 * Pega o the administrador.
	 *
	 * @return o the administrador
	 */
	public Boolean getAdministrador() {
		return this.administrador;
	}

	/**
	 * Atribui o the administrador.
	 *
	 * @param administrador
	 *            o novo the administrador
	 */
	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
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
	 * Pega o the geral.
	 *
	 * @return o the geral
	 */
	public Boolean getGeral() {
		return this.geral;
	}

	/**
	 * Atribui o the geral.
	 *
	 * @param geral
	 *            o novo the geral
	 */
	public void setGeral(Boolean geral) {
		this.geral = geral;
	}

	/*
	 * Pega o the adm cargo perfils.
	 *
	 * @return o the adm cargo perfils
	 *
	public List<AdmCargoPerfil> getAdmCargoPerfils() {
		return this.admCargoPerfils;
	}

	**
	 * Atribui o the adm cargo perfils.
	 *
	 * @param admCargoPerfils
	 *            o novo the adm cargo perfils
	 * 
	public void setAdmCargoPerfils(List<AdmCargoPerfil> admCargoPerfils) {
		this.admCargoPerfils = admCargoPerfils;
	}
	

	**
	 * Adiciona o adm cargo perfil.
	 *
	 * @param admCargoPerfil
	 *            the adm cargo perfil
	 * @return the adm cargo perfil
	 *
	public AdmCargoPerfil addAdmCargoPerfil(AdmCargoPerfil admCargoPerfil) {
		getAdmCargoPerfils().add(admCargoPerfil);
		admCargoPerfil.setAdmPerfil(this);

		return admCargoPerfil;
	}

	**
	 * Remove o adm cargo perfil.
	 *
	 * @param admCargoPerfil
	 *            the adm cargo perfil
	 * @return the adm cargo perfil
	 *
	public AdmCargoPerfil removeAdmCargoPerfil(AdmCargoPerfil admCargoPerfil) {
		getAdmCargoPerfils().remove(admCargoPerfil);
		admCargoPerfil.setAdmPerfil(null);

		return admCargoPerfil;
	}
	*/
	
	/**
	 * Pega o the adm cargo perfils.
	 *
	 * @return o the adm cargo perfils
	 */
	public List<AdmFuncionalidade> getAdmFuncionalidades() {
		return this.admFuncionalidades;
	}

	/**
	 * Atribui o the adm cargo perfils.
	 *
	 * @param admFuncionalidades
	 *            o novo the adm cargo perfils
	 */
	public void setAdmFuncionalidades(List<AdmFuncionalidade> admFuncionalidades) {
		this.admFuncionalidades = admFuncionalidades;
	}

	/*
	 * Pega o the adm funcionario perfils.
	 *
	 * @return o the adm funcionario perfils
	 *
	public List<AdmFuncionarioPerfil> getAdmFuncionarioPerfils() {
		return this.admFuncionarioPerfils;
	}

	/**
	 * Atribui o the adm funcionario perfils.
	 *
	 * @param admFuncionarioPerfils
	 *            o novo the adm funcionario perfils
	 *
	public void setAdmFuncionarioPerfils(List<AdmFuncionarioPerfil> admFuncionarioPerfils) {
		this.admFuncionarioPerfils = admFuncionarioPerfils;
	}

	/**
	 * Adiciona o adm funcionario perfil.
	 *
	 * @param admFuncionarioPerfil
	 *            the adm funcionario perfil
	 * @return the adm funcionario perfil
	 *
	public AdmFuncionarioPerfil addAdmFuncionarioPerfil(AdmFuncionarioPerfil admFuncionarioPerfil) {
		getAdmFuncionarioPerfils().add(admFuncionarioPerfil);
		admFuncionarioPerfil.setAdmPerfil(this);

		return admFuncionarioPerfil;
	}

	/**
	 * Remove o adm funcionario perfil.
	 *
	 * @param admFuncionarioPerfil
	 *            the adm funcionario perfil
	 * @return the adm funcionario perfil
	 *
	public AdmFuncionarioPerfil removeAdmFuncionarioPerfil(AdmFuncionarioPerfil admFuncionarioPerfil) {
		getAdmFuncionarioPerfils().remove(admFuncionarioPerfil);
		admFuncionarioPerfil.setAdmPerfil(null);

		return admFuncionarioPerfil;
	}
*/
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
	 * Gets the adm cargos.
	 *
	 * @return the adm cargos
	 */
	public List<AdmCargo> getAdmCargos() {
		return this.admCargos;
	}

	/**
	 * Sets the adm cargos.
	 *
	 * @param admCargos
	 *            the new adm cargos
	 */
	public void setAdmCargos(List<AdmCargo> admCargos) {
		this.admCargos = admCargos;
	}
	
	/**
	 * Gets the adm funcionarios.
	 *
	 * @return the adm funcionarios
	 */
	public List<AdmFuncionario> getAdmFuncionarios() {
		return admFuncionarios;
	}

	/**
	 * Sets the adm funcionarios.
	 *
	 * @param admFuncionarios
	 *            the new adm funcionarios
	 */
	public void setAdmFuncionarios(List<AdmFuncionario> admFuncionarios) {
		this.admFuncionarios = admFuncionarios;
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
		AdmPerfil other = (AdmPerfil) obj;
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
	 * To perfil VO.
	 *
	 * @return the perfil VO
	 */
	public PerfilVO toPerfilVO(){
		PerfilVO p = new PerfilVO();
		
		p.setId(id);
		p.setAdministrador(administrador);
		p.setDescricao(descricao);
		p.setGeral(geral);

		for (AdmFuncionalidade admFuncionalidade : admFuncionalidades) {
			p.getFuncionalidades().add(admFuncionalidade.toFuncionalidadeVO());	
		}
		
		for (AdmPagina admPagina : admPaginas) {
			p.getPaginas().add(admPagina.toPaginaVO());
		}

		for (AdmFuncionario admFuncionario : admFuncionarios) {
			p.getFuncionarios().add(admFuncionario.toFuncionarioVO());
		}
		
		for (AdmCargo admCargo : admCargos) {
			p.getCargos().add(admCargo.toCargoVO());
		}
		
		return p;
	}
}