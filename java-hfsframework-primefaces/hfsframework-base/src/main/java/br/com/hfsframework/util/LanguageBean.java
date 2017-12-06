/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 * The Class LanguageBean.
 */
@ManagedBean(name = "linguagem")
@SessionScoped
public class LanguageBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The locale code. */
	private String localeCode = "pt_BR";

	/** The countries. */
	private static Map<String, Object> countries;
	static {
		countries = new LinkedHashMap<String, Object>();
		countries.put("Portugues", new Locale("pt", "BR"));
		countries.put("Ingles", Locale.ENGLISH);
	}

	/**
	 * Gets the countries in map.
	 *
	 * @return the countries in map
	 */
	public Map<String, Object> getCountriesInMap() {
		return countries;
	}

	/**
	 * Pega o the locale code.
	 *
	 * @return o the locale code
	 */
	public String getLocaleCode() {
		return localeCode;
	}

	/**
	 * Atribui o the locale code.
	 *
	 * @param localeCode
	 *            o novo the locale code
	 */
	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	/**
	 * Country locale code changed.
	 *
	 * @param e
	 *            the e
	 */
	// value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e) {

		String newLocaleValue = e.getNewValue().toString();

		// loop country map to compare the locale code
		for (Map.Entry<String, Object> entry : countries.entrySet()) {

			if (entry.getValue().toString().equals(newLocaleValue)) {
				FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
			}
		}
	}

}
