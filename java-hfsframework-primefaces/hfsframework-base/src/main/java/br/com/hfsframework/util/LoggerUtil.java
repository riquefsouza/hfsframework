/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

/**
 * The Class LoggerUtil.
 */
public final class LoggerUtil implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Gets the logger context.
	 *
	 * @return the logger context
	 */
	public static LoggerContext getLoggerContext() {
		final LoggerContext ctx = PrivateManager.getContext();
		ctx.reconfigure();
		return ctx;
	}

	/**
	 * The Class PrivateManager.
	 */
	private static class PrivateManager extends LogManager {

		/** The Constant FQCN. */
		private static final String FQCN = LogManager.class.getName();

		/**
		 * Gets the context.
		 *
		 * @return the context
		 */
		public static LoggerContext getContext() {
			return (LoggerContext) getContext(FQCN, false);
		}

		/**
		 * Gets the logger.
		 *
		 * @param name
		 *            the name
		 * @return the logger
		 */
		public static org.apache.logging.log4j.Logger getLogger(final String name) {
			return getLogger(FQCN, name);
		}
	}

}
