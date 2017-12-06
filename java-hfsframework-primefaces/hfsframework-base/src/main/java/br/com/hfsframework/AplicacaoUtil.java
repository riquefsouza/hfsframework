/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework;

import java.io.Serializable;
import java.lang.management.ManagementFactory;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.hfsframework.security.model.UsuarioAutenticadoVO;

/**
 * The Class AplicacaoUtil.
 */
@Named
public final class AplicacaoUtil implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static Logger log = LogManager.getLogger(AplicacaoUtil.class);
		
	/**
	 * Instantiates a new aplicacao util.
	 */
	public AplicacaoUtil() {
		super();
	}

	/**
	 * Checks if is debug mode.
	 *
	 * @return true, if is debug mode
	 */
	public boolean isDebugMode() {
		//boolean isDebug = ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
		//log.info("Sistema em modo de debug: " + isDebug);
		//return isDebug;
		return false;
	}

	/**
	 * Gets the sessao.
	 *
	 * @return the sessao
	 */
	public HttpSession getSessao() {
		HttpServletRequest hsr = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return hsr.getSession();		
	}
	
	/**
	 * Sets the usuario autenticado.
	 *
	 * @param usu
	 *            the new usuario autenticado
	 */
	public void setUsuarioAutenticado(UsuarioAutenticadoVO usu){
		getSessao().setAttribute("usuarioAutenticado", usu);
	}
	
	/**
	 * Gets the usuario autenticado.
	 *
	 * @return the usuario autenticado
	 */
	public UsuarioAutenticadoVO getUsuarioAutenticado(){
		UsuarioAutenticadoVO usu = (UsuarioAutenticadoVO) getSessao().getAttribute("usuarioAutenticado");
		return usu;
	}
	
	/**
	 * Remove o usuario autenticado.
	 */
	public void removeUsuarioAutenticado(){
		getSessao().removeAttribute("usuarioAutenticado");
	}
	
}
