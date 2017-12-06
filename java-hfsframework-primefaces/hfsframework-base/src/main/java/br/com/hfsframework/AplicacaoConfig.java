/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.apache.deltaspike.core.api.projectstage.ProjectStage;

/**
 * The Class AplicacaoConfig.
 */
@Named
public class AplicacaoConfig implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The habilitar controle perfil. */
	private boolean habilitarControlePerfil = false;
	
	/** The habilitar LDAP. */
	private boolean habilitarLDAP = false;

	/** The mostrar menu administrativo. */
	private boolean mostrarMenuAdministrativo = false;

	/** The dt linhas. */
	private String dtLinhas;

	/** The dt modelo linhas por pagina. */
	private String dtModeloLinhasPorPagina;

	/** The dt posicao paginador. */
	private String dtPosicaoPaginador;

	/** The dt modelo paginador. */
	private String dtModeloPaginador;

	/** The dt modelo relatorio pagina. */
	private String dtModeloRelatorioPagina;
	
	/** The usar micro servico. */
	private boolean usarMicroServico = false;

	/** The url micro servico. */
	private String urlMicroServico;
	
	/** The bundle. */
	private ResourceBundle bundle;

	/** The project stage. */
	@Inject
	private ProjectStage projectStage;
	
	/** The estrategia transacao. */
	@Inject
	@ConfigProperty(name = "globalAlternatives.org.apache.deltaspike.jpa.spi.transaction.TransactionStrategy")
	private String estrategiaTransacao;
	
	/**
	 * Instantiates a new aplicacao config.
	 */
	public AplicacaoConfig() {
		super();

		// FacesContext context = FacesContext.getCurrentInstance();
		// Locale locale = context.getViewRoot().getLocale();

		Locale locale = new Locale("pt", "BR");
		bundle = ResourceBundle.getBundle("aplicacao", locale);

		carregar();
	}

	/**
	 * Instantiates a new aplicacao config.
	 *
	 * @param bundle
	 *            the bundle
	 */
	public AplicacaoConfig(ResourceBundle bundle) {
		this.bundle = bundle;

		carregar();
	}

	/**
	 * Carregar.
	 */
	private void carregar() {
		if (bundle != null) {
			this.habilitarControlePerfil = Boolean.parseBoolean(bundle.getString("habilitarControlePerfil"));
			this.habilitarLDAP = Boolean.parseBoolean(bundle.getString("habilitarLDAP"));
			this.mostrarMenuAdministrativo = Boolean.parseBoolean(bundle.getString("mostrarMenuAdministrativo"));
			this.dtLinhas = bundle.getString("dtLinhas");
			this.dtModeloLinhasPorPagina = bundle.getString("dtModeloLinhasPorPagina");
			this.dtPosicaoPaginador = bundle.getString("dtPosicaoPaginador");
			this.dtModeloPaginador = bundle.getString("dtModeloPaginador");
			this.dtModeloRelatorioPagina = bundle.getString("dtModeloRelatorioPagina");
			this.usarMicroServico = Boolean.parseBoolean(bundle.getString("usarMicroServico"));
			this.urlMicroServico = bundle.getString("urlMicroServico");
		} else {
			habilitarControlePerfil = true;
			habilitarLDAP = false;
			mostrarMenuAdministrativo = false;
			dtLinhas = "20";
			dtModeloLinhasPorPagina = "10,20,50,80,100";
			dtPosicaoPaginador = "top";
			dtModeloPaginador = "{Exporters} {FirstPageLink} {PreviousPageLink} {CurrentPageReport} {NextPageLink} {LastPageLink} {RowsPerPageDropdown}";
			dtModeloRelatorioPagina = "({startRecord} até {endRecord} total {totalRecords} - Página: {currentPage}/{totalPages})";
			usarMicroServico = false;
			urlMicroServico = "";
		}
	}

	/**
	 * Gets the project stage.
	 *
	 * @return the project stage
	 */
	public ProjectStage getProjectStage() {
		return projectStage;
	}
	
	/**
	 * Checks if is development.
	 *
	 * @return true, if is development
	 */
	public boolean isDevelopment() {
		return ProjectStage.Development.equals(this.projectStage);
	}
	
	/**
	 * Gets the estrategia transacao.
	 *
	 * @return the estrategia transacao
	 */
	public String getEstrategiaTransacao() {
		return estrategiaTransacao;
	}
	
	/**
	 * Checa se é the habilitar controle perfil.
	 *
	 * @return o the habilitar controle perfil
	 */
	public boolean isHabilitarControlePerfil() {
		return habilitarControlePerfil;
	}

	/**
	 * Atribui o the habilitar controle perfil.
	 *
	 * @param habilitarControlePerfil
	 *            o novo the habilitar controle perfil
	 */
	public void setHabilitarControlePerfil(boolean habilitarControlePerfil) {
		this.habilitarControlePerfil = habilitarControlePerfil;
	}

	/**
	 * Checa se é the mostrar menu administrativo.
	 *
	 * @return o the mostrar menu administrativo
	 */
	public boolean isMostrarMenuAdministrativo() {
		return mostrarMenuAdministrativo;
	}

	/**
	 * Atribui o the mostrar menu administrativo.
	 *
	 * @param mostrarMenuAdministrativo
	 *            o novo the mostrar menu administrativo
	 */
	public void setMostrarMenuAdministrativo(boolean mostrarMenuAdministrativo) {
		this.mostrarMenuAdministrativo = mostrarMenuAdministrativo;
	}

	/**
	 * Pega o the dt linhas.
	 *
	 * @return o the dt linhas
	 */
	public String getDtLinhas() {
		return dtLinhas;
	}

	/**
	 * Atribui o the dt linhas.
	 *
	 * @param dtLinhas
	 *            o novo the dt linhas
	 */
	public void setDtLinhas(String dtLinhas) {
		this.dtLinhas = dtLinhas;
	}

	/**
	 * Pega o the dt modelo linhas por pagina.
	 *
	 * @return o the dt modelo linhas por pagina
	 */
	public String getDtModeloLinhasPorPagina() {
		return dtModeloLinhasPorPagina;
	}

	/**
	 * Atribui o the dt modelo linhas por pagina.
	 *
	 * @param dtModeloLinhasPorPagina
	 *            o novo the dt modelo linhas por pagina
	 */
	public void setDtModeloLinhasPorPagina(String dtModeloLinhasPorPagina) {
		this.dtModeloLinhasPorPagina = dtModeloLinhasPorPagina;
	}

	/**
	 * Pega o the dt posicao paginador.
	 *
	 * @return o the dt posicao paginador
	 */
	public String getDtPosicaoPaginador() {
		return dtPosicaoPaginador;
	}

	/**
	 * Atribui o the dt posicao paginador.
	 *
	 * @param dtPosicaoPaginador
	 *            o novo the dt posicao paginador
	 */
	public void setDtPosicaoPaginador(String dtPosicaoPaginador) {
		this.dtPosicaoPaginador = dtPosicaoPaginador;
	}

	/**
	 * Pega o the dt modelo paginador.
	 *
	 * @return o the dt modelo paginador
	 */
	public String getDtModeloPaginador() {
		return dtModeloPaginador;
	}

	/**
	 * Atribui o the dt modelo paginador.
	 *
	 * @param dtModeloPaginador
	 *            o novo the dt modelo paginador
	 */
	public void setDtModeloPaginador(String dtModeloPaginador) {
		this.dtModeloPaginador = dtModeloPaginador;
	}

	/**
	 * Pega o the dt modelo relatorio pagina.
	 *
	 * @return o the dt modelo relatorio pagina
	 */
	public String getDtModeloRelatorioPagina() {
		return dtModeloRelatorioPagina;
	}

	/**
	 * Atribui o the dt modelo relatorio pagina.
	 *
	 * @param dtModeloRelatorioPagina
	 *            o novo the dt modelo relatorio pagina
	 */
	public void setDtModeloRelatorioPagina(String dtModeloRelatorioPagina) {
		this.dtModeloRelatorioPagina = dtModeloRelatorioPagina;
	}

	/**
	 * Checa se é the usar micro servico.
	 *
	 * @return o the usar micro servico
	 */
	public boolean isUsarMicroServico() {
		return usarMicroServico;
	}

	/**
	 * Atribui o the usar micro servico.
	 *
	 * @param usarMicroServico
	 *            o novo the usar micro servico
	 */
	public void setUsarMicroServico(boolean usarMicroServico) {
		this.usarMicroServico = usarMicroServico;
	}

	/**
	 * Gets the url micro servico.
	 *
	 * @return the url micro servico
	 */
	public String getUrlMicroServico() {
		return urlMicroServico;
	}

	/**
	 * Sets the url micro servico.
	 *
	 * @param urlMicroServico
	 *            the new url micro servico
	 */
	public void setUrlMicroServico(String urlMicroServico) {
		this.urlMicroServico = urlMicroServico;
	}

	/**
	 * Checks if is habilitar LDAP.
	 *
	 * @return true, if is habilitar LDAP
	 */
	public boolean isHabilitarLDAP() {
		return habilitarLDAP;
	}

	/**
	 * Sets the habilitar LDAP.
	 *
	 * @param habilitarLDAP the new habilitar LDAP
	 */
	public void setHabilitarLDAP(boolean habilitarLDAP) {
		this.habilitarLDAP = habilitarLDAP;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AplicacaoConfig [habilitarLDAP=" + habilitarLDAP + "\n habilitarControlePerfil=" + habilitarControlePerfil + 
				"\n mostrarMenuAdministrativo="	+ mostrarMenuAdministrativo + "\n dtLinhas=" + dtLinhas + 
				"\n dtModeloLinhasPorPagina=" + dtModeloLinhasPorPagina + "\n dtPosicaoPaginador=" + dtPosicaoPaginador + 
				"\n dtModeloPaginador="	+ dtModeloPaginador + "\n dtModeloRelatorioPagina=" + dtModeloRelatorioPagina + 
				"\n usarMicroServico=" + usarMicroServico + "\n urlMicroServico=" + urlMicroServico + "]";
	}
	
}
