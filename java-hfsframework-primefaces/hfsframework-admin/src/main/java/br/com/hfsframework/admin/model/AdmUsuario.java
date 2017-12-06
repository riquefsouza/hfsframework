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

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.hfsframework.security.model.UsuarioVO;
import br.com.hfsframework.util.CPFCNPJUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class AdmUsuario.
 */
@Entity
@Table(name = "ADM_USUARIO")
@NamedQuery(name = "AdmUsuario.login", query = "SELECT a FROM AdmUsuario a WHERE a.login=?1 AND a.senha=?2")
@NamedNativeQueries({
	@NamedNativeQuery(name = "AdmUsuario.findIPByOracle", query = "SELECT SYS_CONTEXT('USERENV', 'IP_ADDRESS', 15) FROM DUAL"),
	@NamedNativeQuery(name = "AdmUsuario.findIPByPostgreSQL", query = "SELECT substr(CAST(inet_client_addr() AS VARCHAR),1,strpos(CAST(inet_client_addr() AS VARCHAR),'/')-1)"),
	@NamedNativeQuery(name = "AdmUsuario.setLoginPostgreSQL", query = "select set_config('myvars.usuario_login', ?1, false)"),
	@NamedNativeQuery(name = "AdmUsuario.setIPPostgreSQL", query = "select set_config('myvars.usuario_ip', ?1, false)")
})
public class AdmUsuario implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@EmbeddedId
	private AdmUsuarioPK id;

	/** The cpf. */
	@Column(name = "USU_CPF")
	private BigDecimal cpf;

	/** The data. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "USU_DATA")
	private Date data;

	/** The email. */
	@Column(name = "USU_EMAIL")
	private String email;

	/** The ldap DN. */
	@Column(name = "USU_LDAP_DN")
	private String ldapDN;

	/** The login. */
	@Column(name = "USU_LOGIN")
	private String login;

	/** The nome. */
	@Column(name = "USU_NOME")
	private String nome;

	/** The senha. */
	@Column(name = "USU_SENHA")
	private String senha;

	/**
	 * Instantiates a new adm usuario.
	 */
	public AdmUsuario() {
		super();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.id = new AdmUsuarioPK();
		this.cpf = BigDecimal.ZERO;
		this.data = new Date();
		this.email = "";
		this.ldapDN = "";
		this.login = "";
		this.nome = "";
		this.senha = "";
	}

	/**
	 * Pega o the id.
	 *
	 * @return o the id
	 */
	public AdmUsuarioPK getId() {
		return this.id;
	}

	/**
	 * Atribui o the id.
	 *
	 * @param id
	 *            o novo the id
	 */
	public void setId(AdmUsuarioPK id) {
		this.id = id;
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
	 * Pega o the ldap DN.
	 *
	 * @return o the ldap DN
	 */
	public String getLdapDN() {
		return this.ldapDN;
	}

	/**
	 * Atribui o the ldap DN.
	 *
	 * @param ldapDN
	 *            o novo the ldap DN
	 */
	public void setLdapDN(String ldapDN) {
		this.ldapDN = ldapDN;
	}

	/**
	 * Pega o the login.
	 *
	 * @return o the login
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * Atribui o the login.
	 *
	 * @param login
	 *            o novo the login
	 */
	public void setLogin(String login) {
		this.login = login;
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
	 * Gets the senha.
	 *
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Sets the senha.
	 *
	 * @param senha the new senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
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
		AdmUsuario other = (AdmUsuario) obj;
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
		return nome;
	}

	/**
	 * To usuario VO.
	 *
	 * @return the usuario VO
	 */
	public UsuarioVO toUsuarioVO(){
		UsuarioVO u = new UsuarioVO();

		u.setMatricula(this.id.getMatricula());
		u.setIp(this.id.getIp());
		u.setCpf(cpf);
		u.setData(data);
		u.setEmail(email);
		u.setLdapDN(ldapDN);
		u.setLogin(login);
		u.setNome(nome);
		u.setSenha(senha);
		
		return u;
	}

}