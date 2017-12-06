/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.security;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;
import org.picketlink.authentication.event.LoggedInEvent;
import org.picketlink.authentication.event.LoginFailedEvent;
import org.picketlink.authentication.event.PostLoggedOutEvent;

import br.com.hfsframework.security.Pages;

/**
 * The listener interface for receiving authentication events. The class that is
 * interested in processing a authentication event implements this interface,
 * and the object created with that class is registered with a component using
 * the component's addAuthenticationListener method. When the authentication
 * event occurs, that object's appropriate method is invoked.
 *
 */
public class AuthenticationListener {

	/** The view navigation handler. */
	@Inject
	private ViewNavigationHandler viewNavigationHandler;

	/** The logged in access decision voter. */
	@Inject
	private LoggedInAccessDecisionVoter loggedInAccessDecisionVoter;

	/**
	 * Handle logged in.
	 *
	 * @param event
	 *            the event
	 */
	public void handleLoggedIn(@Observes LoggedInEvent event) {
		this.viewNavigationHandler.navigateTo(loggedInAccessDecisionVoter.getDeniedPage());
	}

	/**
	 * Handle failed.
	 *
	 * @param event
	 *            the event
	 */
	public void handleFailed(@Observes LoginFailedEvent event) {
		this.viewNavigationHandler.navigateTo(Pages.Login.class);
	}

	/**
	 * Handle logout.
	 *
	 * @param event
	 *            the event
	 */
	public void handleLogout(@Observes PostLoggedOutEvent event) {
		this.viewNavigationHandler.navigateTo(Pages.Login.class);
	}
}
