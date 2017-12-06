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
 * The Class UsuarioVO.
 */
public class UsuarioVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The matricula. */
	private Long matricula;

	/** The ip. */
	private String ip;
	
	/** The cpf. */
	private BigDecimal cpf;

	/** The data. */
	private Date data;

	/** The email. */
	private String email;

	/** The ldap DN. */
	private String ldapDN;

	/** The login. */
	private String login;

	/** The nome. */
	private String nome;

	/** The senha. */
	private String senha;

	/**
	 * Instantiates a new adm usuario.
	 */
	public UsuarioVO() {
		super();
		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.matricula = 0L;
		this.ip = "";
		this.cpf = BigDecimal.ZERO;
		this.data = new Date();
		this.email = "";
		this.ldapDN = "";
		this.login = "";
		this.nome = "";
		this.senha = "";
	}

	/**
	 * Pega o the matricula.
	 *
	 * @return o the matricula
	 */
	public Long getMatricula() {
		return matricula;
	}

	/**
	 * Atribui o the matricula.
	 *
	 * @param matricula
	 *            o novo the matricula
	 */
	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	/**
	 * Pega o the ip.
	 *
	 * @return o the ip
	 */
	public String getIp() {
		return ip;
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
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		UsuarioVO other = (UsuarioVO) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
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


}
