/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.ldap;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Named;

import org.apache.directory.api.ldap.model.cursor.CursorException;
import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.entry.Attribute;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapConnectionConfig;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class LdapUtil.
 */
@Named
public class LdapUtil implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private Logger log;

	/** The config. */
	private LdapConfig config;
	
	/** The lista ldap atributo. */
	private List<LdapAtributo> listaLdapAtributo;
	
	/**
	 * Instantiates a new ldap util.
	 */
	public LdapUtil() {
		super();
		this.log = LogManager.getLogger(LdapUtil.class);
		this.listaLdapAtributo = new ArrayList<LdapAtributo>();
	}

	/**
	 * Configurar.
	 *
	 * @param config
	 *            the config
	 */
	public void configurar(LdapConfig config){
		this.config = config;
	}
	
	/**
	 * Configurar LDAP.
	 *
	 * @return the ldap connection config
	 */
	private LdapConnectionConfig configurarLDAP() {
		LdapConnectionConfig sslConfig = new LdapConnectionConfig();
		sslConfig.setLdapHost(config.getLdapServer());
		sslConfig.setLdapPort(getPortaLDAP());

		if ("LDAPS".compareTo(config.getLdapTipoConexao()) == 0) {
			sslConfig.setEnabledProtocols(new String[] { "SSLv2Hello", "SSLv3", "TLSv1" });
			sslConfig.setSslProtocol(config.getLdapSProtocolo());
			sslConfig.setUseSsl(true);
		}
		return sslConfig;
	}

	/**
	 * Gets the porta LDAP.
	 *
	 * @return the porta LDAP
	 */
	private int getPortaLDAP() {
		if ("LDAP".compareTo(config.getLdapTipoConexao()) == 0) {
			return config.getLdapPorta();
		}

		if ("LDAPS".compareTo(config.getLdapTipoConexao()) == 0) {
			return config.getLdapSPorta();
		}

		return -1;
	}

	/**
	 * Fechar conexao LDAP.
	 *
	 * @param connection
	 *            the connection
	 * @throws LdapConfiguracaoException
	 *             the ldap configuracao exception
	 */
	private void fecharConexaoLDAP(LdapConnection connection) 
			throws LdapConfiguracaoException {
		try {
			connection.close();
			log.info("Conexão fechada LDAP");
		} catch (IOException e) {
			throw new LdapConfiguracaoException(e.getMessage());
		}
	}

	/**
	 * Login.
	 *
	 * @param usuario
	 *            the usuario
	 * @param senha
	 *            the senha
	 * @return true, if successful
	 * @throws LdapConfiguracaoException
	 *             the ldap configuracao exception
	 * @throws LdapLoginException
	 *             the ldap login exception
	 */
	public boolean login(String usuario, String senha)
			throws LdapConfiguracaoException, LdapLoginException {
		boolean retorno = false;
		Entry info;
		EntryCursor cursor;
		String dnUsuario;
		Dn dnInativos;
		LdapConnectionConfig sslConfig = configurarLDAP();
		LdapNetworkConnection conexao = new LdapNetworkConnection(sslConfig);
		String filter = config.getLdapFilter().replace("USER", usuario);
		String atributos[];

		atributos = config.getLdapAtributos().split(",");

		try {
			conexao.bind(config.getLdapUserDN(), config.getLdapUserSenha());
		} catch (LdapException e) {
			fecharConexaoLDAP(conexao);
			throw new LdapConfiguracaoException(log,
					"Falha na comunicação com o servidor LDAP, verifique as configurações no ldap.properties", e);
		}					

		try {
			if (conexao.isConnected()){
			
				try {
					cursor = conexao.search(config.getLdapBaseDN(), filter, SearchScope.SUBTREE, atributos);
					if (cursor.next()) {
						info = cursor.get();

						dnInativos = new Dn(config.getLdapDnInativos());
						// Valida se o usuário não está inativo.
						if (dnInativos.isAncestorOf(info.getDn())) {
							throw new LdapLoginException(log,
									"Falha de autenticação de usuário. Usuário está inativo.");
						}

						if (info.size() > 0) {
							dnUsuario = info.getDn().toString();

							try {
								conexao.bind(dnUsuario, senha);
								if (conexao.isAuthenticated()){
									retorno = true;
									
									for (Iterator<Attribute> iterator = info.iterator(); iterator
											.hasNext();) {
										Attribute entrada = iterator.next();
										listaLdapAtributo.add(new LdapAtributo(entrada.getId(), entrada.getString()));
										log.info(entrada.getId() + ": " + entrada.getString());
									}				
									
									conexao.unBind();
								}
							} catch (LdapException e) {
								throw new LdapLoginException(log,
										"Falha de autenticação de usuário, verifique login e senha", e);
							}
						}
					}
				} catch (LdapException e) {
					throw new LdapConfiguracaoException(log,
							"Falha na busca no LDAP, verifique os parâmetros do ldap.properties", e);
				} catch (CursorException e) {
					throw new LdapConfiguracaoException(log,
							"Falha na busca no LDAP, verifique os parâmetros do ldap.properties", e);
				}				
				
			} else {
				throw new LdapConfiguracaoException(log, "Erro de conexão!");
			}
			
		} finally {
			fecharConexaoLDAP(conexao);
		}
		
		return retorno;
	}

	/**
	 * Gets the atributos.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the atributos
	 * @throws LdapConfiguracaoException
	 *             the ldap configuracao exception
	 * @throws LdapLoginException
	 *             the ldap login exception
	 */
	public List<LdapAtributo> getAtributos(String usuario)
			throws LdapConfiguracaoException, LdapLoginException {
		List<LdapAtributo> lista = new ArrayList<LdapAtributo>();
		LdapConnectionConfig sslConfig = configurarLDAP();
		LdapNetworkConnection connection = new LdapNetworkConnection(sslConfig);
		String filter = config.getLdapFilter().replace("USER", usuario);
		String atributos[];

		atributos = config.getLdapAtributos().split(",");

		try {
			connection.bind(config.getLdapUserDN(), config.getLdapUserSenha());
		} catch (LdapException e) {
			fecharConexaoLDAP(connection);
			throw new LdapConfiguracaoException(
					"Falha na comunicação com o servidor LDAP, verifique as configurações no ldap.properties");
		}
		try {
			EntryCursor cursor = connection.search(config.getLdapBaseDN(),
					filter, SearchScope.SUBTREE, atributos);
			Entry entry;
			if (cursor.next()) {
				entry = cursor.get();
				Dn dnInativos = new Dn(config.getLdapDnInativos());
				// Valida se o usuário não está inativo.
				if (dnInativos.isAncestorOf(entry.getDn())) {
					throw new LdapLoginException(
							"Falha de autenticação de usuário. Usuário está inativo.");
				}
				
				for (Iterator<Attribute> iterator = entry.iterator(); iterator
						.hasNext();) {
					Attribute entrada = iterator.next();
					lista.add(new LdapAtributo(entrada.getId(), entrada.getString()));
				}				
				
			} else {
				throw new LdapLoginException(
						"Falha de autenticação de usuário, verifique login e senha");

			}
		} catch (LdapException e) {
			throw new LdapLoginException(
					"Falha de autenticação de usuário, verifique login e senha");
		} catch (CursorException e) {
			throw new LdapConfiguracaoException(
					"Falha na busca no LDAP, verifique os parâmetros do ldap.properties");
		} finally {
			fecharConexaoLDAP(connection);
		}
		
		return lista;
	}
	
	/**
	 * Pega o the lista ldap atributo.
	 *
	 * @return o the lista ldap atributo
	 */
	public List<LdapAtributo> getListaLdapAtributo() {
		return listaLdapAtributo;
	}

	/**
	 * Atribui o the lista ldap atributo.
	 *
	 * @param listaLdapAtributo
	 *            o novo the lista ldap atributo
	 */
	public void setListaLdapAtributo(List<LdapAtributo> listaLdapAtributo) {
		this.listaLdapAtributo = listaLdapAtributo;
	}
	
	/**
	 * Gets the atributo.
	 *
	 * @param atributo
	 *            the atributo
	 * @return the atributo
	 */
	public String getAtributo(String atributo) {
		String svalor = "";
		List<LdapAtributo> atributos = this.getListaLdapAtributo();
		if (!atributos.isEmpty()) {
			for (LdapAtributo item : atributos) {
				if (item.getId().equals(atributo)) {
					svalor = item.getValor();
					break;
				}
			}
		}
		return svalor;
	}
	
	/**
	 * Gets the matricula.
	 *
	 * @return the matricula
	 */
	public Long getMatricula() {
		String atributo = getAtributo("description");
		if (atributo!=null && !atributo.isEmpty())
			return Long.parseLong(atributo);
		else
			return 0L;
	}
	
	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin(){
		return getAtributo("name");		
	}
	
	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome(){
		return getAtributo("displayname");		
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail(){
		return getAtributo("mail");
	}
	
	/**
	 * Gets the ldap DN.
	 *
	 * @return the ldap DN
	 */
	public String getLdapDN(){
		return getAtributo("distinguishedname");
	}
}
