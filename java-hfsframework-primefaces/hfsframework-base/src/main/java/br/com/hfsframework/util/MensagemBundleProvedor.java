/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * The Class MensagemBundleProvedor.
 */
public class MensagemBundleProvedor {
	
	/** The bundle. */
	private ResourceBundle bundle;

	/**
	 * Pega o the bundle.
	 *
	 * @return o the bundle
	 */	
	@Produces
	@MensagemBundle
	public ResourceBundle getBundle() {
		if (this.bundle == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			Locale locale = context.getViewRoot().getLocale();
			bundle = ResourceBundle.getBundle("messages", locale);
		}
		return this.bundle;
	}
}
