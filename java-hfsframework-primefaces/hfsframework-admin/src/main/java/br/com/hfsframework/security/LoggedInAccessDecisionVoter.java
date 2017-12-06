/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.security;

import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.apache.deltaspike.security.api.authorization.AbstractAccessDecisionVoter;
import org.apache.deltaspike.security.api.authorization.AccessDecisionVoterContext;
import org.apache.deltaspike.security.api.authorization.SecurityViolation;
import org.picketlink.Identity;

/**
 * The Class LoggedInAccessDecisionVoter.
 */
@SessionScoped // or @WindowScoped
public class LoggedInAccessDecisionVoter extends AbstractAccessDecisionVoter {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The view config resolver. */
	@Inject
	private ViewConfigResolver viewConfigResolver;

	/** The identity. */
	@Inject
	private Identity identity;

	/** The denied page. */
	// set a default
	private Class<? extends ViewConfig> deniedPage = Pages.Secure.Desktop.class;

	/* (non-Javadoc)
	 * @see org.apache.deltaspike.security.api.authorization.AbstractAccessDecisionVoter#checkPermission(org.apache.deltaspike.security.api.authorization.AccessDecisionVoterContext, java.util.Set)
	 */
	@Override
	protected void checkPermission(AccessDecisionVoterContext context, Set<SecurityViolation> violations) {

		if (identity.isLoggedIn()) {
			// no violations, pass
		} else {
			violations.add(new SecurityViolation() {
				private static final long serialVersionUID = 1L;

				@Override
				public String getReason() {
					return "O usuário deve estar logado para acessar o recurso";
				}
			});

			deniedPage = viewConfigResolver
					.getViewConfigDescriptor(FacesContext.getCurrentInstance().getViewRoot().getViewId())
					.getConfigClass();
		}
	}

	/**
	 * Pega o the denied page.
	 *
	 * @return o the denied page
	 */
	public Class<? extends ViewConfig> getDeniedPage() {
		return deniedPage;
	}

}
