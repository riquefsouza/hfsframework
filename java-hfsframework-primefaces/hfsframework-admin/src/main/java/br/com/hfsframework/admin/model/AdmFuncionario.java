/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.hfsframework.converters.BooleanToStringConverter;
import br.com.hfsframework.security.model.FuncionarioVO;
import br.com.hfsframework.util.CPFCNPJUtil;

@Entity
@Table(name = "ADM_FUNCIONARIO")
@NamedQuery(name = "AdmFuncionario.findByMatriculaAndCPF", query = "SELECT f FROM AdmFuncionario f WHERE f.id = ?1 AND f.cpf = ?2")
public class AdmFuncionario implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "FUN_CODIGO")
	private Long id;

	/** The ativo. */
	@Convert(converter=BooleanToStringConverter.class)
	@Column(name = "FUN_ATIVO")
	private Boolean ativo;

	/** The celular. */
	@Column(name = "FUN_CELULAR")
	private String celular;

	/** The cod cargo. */
	@Column(name = "FUN_CAR_SEQ")
	private Long codCargoPrincipal;

	/** The cpf. */
	@Column(name = "FUN_CPF")
	private BigDecimal cpf;

	/** The data admissao. */
	@Temporal(TemporalType.DATE)
	@Column(name = "FUN_DATA_ADMISSAO")
	private Date dataAdmissao;

	/** The data saida. */
	@Temporal(TemporalType.DATE)
	@Column(name = "FUN_DATA_SAIDA")
	private Date dataSaida;

	/** The email. */
	@Column(name = "FUN_EMAIL")
	private String email;

	/** The nome. */
	@Column(name = "FUN_NOME")
	private String nome;

	/** The setor. */
	@Column(name = "FUN_SET_SIGLA")
	private String setor;

	/** The telefone. */
	@Column(name = "FUN_TELEFONE")
	private String telefone;

	/** The vw adm cargo principal. */
	//bi-directional many-to-one association to AdmCargo
	@ManyToOne(optional = false, fetch=FetchType.EAGER, cascade = CascadeType.REFRESH) 
	@JoinColumn(name="FUN_CAR_SEQ", nullable=false, insertable = false, updatable = false)
	private AdmCargo admCargoPrincipal;
			
	/* The vw adm cargos. 
	//@ManyToMany(mappedBy="vwAdmFuncionarios", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "ADM_CARGO_FUNCIONARIO", joinColumns = {
			@JoinColumn(name = "CFN_FUN_CODIGO", referencedColumnName = "FUN_CODIGO") }, inverseJoinColumns = {
					@JoinColumn(name = "CFN_CAR_SEQ" , referencedColumnName = "CAR_SEQ") })	
	private List<AdmCargo> vwAdmCargos;
	*/
	
	//@Transient
	//private List<AdmCargoDTO> admCargos;
	
	/**
	 * Instantiates a new adm funcionario.
	 */
	public AdmFuncionario() {
		super();
		//this.admCargos = new ArrayList<AdmCargo>();
		limpar();
	}
	
	/**
	 * Instantiates a new vw adm funcionario.
	 *
	 * @param f
	 *            the f
	 */
	public AdmFuncionario(FuncionarioVO f) {
		this();
		
		this.id = f.getId();
		this.ativo = f.getAtivo();
		this.celular = f.getCelular();
		this.codCargoPrincipal = f.getCodCargoPrincipal();
		this.cpf = f.getCpf();
		this.dataAdmissao = f.getDataAdmissao();
		this.dataSaida = f.getDataSaida();
		this.email = f.getEmail();
		this.nome = f.getNome();
		this.setor = f.getSetor();
		this.telefone = f.getTelefone();
		this.admCargoPrincipal = new AdmCargo(f.getCargoPrincipal());
		/*
		for (CargoVO cargo : f.getCargos()) {
			this.admCargos.add(new AdmCargoDTO(cargo));	
		}
		*/
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = 0L;
		this.ativo = false;
		this.celular = "";
		this.codCargoPrincipal = 0L;
		this.cpf = BigDecimal.ZERO;
		this.dataAdmissao = new Date();
		this.dataSaida = new Date();
		this.email = "";
		this.nome = "";
		this.setor = "";
		this.telefone = "";
		this.admCargoPrincipal = new AdmCargo();
		//this.admCargos.clear();
	}

	/**
	 * Pega o the ativo.
	 *
	 * @return o the ativo
	 */
	public Boolean getAtivo() {
		return this.ativo;
	}

	/**
	 * Atribui o the ativo.
	 *
	 * @param ativo
	 *            o novo the ativo
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * Pega o the celular.
	 *
	 * @return o the celular
	 */
	public String getCelular() {
		return this.celular;
	}

	/**
	 * Atribui o the celular.
	 *
	 * @param celular
	 *            o novo the celular
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * Pega o the cpf.
	 *
	 * @return o the cpf
	 */
	public BigDecimal getCpf() {
		return this.cpf;
	}

	/**
	 * Atribui o the cpf.
	 *
	 * @param cpf
	 *            o novo the cpf
	 */
	public void setCpf(BigDecimal cpf) {
		this.cpf = cpf;
	}

	/**
	 * Pega o the data admissao.
	 *
	 * @return o the data admissao
	 */
	public Date getDataAdmissao() {
		return this.dataAdmissao;
	}

	/**
	 * Atribui o the data admissao.
	 *
	 * @param dataAdmissao
	 *            o novo the data admissao
	 */
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	/**
	 * Pega o the data saida.
	 *
	 * @return o the data saida
	 */
	public Date getDataSaida() {
		return this.dataSaida;
	}

	/**
	 * Atribui o the data saida.
	 *
	 * @param dataSaida
	 *            o novo the data saida
	 */
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	/**
	 * Pega o the email.
	 *
	 * @return o the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Atribui o the email.
	 *
	 * @param email
	 *            o novo the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Pega o the nome.
	 *
	 * @return o the nome
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Atribui o the nome.
	 *
	 * @param nome
	 *            o novo the nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Pega o the setor.
	 *
	 * @return o the setor
	 */
	public String getSetor() {
		return this.setor;
	}

	/**
	 * Atribui o the setor.
	 *
	 * @param setor
	 *            o novo the setor
	 */
	public void setSetor(String setor) {
		this.setor = setor;
	}

	/**
	 * Pega o the telefone.
	 *
	 * @return o the telefone
	 */
	public String getTelefone() {
		return this.telefone;
	}

	/**
	 * Atribui o the telefone.
	 *
	 * @param telefone
	 *            o novo the telefone
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

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

	/**
	 * Pega o the cod cargo.
	 *
	 * @return o the cod cargo
	 */
	public Long getCodCargoPrincipal() {
		return codCargoPrincipal;
	}

	/**
	 * Atribui o the cod cargo.
	 *
	 * @param codCargoPrincipal
	 *            o novo the cod cargo
	 */
	public void setCodCargoPrincipal(Long codCargoPrincipal) {
		this.codCargoPrincipal = codCargoPrincipal;
	}

	/**
	 * Pega o the adm cargo principal.
	 *
	 * @return o the adm cargo principal
	 */
	public AdmCargo getAdmCargoPrincipal() {
		return admCargoPrincipal;
	}

	/**
	 * Atribui o the adm cargo principal.
	 *
	 * @param admCargoPrincipal
	 *            o novo the adm cargo principal
	 */
	public void setAdmCargoPrincipal(AdmCargo admCargoPrincipal) {
		this.admCargoPrincipal = admCargoPrincipal;
	}

	/*
	 * Pega o the adm cargos.
	 *
	 * @return o the adm cargos
	 *
	public List<AdmCargoDTO> getAdmCargos() {
		return admCargos;
	}

	**
	 * Atribui o the adm cargos.
	 *
	 * @param admCargos
	 *            o novo the adm cargos
	 *
	public void setAdmCargos(List<AdmCargoDTO> admCargos) {
		this.admCargos = admCargos;
	}
	*/

	/**
	 * Gets the cpf formatado.
	 *
	 * @return the cpf formatado
	 */
	public String getCpfFormatado() {
		try {
			return CPFCNPJUtil.formatCPForCPNJ(cpf.longValue(), false);
		} catch (Exception e) {
			return this.cpf.toString();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {		
		try {
			return this.nome + " (" + CPFCNPJUtil.formatCPForCPNJ(cpf.longValue(), false) + ")";
		} catch (Exception e) {
			return this.nome + " (" + cpf + ")";
		}
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
		AdmFuncionario other = (AdmFuncionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * To funcionario VO.
	 *
	 * @return the funcionario VO
	 */
	public FuncionarioVO toFuncionarioVO(){
		FuncionarioVO f = new FuncionarioVO();

		f.setId(id);
		f.setAtivo(ativo);
		f.setCelular(celular);
		f.setCodCargoPrincipal(codCargoPrincipal);
		f.setCpf(cpf);
		f.setDataAdmissao(dataAdmissao);
		f.setDataSaida(dataSaida);
		f.setEmail(email);
		f.setNome(nome);
		f.setSetor(setor);
		f.setTelefone(telefone);
		
		if (admCargoPrincipal!=null){
			f.setCargoPrincipal(admCargoPrincipal.toCargoVO());
		}
		/*
		for (AdmCargoDTO admCargo : admCargos) {
			f.getCargos().add(admCargo.toCargoVO());
		}
		 */
		return f;		
	}
	
}