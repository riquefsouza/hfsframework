/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.hfsframework.AplicacaoConfig;
import br.com.hfsframework.security.model.UsuarioAutenticadoVO;

/**
 * The Class NavegationFilter.
 */
public class NavegationFilter implements Filter {
	
	/** The Constant TELA_ERRO. */
	private static final String TELA_ERRO = "/autorizacaoNegada.xhtml";
	
	/** The Constant TELA_DESKTOP. */
	public static final String TELA_DESKTOP = "desktop.xhtml";
	
	/** The Constant FACES_REDIRECT_XML. */
	private static final String FACES_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><partial-response><redirect url=\"%s\"></redirect></partial-response>";
	
	/** The usuario autenticado. */
	@Inject
	private UsuarioAutenticadoVO usuarioAutenticado;
	
	/** The aplicacao config. */
	@Inject
	private AplicacaoConfig aplicacaoConfig;
	
	//@Inject
	//private Logger log;
		
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String url = request.getRequestURI();
		
		//log.info("URL: " + url);
		
		if (aplicacaoConfig.isHabilitarControlePerfil()) {

			if (usuarioAutenticado.isPossuiPermissao(url, TELA_DESKTOP)) {
				chain.doFilter(req, resp);
			} else {
				redirectToLogin(req, resp);
			}

		} else {
			chain.doFilter(req, resp);
		}
		
	}

	/**
	 * Redirect to login.
	 *
	 * @param req
	 *            the req
	 * @param res
	 *            the res
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected void redirectToLogin(ServletRequest req, ServletResponse res) throws IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		if ("partial/ajax".equals(request.getHeader("Faces-Request"))) {
			res.setContentType("text/xml");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().printf(FACES_REDIRECT_XML, new Object[] { request.getContextPath() + TELA_ERRO });
		} else {
			String url = request.getContextPath() + TELA_ERRO;
			HttpServletResponse resHttp = (HttpServletResponse) res;
			resHttp.sendRedirect(url);
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}
}
