/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.email;

import java.io.File;
import java.io.Serializable;

import javax.inject.Named;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.hfsframework.util.ExcecaoUtil;

/**
 * The Class EmailUtil.
 */
@Named
public class EmailUtil implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private Logger log;

	/** The config. */
	private EmailConfig config;

	/**
	 * Instantiates a new email util.
	 */
	public EmailUtil() {
		super();
		this.log = LogManager.getLogger(EmailUtil.class);
	}

	/**
	 * Configurar.
	 *
	 * @param config
	 *            the config
	 */
	public void configurar(EmailConfig config) {
		this.config = config;
	}

	/**
	 * Enviar email.
	 *
	 * @param emailDe
	 *            the email de
	 * @param assunto
	 *            the assunto
	 * @param emailPara
	 *            the email para
	 * @param mensagem
	 *            the mensagem
	 */
	public void enviarEmail(String emailDe, String assunto, String[] emailPara, String mensagem) {
		try {
			Email email = new SimpleEmail();
			email.setHostName(config.getEmailServerHost());
			email.setSmtpPort(config.getEmailServerPort());
			if (config.isEmailAutentica()) {
				email.setAuthenticator(new DefaultAuthenticator(config.getEmailUsuario(), config.getEmailSenha()));
				email.setSSLOnConnect(config.isEmailHabilitaSSL());			
				email.setSSLCheckServerIdentity(config.isEmailHabilitaSSL());
				email.setSslSmtpPort(Integer.toString(config.getEmailServerPort()));
				email.setStartTLSEnabled(config.isEmailHabilitaTLS());
				email.setStartTLSRequired(config.isEmailRequerTLS());
			}
			email.setFrom(emailDe);
			email.setSubject(assunto);
			email.setMsg(mensagem);
			email.addTo(emailPara);
			email.send();
		} catch (EmailException e) {
			ExcecaoUtil.getErros(log, e, e.getMessage(), true);
		}
	}

	/**
	 * Enviar html email.
	 *
	 * @param emailDe
	 *            the email de
	 * @param assunto
	 *            the assunto
	 * @param emailPara
	 *            the email para
	 * @param mensagemHTML
	 *            the mensagem HTML
	 */
	public void enviarHtmlEmail(String emailDe, String assunto, String[] emailPara, String mensagemHTML) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName(config.getEmailServerHost());
			email.setSmtpPort(config.getEmailServerPort());
			if (config.isEmailAutentica()) {
				email.setAuthenticator(new DefaultAuthenticator(config.getEmailUsuario(), config.getEmailSenha()));
				email.setSSLOnConnect(config.isEmailHabilitaSSL());			
				email.setSSLCheckServerIdentity(config.isEmailHabilitaSSL());
				email.setSslSmtpPort(Integer.toString(config.getEmailServerPort()));
				email.setStartTLSEnabled(config.isEmailHabilitaTLS());
				email.setStartTLSRequired(config.isEmailRequerTLS());				
			}
			email.setSSLOnConnect(config.isEmailHabilitaSSL());
			email.setFrom(emailDe);
			email.setSubject(assunto);
			email.setHtmlMsg(mensagemHTML);
			//email.setTextMsg(mensagemHTML);
			email.addTo(emailPara);
			email.send();
		} catch (EmailException e) {
			ExcecaoUtil.getErros(log, e, e.getMessage(), true);
		}
	}

	/**
	 * Adicionar anexo.
	 *
	 * @param arquivo
	 *            the arquivo
	 * @param descricaoArquivo
	 *            the descricao arquivo
	 * @return the email attachment
	 */
	private EmailAttachment adicionarAnexo(File arquivo, String descricaoArquivo) {
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(arquivo.getPath());
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription(descricaoArquivo);
		attachment.setName(arquivo.getName());
		return attachment;
	}

	/**
	 * Enviar email com anexos.
	 *
	 * @param emailDe
	 *            the email de
	 * @param assunto
	 *            the assunto
	 * @param emailPara
	 *            the email para
	 * @param mensagem
	 *            the mensagem
	 * @param arquivo
	 *            the arquivo
	 * @param descricaoArquivo
	 *            the descricao arquivo
	 */
	public void enviarEmailComAnexos(String emailDe, String assunto, String[] emailPara, String mensagem, File arquivo,
			String descricaoArquivo) {
		try {
			MultiPartEmail email = new MultiPartEmail();

			email.setHostName(config.getEmailServerHost());
			email.setSmtpPort(config.getEmailServerPort());
			if (config.isEmailAutentica()) {
				email.setAuthenticator(new DefaultAuthenticator(config.getEmailUsuario(), config.getEmailSenha()));
				email.setSSLOnConnect(config.isEmailHabilitaSSL());			
				email.setSSLCheckServerIdentity(config.isEmailHabilitaSSL());
				email.setSslSmtpPort(Integer.toString(config.getEmailServerPort()));
				email.setStartTLSEnabled(config.isEmailHabilitaTLS());
				email.setStartTLSRequired(config.isEmailRequerTLS());				
			}
			email.setSSLOnConnect(config.isEmailHabilitaSSL());
			email.setFrom(emailDe);
			email.setSubject(assunto);
			email.setMsg(mensagem);
			email.addTo(emailPara);

			email.attach(adicionarAnexo(arquivo, descricaoArquivo));

			email.send();
		} catch (EmailException e) {
			ExcecaoUtil.getErros(log, e, e.getMessage(), true);
		}
	}
}
