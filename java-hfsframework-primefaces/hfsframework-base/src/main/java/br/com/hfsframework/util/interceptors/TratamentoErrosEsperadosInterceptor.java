/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.interceptors;

import java.io.Serializable;
import java.util.Iterator;

import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.OptimisticLockException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.hfsframework.util.exceptions.ErroEsperado;
import br.com.hfsframework.util.exceptions.ErroInesperado;

/**
 * The Class TratamentoErrosEsperadosInterceptor.
 */
@Interceptor
@TratamentoErrosEsperados
public class TratamentoErrosEsperadosInterceptor implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 535720585764001836L;
	
	/** The log. */
	// private Logger log = Logger.getLogger(GenericServiceBean.class);
	private Logger log = LogManager.getLogger(TratamentoErrosEsperadosInterceptor.class);

	/**
	 * Tratamento de erros esperados.
	 *
	 * @param invocation
	 *            the invocation
	 * @return the object
	 * @throws Exception
	 *             the exception
	 */
	@AroundInvoke
	public Object tratamentoDeErrosEsperados(InvocationContext invocation) throws Exception {
		try {
			return invocation.proceed();
		} catch (ErroEsperado e) {
			for (String mensagemErro : e.getMensagens()) {
				this.log.debug("Ocorreu o erro esperado: [" + mensagemErro + "] no método: "
						+ invocation.getMethod().getName());
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, null, mensagemErro));
			}
			return null;
		} catch (EJBException e) {
			boolean encontrouErroEsperado = false;

			Object causa = e;
			while ((causa = (Exception) ((Exception) causa).getCause()) != null) {
				if ((causa instanceof ErroEsperado)) {
					Iterator<String> localIterator2 = ((ErroEsperado) causa).getMensagens().iterator();
					while (localIterator2.hasNext()) {
						String mensagemErro = (String) localIterator2.next();
						encontrouErroEsperado = true;
						this.log.debug("Ocorreu o erro esperado: [" + mensagemErro + "] no método: "
								+ invocation.getMethod().getName());
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR, null, mensagemErro));
					}
				}
				if ((causa instanceof OptimisticLockException)) {
					String mensagemErro = "Este registro foi atualizado ou apagado por outro usuário. Favor voltar a tela inicial do sistema e repetir a operação.";
					this.log.debug(mensagemErro);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, null, mensagemErro));

					return null;
				}
			}
			if (encontrouErroEsperado) {
				return null;
			}
			throw e;
		} catch (Exception e) {
			this.log.error(e);
			throw new ErroInesperado(e);
		}
	}
}
