/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="cnpjCpfValidator")
public class CnpjCpfValidator implements Validator {
	
	private ResourceBundle resourceBundle;
	
	public CnpjCpfValidator() {
		resourceBundle = new MensagemBundleProvedor().getBundle();
	}
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object valor) throws ValidatorException {
		if (valor == null) {
			return;
		}
		
		String cnpjCpf = String.valueOf(valor);
		if (!CPFCNPJUtil.isCPForCPNJ(cnpjCpf)) {
			String msgErro = "erro.validacao.cnpjcpf";
			if (cnpjCpf.length() == 11) {
				msgErro = "erro.validacao.cpf";
			} else if (cnpjCpf.length() == 14) {
				msgErro = "erro.validacao.cnpj";
			}
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary(resourceBundle.getString(msgErro));
            throw new ValidatorException(message);
		}

	}

}
