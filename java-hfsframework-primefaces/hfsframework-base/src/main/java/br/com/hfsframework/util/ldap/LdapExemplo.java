/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.ldap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class LdapExemplo.
 */
public class LdapExemplo {

	/** The log. */
	private static Logger log = LogManager.getLogger(LdapExemplo.class);
	
	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		try {
			LdapUtil ldap = new LdapUtil();		
			LdapConfig config = new LdapConfig(null);
						
			config.setLdapSPorta(636);
			config.setLdapSProtocolo("SSLv3");
			config.setLdapPorta(389);
			config.setLdapTipoConexao("LDAP");
			config.setLdapFilter("(cn=USER)");
			config.setLdapDnInativos("OU=INATIVOS,OU=TRT,DC=testedom,DC=jus,DC=br");
			config.setLdapAtributos("description,userPrincipalName,objectCategory,name,memberOf,mail,distinguishedName,displayName,cn,logonCount");
			config.setLdapAtributoCodFuncional("description");
			config.setLdapServer("trtrio.gov.br");
			config.setLdapUserSenha("C0nsult@2015");
			config.setLdapBaseDN("OU=TRT,DC=trtrio,DC=gov,DC=br");
			config.setLdapUserDN("CN=consultapauta,OU=CGNC,OU=STI,OU=TRT,DC=trtrio,DC=gov,DC=br");		
						
			ldap.configurar(config);
			
			log.info("Autenticado: " + ldap.login("henrique.souza", ""));
			log.info("------------------ Atributos ----------------------------");
			for (LdapAtributo item: ldap.getAtributos("henrique.souza")) {
				log.info(item.getId() + " - " + item.getValor());			
			}
		} catch (LdapConfiguracaoException e) {
			e.printStackTrace();
		} catch (LdapLoginException e) {
			e.printStackTrace();
		}

	}

}
