/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.vwAdmLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import br.com.hfsframework.admin.business.VwAdmLogBC;
import br.com.hfsframework.admin.business.VwAdmLogValorBC;
import br.com.hfsframework.admin.model.VwAdmLog;
import br.com.hfsframework.admin.model.VwAdmLogValor;
import br.com.hfsframework.base.BaseViewConsulta;
import br.com.hfsframework.base.IBaseViewConsulta;
import br.com.hfsframework.security.Pages;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

/**
 * The Class VwAdmLogMB.
 */
@Named
@ViewAccessScoped
@TratamentoErrosEsperados
public class VwAdmLogMB extends BaseViewConsulta<VwAdmLog, Long, VwAdmLogBC, Pages.Secure.ListarVwAdmLog>
		implements IBaseViewConsulta<VwAdmLog> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The vw adm log filtros. */
	@Inject
	private VwAdmLog vwAdmLogFiltros;

	@Inject
	private VwAdmLogValor vwAdmLogValor;

	@Inject
	private VwAdmLogValorBC vwAdmLogValorBC;

	/** The lista entidade. */
	private List<String> listaSEntidade;

	/**
	 * Instantiates a new vw adm log MB.
	 */
	public VwAdmLogMB() {
		super(Pages.Secure.ListarVwAdmLog.class);

		listaSEntidade = new ArrayList<String>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hfsframework.base.IBaseViewConsulta#init()
	 */
	@PostConstruct
	public void init() {
		if (getBean() != null) {
			listaSEntidade = getBusinessController().getRepositorio().findEntidades();

			if (getBean().getEntidade() != null && listaSEntidade.size() > 0) {
				getBean().setEntidade(listaSEntidade.get(0));
				selectEntidade();
			}
		}

		//atualizaListaDataTable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.hfsframework.base.BaseViewConsulta#atualizaListaDataTable()
	 */
	@Override
	protected void atualizaListaDataTable() {
		this.setListaEntidade(getBusinessController().findByFiltros(vwAdmLogFiltros));
	}

	/**
	 * Select entidade.
	 */
	public void selectEntidade() {
		//if (getBean().getEntidade() != null) {
			//String entidade = entidadeRepository.findBy(getBean().getEntidade());
			//getBean().setEntidade(entidade);
		//}
	}

	/**
	 * On pesquisar.
	 */
	public void onPesquisar() {
		// getSessao().setAttribute("contratoFiltros", contratoFiltros);
		atualizaListaDataTable();
	}

	/**
	 * On limpar pesquisa.
	 */
	public void onLimparPesquisa() {
		vwAdmLogFiltros.limpar();
		// getSessao().removeAttribute("contratoFiltros");
		// getSessao().removeAttribute("contratoBean");
		if (this.getListaEntidade() != null) {
			this.getListaEntidade().clear();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.hfsframework.base.IBaseViewConsulta#preProcessPDF(java.lang
	 * .Object)
	 */
	@Override
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		super.preProcessPDF(document, "VwAdmLog");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hfsframework.base.IBaseViewConsulta#getBean()
	 */
	@Override
	public VwAdmLog getBean() {
		VwAdmLog bean = super.getEntidade();
		
		if (bean!=null) {
			if (bean.getUsuario()!=null) {
				List<VwAdmLogValor> listaLogValor =	vwAdmLogValorBC.getRepositorio().findByFiltros(
						bean.getUsuario(), bean.getDataNumero(), bean.getOperacao(), 
						bean.getIp(), bean.getEntidade(), bean.getTabela(), bean.getChave());
				
				bean.setListaLogValor(listaLogValor);
			}
		}
		
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hfsframework.base.IBaseViewConsulta#setBean(java.lang.
	 * Object)
	 */
	@Override
	public void setBean(VwAdmLog entidade) {
		super.setEntidade(entidade);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.hfsframework.base.IBaseViewConsulta#getListaBean()
	 */
	@Override
	public List<VwAdmLog> getListaBean() {
		return super.getListaEntidade();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.hfsframework.base.IBaseViewConsulta#setListaBean(java.util.
	 * List)
	 */
	@Override
	public void setListaBean(List<VwAdmLog> listaEntidade) {
		super.setListaEntidade(listaEntidade);
	}

	/**
	 * Pega o the vw adm log filtros.
	 *
	 * @return o the vw adm log filtros
	 */
	public VwAdmLog getVwAdmLogFiltros() {
		return vwAdmLogFiltros;
	}

	/**
	 * Atribui o the vw adm log filtros.
	 *
	 * @param vwAdmLogFiltros
	 *            o novo the vw adm log filtros
	 */
	public void setVwAdmLogFiltros(VwAdmLog vwAdmLogFiltros) {
		this.vwAdmLogFiltros = vwAdmLogFiltros;
	}

	/**
	 * Pega o the lista entidade.
	 *
	 * @return o the lista entidade
	 */
	public List<String> getListaSEntidade() {
		return listaSEntidade;
	}

	/**
	 * Atribui o the lista entidade.
	 *
	 * @param listaSEntidade
	 *            o novo the lista entidade
	 */
	public void setListaSEntidade(List<String> listaSEntidade) {
		this.listaSEntidade = listaSEntidade;
	}

	public VwAdmLogValor getVwAdmLogValor() {
		return vwAdmLogValor;
	}

	public void setVwAdmLogValor(VwAdmLogValor vwAdmLogValor) {
		this.vwAdmLogValor = vwAdmLogValor;
	}

}
