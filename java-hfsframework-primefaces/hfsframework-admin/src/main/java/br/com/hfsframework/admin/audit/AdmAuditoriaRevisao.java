/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.audit;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

/**
 * The Class AdmAuditoriaRevisao.
 */
@Entity(name = "ADM_AUDITORIA_REVISAO")
@RevisionEntity(AuditoriaListener.class)
public class AdmAuditoriaRevisao implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@SequenceGenerator(name = "ADM_AUDITORIA_REVISAO_ID_GENERATOR", sequenceName="adm_auditoria_revisao_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_AUDITORIA_REVISAO_ID_GENERATOR")
	@Column(name = "AUD_ID")
	@RevisionNumber
	private int id;

	/** The timestamp. */
	@Column(name = "AUD_DATA_HORA")
	@RevisionTimestamp
	private long timestamp;

	/** The login. */
	@Column(name = "AUD_LOGIN")
	private String login;

	/** The ip. */
	@Column(name = "AUD_IP")
	private String ip;

	/**
	 * Pega o the id.
	 *
	 * @return o the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Atribui o the id.
	 *
	 * @param id
	 *            o novo the id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Pega o the timestamp.
	 *
	 * @return o the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * Atribui o the timestamp.
	 *
	 * @param timestamp
	 *            o novo the timestamp
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
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
	 * Pega o the login.
	 *
	 * @return o the login
	 */
	public String getLogin() {
		return login;
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
	 * Gets the revision date.
	 *
	 * @return the revision date
	 */
	@Transient
	public Date getRevisionDate() {
		return new Date(timestamp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof AdmAuditoriaRevisao)) {
			return false;
		}

		final AdmAuditoriaRevisao that = (AdmAuditoriaRevisao) o;
		return id == that.id && timestamp == that.timestamp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result;
		result = id;
		result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AdmAuditoriaRevisao [id=" + id + ", revisionDate = "
				+ DateFormat.getDateTimeInstance().format(getRevisionDate()) + ", login=" + login + ", ip=" + ip + "]";
	}

}