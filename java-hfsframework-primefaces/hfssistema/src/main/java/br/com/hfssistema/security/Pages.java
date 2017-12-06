/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfssistema.security;

import org.apache.deltaspike.security.api.authorization.Secured;

import br.com.hfsframework.security.LoggedInAccessDecisionVoter;

/**
 * The Interface Pages.
 */
public interface Pages extends br.com.hfsframework.security.Pages {

	/**
	 * The Interface Secure.
	 */
	@Secured(LoggedInAccessDecisionVoter.class)
	interface Secure extends br.com.hfsframework.security.Pages.Secure {

	}

}
