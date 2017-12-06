/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.filter;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class LoggerProducer.
 */
public class LoggerProducer {
	
	/**
	 * Gets the logger.
	 *
	 * @param p
	 *            the p
	 * @return the logger
	 */
	@Produces
	public Logger getLogger(InjectionPoint p) {
		return LogManager.getLogger(p.getClass().getCanonicalName());
	}
}
