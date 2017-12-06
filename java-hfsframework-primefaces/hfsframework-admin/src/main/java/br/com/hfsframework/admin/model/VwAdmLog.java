/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The Class VwAdmLog.
 */
@Entity
@Table(name = "VW_ADM_LOG")
@NamedQueries({
	@NamedQuery(name = "VwAdmLog.findEntidades", query = "SELECT DISTINCT v.entidade FROM VwAdmLog v ORDER BY v.entidade"),
	@NamedQuery(name = "VwAdmLog.findByFiltros", query = "SELECT DISTINCT v FROM VwAdmLog v "
			+ "WHERE (v.entidade = ?1 OR ?1 IS NULL) AND (v.usuario = ?2  OR ?2 IS NULL) "
			+ "AND (v.ip = ?3  OR ?3 IS NULL) AND (v.operacao = ?4 OR ?4 IS NULL) "
			+ "AND (v.chave = ?5 OR ?5 IS NULL) AND (v.dataNumero >= ?6 OR ?6 IS NULL) "
			+ "AND (v.dataNumero <= ?7 OR ?7 IS NULL) "
			+ "ORDER BY v.id DESC")	
})
@NamedNativeQueries({ 
	@NamedNativeQuery(name = "VwAdmLog.isContemChave", query = "select count(1) from ?1 where id = ?2 and ?3 = ?4"), // and rownum = 1")
	@NamedNativeQuery(name = "VwAdmLog.detalhar", query = "select DISTINCT * from ?1 where id = ?2"),
	@NamedNativeQuery(name = "VwAdmLog.primaryKeys", query = "SELECT DISTINCT column_name "
			+ "FROM user_cons_columns "
			+ "WHERE constraint_name=(SELECT CONSTRAINT_NAME FROM USER_CONSTRAINTS WHERE LOWER(TABLE_NAME) = LOWER(?1) AND CONSTRAINT_TYPE='P')"),
}) 
public class VwAdmLog implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name = "id")
	private Long id;
	
	/** The usuario. */
	private String usuario;
	
	/** The data. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	/** The data numero. */
	private Long dataNumero;
	
	/** The operacao. */
	private String operacao;
	
	/** The ip. */
	private String ip;
	
	/** The entidade. */
	private String entidade;
	
	/** The tabela. */
	private String tabela;
	
	/** The chave. */
	private String chave;
	
	/** The data inicio. */
	@Transient
	private Date dataInicio;
	
	/** The data fim. */
	@Transient
	private Date dataFim;
	
	/** The chave valor. */
	@Transient
	private String chaveValor;
	
	/** The lista log valor. */
	@Transient
	private List<VwAdmLogValor> listaLogValor;

	/**
	 * Instantiates a new vw adm log.
	 */
	public VwAdmLog() {
		super();
		this.listaLogValor = new ArrayList<VwAdmLogValor>();
		limpar();
	}
	
	/**
	 * Limpar.
	 */
	public void limpar(){
		this.id = null;
		this.usuario = null;
		this.data = null;
		this.operacao = null;
		this.ip = null;
		this.entidade = null;
		this.tabela = null;
		this.chave = null;
		
		this.chaveValor = null;
		this.dataInicio = null;
		this.dataFim = null;
		this.listaLogValor.clear();
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
	 * Pega o the usuario.
	 *
	 * @return o the usuario
	 */
	public String getUsuario() {
		return this.usuario;
	}

	/**
	 * Atribui o the usuario.
	 *
	 * @param usuario
	 *            o novo the usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Pega o the data.
	 *
	 * @return o the data
	 */
	public Date getData() {
		return this.data;
	}

	/**
	 * Atribui o the data.
	 *
	 * @param data
	 *            o novo the data
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * Pega o the operacao.
	 *
	 * @return o the operacao
	 */
	public String getOperacao() {
		return this.operacao;
	}

	/**
	 * Atribui o the operacao.
	 *
	 * @param operacao
	 *            o novo the operacao
	 */
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	/**
	 * Pega o the ip.
	 *
	 * @return o the ip
	 */
	public String getIp() {
		return this.ip;
	}

	/**
	 * Atribui o the ip.
	 *
	 * @param ip
	 *            o novo the ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Pega o the entidade.
	 *
	 * @return o the entidade
	 */
	public String getEntidade() {
		return this.entidade;
	}

	/**
	 * Atribui o the entidade.
	 *
	 * @param entidade
	 *            o novo the entidade
	 */
	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	/**
	 * Pega o the tabela.
	 *
	 * @return o the tabela
	 */
	public String getTabela() {
		return this.tabela;
	}

	/**
	 * Atribui o the tabela.
	 *
	 * @param tabela
	 *            o novo the tabela
	 */
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}

	/**
	 * Pega o the chave.
	 *
	 * @return o the chave
	 */
	public String getChave() {
		return this.chave;
	}

	/**
	 * Atribui o the chave.
	 *
	 * @param chave
	 *            o novo the chave
	 */
	public void setChave(String chave) {
		this.chave = chave;
	}

	/**
	 * Pega o the data inicio.
	 *
	 * @return o the data inicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * Atribui o the data inicio.
	 *
	 * @param dataInicio
	 *            o novo the data inicio
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * Pega o the data fim.
	 *
	 * @return o the data fim
	 */
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * Atribui o the data fim.
	 *
	 * @param dataFim
	 *            o novo the data fim
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * Pega o the chave valor.
	 *
	 * @return o the chave valor
	 */
	public String getChaveValor() {
		return chaveValor;
	}

	/**
	 * Atribui o the chave valor.
	 *
	 * @param chaveValor
	 *            o novo the chave valor
	 */
	public void setChaveValor(String chaveValor) {
		this.chaveValor = chaveValor;
	}

	/**
	 * Gets the operacao texto.
	 *
	 * @return the operacao texto
	 */
	public String getOperacaoTexto() {
		if ((operacao != null) && (!operacao.isEmpty())) {
			if (operacao.equals("I")) {
				return "Inclusão";
			}
			if (operacao.equals("U")) {
				return "Alteração";
			}
			return "Exclusão";
		}
		return "";
	}

	/**
	 * Gets the data numero.
	 *
	 * @return the data numero
	 */
	public Long getDataNumero() {
		return dataNumero;
	}

	/**
	 * Sets the data numero.
	 *
	 * @param dataNumero
	 *            the new data numero
	 */
	public void setDataNumero(Long dataNumero) {
		this.dataNumero = dataNumero;
	}

	/**
	 * Gets the lista log valor.
	 *
	 * @return the lista log valor
	 */
	public List<VwAdmLogValor> getListaLogValor() {
		return listaLogValor;
	}

	/**
	 * Sets the lista log valor.
	 *
	 * @param listaLogValor
	 *            the new lista log valor
	 */
	public void setListaLogValor(List<VwAdmLogValor> listaLogValor) {
		this.listaLogValor = listaLogValor;
	}

}
