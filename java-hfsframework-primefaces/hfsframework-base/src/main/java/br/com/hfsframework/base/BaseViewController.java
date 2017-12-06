/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import br.com.hfsframework.security.model.UsuarioAutenticadoVO;
import br.com.hfsframework.util.ExcecaoUtil;
import br.com.hfsframework.util.ExporterUtil;
import br.com.hfsframework.util.filter.NavegationFilter;

/**
 * The Class BaseViewController.
 */
@Named
public abstract class BaseViewController {

	/** The log. */
	@Inject
	protected transient Logger log;	

	/** The view config resolver. */
	@Inject
	protected ViewConfigResolver viewConfigResolver;
	
	/** The context. */
	@Inject
	protected FacesContext context;
	
	/** The pdf utils. */
	@Inject
	protected ExporterUtil pdfUtils;
	
	/* The pagina desktop. */
	//private Class<? extends ViewConfig> paginaDesktop = Pages.Secure.Desktop.class;

	/** The Constant SELECIONAR_REGISTRO. */
	protected static final String SELECIONAR_REGISTRO = "Favor selecionar um registro da tabela para proceder esta ação.";
	
	/** The Constant ERRO_SALVAR. */
	protected static final String ERRO_SALVAR = "Erro de Transação ao Salvar: ";
	
	/** The Constant ERRO_DELETE. */
	protected static final String ERRO_DELETE = "Erro de Transação ao Excluir: ";
	
	/**
	 * Pega o the pagina desktop.
	 *
	 * @return o the pagina desktop
	 */
	public String getPaginaDesktop(){
		//return viewConfigResolver.getViewConfigDescriptor(paginaDesktop).getPath();
		return "/private/"+NavegationFilter.TELA_DESKTOP;
	}

	/**
	 * Gerar mensagem erro.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public void gerarMensagemErro(String mensagem) {
		context.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, null, mensagem));
	}

	/**
	 * Gerar mensagem erro.
	 *
	 * @param e
	 *            the e
	 * @param mensagem
	 *            the mensagem
	 */
	public void gerarMensagemErro(Exception e, String mensagem) {
		context.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, null, ExcecaoUtil.getErros(log, e, mensagem)));
	}

	/**
	 * Gerar mensagem informativa.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public void gerarMensagemInformativa(String mensagem) {
		context.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, null, mensagem));
	}

	/**
	 * Gerar mensagem aviso.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public void gerarMensagemAviso(String mensagem) {
		context.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, null, mensagem));
	}

	/**
	 * Gerar mensagem erro.
	 *
	 * @param e
	 *            the e
	 * @param mensagem
	 *            the mensagem
	 * @param clientId
	 *            the client id
	 */
	public void gerarMensagemErro(Exception e, String mensagem, String clientId) {
		context.addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, null, ExcecaoUtil.getErros(log, e, mensagem)));
	}

	/**
	 * Gerar mensagem informativa.
	 *
	 * @param mensagem
	 *            the mensagem
	 * @param clientId
	 *            the client id
	 */
	public void gerarMensagemInformativa(String mensagem, String clientId) {
		context.addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_INFO, null, mensagem));
	}

	/**
	 * Gerar mensagem aviso.
	 *
	 * @param mensagem
	 *            the mensagem
	 * @param clientId
	 *            the client id
	 */
	public void gerarMensagemAviso(String mensagem, String clientId) {
		context.addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_WARN, null, mensagem));
	}

	/**
	 * Adiciona o message info dialog.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public static void addMessageInfoDialog(String mensagem) {
		RequestContext.getCurrentInstance()
				.showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", mensagem));
	}

	/**
	 * Adiciona o message alerta dialog.
	 *
	 * @param mensagem
	 *            the mensagem
	 */
	public static void addMessageAlertaDialog(String mensagem) {
		RequestContext.getCurrentInstance()
				.showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", mensagem));
	}

	/**
	 * Adiciona o message erro dialog.
	 *
	 * @param e
	 *            the e
	 * @param mensagem
	 *            the mensagem
	 */
	public static void addMessageErroDialog(Exception e, String mensagem) {
		RequestContext.getCurrentInstance()
				.showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", ExcecaoUtil.getErros(LogManager.getLogger(), e, mensagem)));
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
	 * Gets the atributos sessao.
	 *
	 * @return the atributos sessao
	 */
	public Map<String, String> getAtributosSessao() {
		HttpSession sessao = getSessao();
		HashMap<String, String> mapa = new HashMap<String, String>();
		String atributo, valor;
		for (Enumeration<String> item = sessao.getAttributeNames(); item.hasMoreElements();) {
			atributo = item.nextElement();
			valor = sessao.getAttribute(atributo).toString();
			mapa.put(atributo, valor);
		}
		return mapa;
	}
	
	/**
	 * Log atributos sessao.
	 */
	public void logAtributosSessao() {
		log.info("Sessão: [");
		for (Entry<String, String> item : getAtributosSessao().entrySet()) {
			log.info("\n" + item.getKey() + " = " + item.getValue());
		}
		log.info("]");
	}
	
	/**
	 * Pre process PDF.
	 *
	 * @param document
	 *            the document
	 * @param tituloRelatorio
	 *            the titulo relatorio
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws BadElementException
	 *             the bad element exception
	 * @throws DocumentException
	 *             the document exception
	 */
	public void preProcessPDF(Object document, String tituloRelatorio) 
			throws IOException, BadElementException, DocumentException {
		pdfUtils.preProcessaPDF(document, tituloRelatorio);
	}

	/**
	 * Pega o the usuario autenticado.
	 *
	 * @return o the usuario autenticado
	 */
	public UsuarioAutenticadoVO getUsuarioAutenticado() {		
		return (UsuarioAutenticadoVO) getSessao().getAttribute("usuarioAutenticado");
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
	
}
