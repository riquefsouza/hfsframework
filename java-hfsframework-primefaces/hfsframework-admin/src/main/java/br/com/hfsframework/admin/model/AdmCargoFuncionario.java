/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ADM_CARGO_FUNCIONARIO")
@NamedQueries({
	@NamedQuery(name = "AdmCargoFuncionario.findCargoByCodFuncionario", query = "SELECT cf.id.codCargo FROM AdmCargoFuncionario cf WHERE cf.id.codFuncionario=?1"),
	@NamedQuery(name = "AdmCargoFuncionario.findFuncionarioByCodCargo", query = "SELECT cf.id.codFuncionario FROM AdmCargoFuncionario cf WHERE cf.id.codCargo=?1")
})
public class AdmCargoFuncionario implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@EmbeddedId
	private AdmCargoFuncionarioPK id;

	/** The chefe sepo. */
	@Column(name = "CFN_CHEFE_SEPO")
	private Boolean chefeSepo;

	/** The data desligamento. */
	@Temporal(TemporalType.DATE)
	@Column(name = "CFN_DATA_DESLIGAMENTO")
	private Date dataDesligamento;

	/** The data exercicio. */
	@Temporal(TemporalType.DATE)
	@Column(name = "CFN_DATA_EXERCICIO")
	private Date dataExercicio;

	/** The data posse. */
	@Temporal(TemporalType.DATE)
	@Column(name = "CFN_DATA_POSSE")
	private Date dataPosse;

	/** The diretor geral. */
	@Column(name = "CFN_DIRETOR_GERAL")
	private Boolean diretorGeral;

	/** The presidente. */
	@Column(name = "CFN_PRESIDENTE")
	private Boolean presidente;

	/** The responsavel orcamento. */
	@Column(name = "CFN_RESPONSAVEL_ORCAMENTO")
	private Boolean responsavelOrcamento;

	/** The situacao. */
	@Column(name = "CFN_SITUACAO")
	private String situacao;

	/**
	 * Instantiates a new vw adm cargo funcionario.
	 */
	public AdmCargoFuncionario() {
		super();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar(){
		this.id = new AdmCargoFuncionarioPK();
		this.chefeSepo = null;
		this.dataDesligamento = null;
		this.dataExercicio = null;
		this.dataPosse = null;
		this.diretorGeral = null;
		this.presidente = null;
		this.responsavelOrcamento = null;
		this.situacao = null;
	}

	/**
	 * Pega o the chefe sepo.
	 *
	 * @return o the chefe sepo
	 */
	public Boolean getChefeSepo() {
		return this.chefeSepo;
	}

	/**
	 * Atribui o the chefe sepo.
	 *
	 * @param chefeSepo
	 *            o novo the chefe sepo
	 */
	public void setChefeSepo(Boolean chefeSepo) {
		this.chefeSepo = chefeSepo;
	}

	/**
	 * Pega o the data desligamento.
	 *
	 * @return o the data desligamento
	 */
	public Date getDataDesligamento() {
		return this.dataDesligamento;
	}

	/**
	 * Atribui o the data desligamento.
	 *
	 * @param dataDesligamento
	 *            o novo the data desligamento
	 */
	public void setDataDesligamento(Date dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}

	/**
	 * Pega o the data exercicio.
	 *
	 * @return o the data exercicio
	 */
	public Date getDataExercicio() {
		return this.dataExercicio;
	}

	/**
	 * Atribui o the data exercicio.
	 *
	 * @param dataExercicio
	 *            o novo the data exercicio
	 */
	public void setDataExercicio(Date dataExercicio) {
		this.dataExercicio = dataExercicio;
	}

	/**
	 * Pega o the data posse.
	 *
	 * @return o the data posse
	 */
	public Date getDataPosse() {
		return this.dataPosse;
	}

	/**
	 * Atribui o the data posse.
	 *
	 * @param dataPosse
	 *            o novo the data posse
	 */
	public void setDataPosse(Date dataPosse) {
		this.dataPosse = dataPosse;
	}

	/**
	 * Pega o the diretor geral.
	 *
	 * @return o the diretor geral
	 */
	public Boolean getDiretorGeral() {
		return this.diretorGeral;
	}

	/**
	 * Atribui o the diretor geral.
	 *
	 * @param diretorGeral
	 *            o novo the diretor geral
	 */
	public void setDiretorGeral(Boolean diretorGeral) {
		this.diretorGeral = diretorGeral;
	}

	/**
	 * Pega o the presidente.
	 *
	 * @return o the presidente
	 */
	public Boolean getPresidente() {
		return this.presidente;
	}

	/**
	 * Atribui o the presidente.
	 *
	 * @param presidente
	 *            o novo the presidente
	 */
	public void setPresidente(Boolean presidente) {
		this.presidente = presidente;
	}

	/**
	 * Pega o the responsavel orcamento.
	 *
	 * @return o the responsavel orcamento
	 */
	public Boolean getResponsavelOrcamento() {
		return this.responsavelOrcamento;
	}

	/**
	 * Atribui o the responsavel orcamento.
	 *
	 * @param responsavelOrcamento
	 *            o novo the responsavel orcamento
	 */
	public void setResponsavelOrcamento(Boolean responsavelOrcamento) {
		this.responsavelOrcamento = responsavelOrcamento;
	}

	/**
	 * Pega o the situacao.
	 *
	 * @return o the situacao
	 */
	public String getSituacao() {
		return this.situacao;
	}

	/**
	 * Atribui o the situacao.
	 *
	 * @param situacao
	 *            o novo the situacao
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/**
	 * Pega o the id.
	 *
	 * @return o the id
	 */
	public AdmCargoFuncionarioPK getId() {
		return id;
	}

	/**
	 * Atribui o the id.
	 *
	 * @param id
	 *            o novo the id
	 */
	public void setId(AdmCargoFuncionarioPK id) {
		this.id = id;
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
		AdmCargoFuncionario other = (AdmCargoFuncionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}