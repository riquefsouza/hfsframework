/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.security.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.hfsframework.util.CPFCNPJUtil;

/**
 * The Class FuncionarioVO.
 */
public class FuncionarioVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The ativo. */
	private Boolean ativo;

	/** The celular. */
	private String celular;

	/** The cod cargo principal. */
	private Long codCargoPrincipal;

	/** The cpf. */
	private BigDecimal cpf;

	/** The data admissao. */
	private Date dataAdmissao;

	/** The data saida. */
	private Date dataSaida;

	/** The email. */
	private String email;

	/** The nome. */
	private String nome;

	/** The setor. */
	private String setor;

	/** The telefone. */
	private String telefone;

	/** The cargo principal. */
	private CargoVO cargoPrincipal;
			
	/* The cargos. */
	//private List<CargoVO> cargos;
	
	/**
	 * Instantiates a new vw adm funcionario.
	 */
	public FuncionarioVO() {
		//this.cargos = new ArrayList<CargoVO>();
		limpar();
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
		this.cargoPrincipal = new CargoVO();
		//this.cargos.clear();
	}

	/**
	 * Gets the ativo.
	 *
	 * @return the ativo
	 */
	public Boolean getAtivo() {
		return this.ativo;
	}

	/**
	 * Sets the ativo.
	 *
	 * @param ativo
	 *            the new ativo
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * Gets the celular.
	 *
	 * @return the celular
	 */
	public String getCelular() {
		return this.celular;
	}

	/**
	 * Sets the celular.
	 *
	 * @param celular
	 *            the new celular
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * Gets the cpf.
	 *
	 * @return the cpf
	 */
	public BigDecimal getCpf() {
		return this.cpf;
	}

	/**
	 * Sets the cpf.
	 *
	 * @param cpf
	 *            the new cpf
	 */
	public void setCpf(BigDecimal cpf) {
		this.cpf = cpf;
	}

	/**
	 * Gets the data admissao.
	 *
	 * @return the data admissao
	 */
	public Date getDataAdmissao() {
		return this.dataAdmissao;
	}

	/**
	 * Sets the data admissao.
	 *
	 * @param dataAdmissao
	 *            the new data admissao
	 */
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	/**
	 * Gets the data saida.
	 *
	 * @return the data saida
	 */
	public Date getDataSaida() {
		return this.dataSaida;
	}

	/**
	 * Sets the data saida.
	 *
	 * @param dataSaida
	 *            the new data saida
	 */
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome
	 *            the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the setor.
	 *
	 * @return the setor
	 */
	public String getSetor() {
		return this.setor;
	}

	/**
	 * Sets the setor.
	 *
	 * @param setor
	 *            the new setor
	 */
	public void setSetor(String setor) {
		this.setor = setor;
	}

	/**
	 * Gets the telefone.
	 *
	 * @return the telefone
	 */
	public String getTelefone() {
		return this.telefone;
	}

	/**
	 * Sets the telefone.
	 *
	 * @param telefone
	 *            the new telefone
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
	 * Gets the cod cargo principal.
	 *
	 * @return the cod cargo principal
	 */
	public Long getCodCargoPrincipal() {
		return codCargoPrincipal;
	}

	/**
	 * Sets the cod cargo principal.
	 *
	 * @param codCargoPrincipal
	 *            the new cod cargo principal
	 */
	public void setCodCargoPrincipal(Long codCargoPrincipal) {
		this.codCargoPrincipal = codCargoPrincipal;
	}

	/**
	 * Gets the cargo principal.
	 *
	 * @return the cargo principal
	 */
	public CargoVO getCargoPrincipal() {
		return cargoPrincipal;
	}

	/**
	 * Sets the cargo principal.
	 *
	 * @param cargoPrincipal
	 *            the new cargo principal
	 */
	public void setCargoPrincipal(CargoVO cargoPrincipal) {
		this.cargoPrincipal = cargoPrincipal;
	}

	/*
	 * Gets the cargo V os.
	 *
	 * @return the cargo V os
	 *
	public List<CargoVO> getCargos() {
		return cargos;
	}

	**
	 * Sets the cargo V os.
	 *
	 * @param cargos
	 *            the new cargo V os
	 *
	public void setCargos(List<CargoVO> cargos) {
		this.cargos = cargos;
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
		FuncionarioVO other = (FuncionarioVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



}
