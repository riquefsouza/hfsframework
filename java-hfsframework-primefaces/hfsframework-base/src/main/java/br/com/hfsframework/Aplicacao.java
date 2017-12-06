/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.faces.event.PostConstructApplicationEvent;
import javax.inject.Inject;

import org.apache.logging.log4j.Logger;

/**
 * The Class Aplicacao.
 */
@ApplicationScoped
public class Aplicacao {

	/** The log. */
	@Inject
	private Logger log;
	
	/** The aplicacao config. */
	@Inject
	private AplicacaoConfig aplicacaoConfig;

	/**
	 * Inicia o.
	 *
	 * @param init
	 *            the init
	 */
	public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
		log.info("------------------------------------------------------------------------");
		log.info("HFS Framework");
		log.info("Desenvolvido por Henrique Figueiredo de Souza");
		log.info("Versão 1.0 - 2017");
		log.info("------------------------------------------------------------------------");
		log.info("Iniciando HFS Framework...");
		
		log.info(aplicacaoConfig.toString());
		
		log.info("Estratégia de Transacao: " + aplicacaoConfig.getEstrategiaTransacao());
		log.info("Estágio do Projeto: " + aplicacaoConfig.getProjectStage().toString());
		
		/*
		try {
			Class.forName("org.olap4j.driver.xmla.XmlaOlap4jDriver");
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		}
		
		
		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:xmla:");
			OlapConnection olapConnection = connection.unwrap(OlapConnection.class);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		*/

	}
	
	/**
	 * Apos construir.
	 *
	 * @param event
	 *            the event
	 */
	public void aposConstruir(@Observes PostConstructApplicationEvent event){ 
		log.info("Evento após Construir a Aplicacação");
	}
		

	/**
	 * Destroy.
	 *
	 * @param init
	 *            the init
	 */
	public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {
		log.info("Finalizado HFS Framework...");
		log.info("------------------------------------------------------------------------");
		log.info("HFS Framework");
		log.info("Desenvolvido por Henrique Figueiredo de Souza");
		log.info("Versão 1.0 - 2017");
		log.info("------------------------------------------------------------------------");
	}
}
