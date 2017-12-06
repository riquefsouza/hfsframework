/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework;

import org.apache.deltaspike.core.api.exception.control.BeforeHandles;
import org.apache.deltaspike.core.api.exception.control.ExceptionHandler;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionEvent;
import org.apache.logging.log4j.Logger;

/**
 * The Class AplicacaoExceptionHandlers.
 */
@ExceptionHandler
public class AplicacaoExceptionHandlers {

	/**
	 * Log exceptions.
	 *
	 * @param evt
	 *            the evt
	 * @param log
	 *            the log
	 */
	void logExceptions(@BeforeHandles ExceptionEvent<Throwable> evt, Logger log) {
		log.warn("exceção: " + evt.getException().getMessage());
	}

	/*
	void handleException(@Handles @RestRequest ExceptionEvent<Throwable> evt, ResponseBuilder builder) {
		builder.status(500).entity("Http Response Code 500: " + evt.getException().getMessage())
				.type(MediaType.TEXT_PLAIN_TYPE);
		evt.handledAndContinue();
	}
	

	void showFacesMessage(@Handles @FacesRequest ExceptionEvent<Throwable> evt, FacesContext facesContext) {
		facesContext.addMessage(null, new FacesMessage(evt.getException().getMessage()));
		evt.handledAndContinue();
	}
	 */
}
