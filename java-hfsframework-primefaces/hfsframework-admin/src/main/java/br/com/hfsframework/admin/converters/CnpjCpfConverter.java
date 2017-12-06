/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "cnpjCpfConverter")
public class CnpjCpfConverter implements Converter {

	/**
	 * <b>Método que remove a mascara do CPF.</b>
	 *
	 * @param facesContext
	 * 			o faces context
	 * @param uIcomponent
	 * 			o uicomponent
	 * @param cnpjCpf
	 * 			o cnpj cpf
	 * @return Object
	 * 			o object
	 */
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uIcomponent, String cnpjCpf) {
		if (cnpjCpf.trim().equals("")) {
			return null;
		} else {
			cnpjCpf = cnpjCpf.replace("-", "");
			cnpjCpf = cnpjCpf.replace(".", "");
			cnpjCpf = cnpjCpf.replace("/", "");
			return cnpjCpf;
		}
	}

	/**
	 * <b>Método que retorna a String do CPF.</b>
	 *
	 * @param facesContext
	 * 			o faces context
	 * @param uIcomponent
	 * 			o uicomponent
	 * @param object
	 * 			o object
	 * @return String
	 * 			o string
	 */
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uIcomponent, Object object) {
		return object.toString();
	}

}
