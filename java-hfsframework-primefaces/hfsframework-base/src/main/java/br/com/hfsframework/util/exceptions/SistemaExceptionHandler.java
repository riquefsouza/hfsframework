/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.exceptions;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class SistemaExceptionHandler.
 */
@Named("exceptionHandler")
public class SistemaExceptionHandler extends ExceptionHandlerWrapper {
	
	/** The log. */
	private Logger log = LogManager.getLogger(SistemaExceptionHandler.class);
	
	/** The wrapped. */
	private final ExceptionHandler wrapped;

	/**
	 * Instantiates a new sistema exception handler.
	 *
	 * @param wrapped
	 *            the wrapped
	 */
	public SistemaExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	/* (non-Javadoc)
	 * @see javax.faces.context.ExceptionHandlerWrapper#getWrapped()
	 */
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	/* (non-Javadoc)
	 * @see javax.faces.context.ExceptionHandlerWrapper#handle()
	 */
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
		while (i.hasNext()) {
			ExceptionQueuedEvent event = (ExceptionQueuedEvent) i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			Throwable t = context.getException();
			
			FacesContext fc = FacesContext.getCurrentInstance();
			//Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
			NavigationHandler nav = fc.getApplication().getNavigationHandler();

			HttpSession session = ((HttpServletRequest) fc.getExternalContext().getRequest()).getSession();
			session.setAttribute("errorType", t.getClass());
			session.setAttribute("errorCausa", ExceptionUtils.getRootCauseMessage(t));
			session.setAttribute("errorCausaRaiz", ExceptionUtils.getRootCause(t));
			session.setAttribute("errorMsg", t.getMessage());
			session.setAttribute("errorStackTrace", ExceptionUtils.getStackTrace(t));
			try {
				if (!testAncestorCause(t, ErroEsperado.class)) {
					this.log.error("Exceção ocorrida: " + t.toString(), t);
					nav.handleNavigation(fc, null, "/error.xhtml?nocid=true");
					fc.getExternalContext()
							.redirect(fc.getExternalContext().getRequestContextPath() + "/error.xhtml?nocid=true");
				}
			} catch (Exception localException) {
			} finally {
				i.remove();
			}
		}
		getWrapped().handle();
	}

	/**
	 * Test ancestor cause.
	 *
	 * @param target
	 *            the target
	 * @param cause
	 *            the cause
	 * @return true, if successful
	 */
	private boolean testAncestorCause(Throwable target, Class<? extends Throwable> cause) {
		do {
			if (target.getClass() == cause) {
				return true;
			}
			target = target.getCause();
		} while (target.getCause() != null);
		return false;
	}
}
