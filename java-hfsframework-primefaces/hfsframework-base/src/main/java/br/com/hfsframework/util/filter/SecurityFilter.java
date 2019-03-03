/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class SecurityFilter.
 */
public class SecurityFilter implements Filter {

	/** The Constant log. */
	protected static final Logger log = LogManager.getLogger(SecurityFilter.class);

	/** The Constant PRAGMA_KEY. */
	private static final String PRAGMA_KEY = "Pragma";
	
	/** The Constant PRAGMA_VALUE. */
	private static final String PRAGMA_VALUE = "no-cache";

	/** The Constant STRICT_TRANSPORT_KEY. */
	private static final String STRICT_TRANSPORT_KEY = "strict-transport-security";
	
	/** The Constant STRICT_TRANSPORT_VALUE. */
	private static final String STRICT_TRANSPORT_VALUE = "max-age=604800";

	/* The Constant SET_COOKIE. 
	private static final String SET_COOKIE = "Set-Cookie";
	*/
	/* The Constant JSESSION_ID. 
	private static final String JSESSION_ID = "JSESSIONID=";
	*/
	/* The Constant HTTP_ONLY. 
	private static final String HTTP_ONLY = ";Secure;HttpOnly";
	 */
	/** The Constant CACHE_CONTROL_KEY. */
	private static final String CACHE_CONTROL_KEY = "Cache-Control";
	
	/** The Constant CACHE_CONTROL_VALUE. */
	private static final String CACHE_CONTROL_VALUE = "no-cache, no-store, must-revalidate";

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		makeCookieSecured(response, httpServletRequest);
		chain.doFilter(request, response);

	}

	/**
	 * Make cookie secured.
	 *
	 * @param response the response
	 * @param httpServletRequest the http servlet request
	 */
	private void makeCookieSecured(ServletResponse response, HttpServletRequest httpServletRequest) {
		//Cookie[] cookies = httpServletRequest.getCookies();
		HttpServletResponse httpResp = ((HttpServletResponse) response);
		/*
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("JSESSIONID".equals(cookie.getName())) {
					cookie.setValue(httpServletRequest.getSession().getId() + HTTP_ONLY);
					cookie.setSecure(true);
					cookie.setHttpOnly(true);
					cookie.setPath("/");
					cookie.setMaxAge(604800);
				}
			}
		}
		httpResp.setHeader(SET_COOKIE, JSESSION_ID + httpServletRequest.getSession().getId() + HTTP_ONLY);
		*/		
		httpResp.setHeader(CACHE_CONTROL_KEY, CACHE_CONTROL_VALUE);
		httpResp.setHeader(PRAGMA_KEY, PRAGMA_VALUE);
		httpResp.setHeader(STRICT_TRANSPORT_KEY, STRICT_TRANSPORT_VALUE);
	}

	/*
	private void createJSONErrorResponse(ServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(
				"Please provide valid input, You might have provided some special characters which is not allowed");
	}
	 */
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
