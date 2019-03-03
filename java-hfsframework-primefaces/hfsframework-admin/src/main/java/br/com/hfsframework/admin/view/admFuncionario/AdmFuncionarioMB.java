/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admFuncionario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import br.com.hfsframework.admin.business.AdmCargoFuncionarioBC;
import br.com.hfsframework.admin.business.AdmFuncionarioBC;
import br.com.hfsframework.admin.data.AdmCargoRepository;
import br.com.hfsframework.admin.data.AdmSetorRepository;
import br.com.hfsframework.admin.model.AdmCargo;
import br.com.hfsframework.admin.model.AdmFuncionario;
import br.com.hfsframework.admin.model.AdmSetor;
import br.com.hfsframework.base.BaseViewCadastro;
import br.com.hfsframework.base.IBaseViewCadastro;
import br.com.hfsframework.security.Pages;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

// TODO: Auto-generated Javadoc
/**
 * The Class AdmFuncionarioMB.
 */
@Named
@ViewAccessScoped
@TratamentoErrosEsperados
public class AdmFuncionarioMB
		extends BaseViewCadastro<AdmFuncionario, Long, AdmFuncionarioBC, 
		Pages.Secure.ListarAdmFuncionario, Pages.Secure.EditarAdmFuncionario>
		implements IBaseViewCadastro<AdmFuncionario> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The adm cargo funcionario BC. */
	@Inject
	private AdmCargoFuncionarioBC admCargoFuncionarioBC;
	
	/** The adm setor repository. */
	@Inject
	private AdmSetorRepository admSetorRepository;
	
	/** The lista adm setor. */
	private List<AdmSetor> listaAdmSetor;

	/** The adm cargo repository. */
	@Inject
	private AdmCargoRepository admCargoRepository;
	
	/** The lista adm cargo. */
	private List<AdmCargo> listaAdmCargo;
	
	/**
	 * Instantiates a new vw adm funcionario MB.
	 */
	public AdmFuncionarioMB() {
		super(Pages.Secure.ListarAdmFuncionario.class, Pages.Secure.EditarAdmFuncionario.class);
		
		listaAdmSetor = new ArrayList<AdmSetor>();
		listaAdmCargo = new ArrayList<AdmCargo>();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#init()
	 */
	@PostConstruct
	public void init() {
		listaAdmSetor = admSetorRepository.findAll();
		listaAdmCargo = admCargoRepository.findAll();
		
		atualizaListaDataTable();
		
		if (getBean().getSetor() != null && listaAdmSetor.size() > 0) {
			getBean().setSetor(listaAdmSetor.get(0).getId());
			selectSetor();
		}
		
		if (getBean().getAdmCargoPrincipal() != null && listaAdmCargo.size() > 0) {
			getBean().getAdmCargoPrincipal().setId(listaAdmCargo.get(0).getId());
			selectAdmCargoPrincipal();
		}	
	}
	
	/**
	 * Select setor.
	 */
	public void selectSetor() {
		AdmSetor admSetor = admSetorRepository.findBy(getBean().getSetor());
		getBean().setSetor(admSetor.getId());
	}
	
	/**
	 * Select adm cargo principal.
	 */
	public void selectAdmCargoPrincipal() {
		AdmCargo admCargo = admCargoRepository
				.findBy(getBean().getAdmCargoPrincipal().getId());
		getBean().setAdmCargoPrincipal(admCargo);
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#onIncluir()
	 */
	@Override
	public String onIncluir() {
		return super.onIncluir(new AdmFuncionario());
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#salvar()
	 */
	@Override
	public String salvar() {
		return super.salvar(getBean().getId(), getBean().getNome());
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseViewCadastro#excluir(java.lang.Object)
	 */
	@Override
	public void excluir(AdmFuncionario entidade) {
		super.excluir(entidade);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#preProcessPDF(java.lang.Object)
	 */
	@Override
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		super.preProcessPDF(document, "AdmFuncionario");
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getBean()
	 */
	@Override
	public AdmFuncionario getBean() {
		return super.getEntidade();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#setBean(java.lang.Object)
	 */
	@Override
	public void setBean(AdmFuncionario entidade) {
		super.setEntidade(entidade);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#getListaBean()
	 */
	@Override
	public List<AdmFuncionario> getListaBean() {
		return super.getListaEntidade();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseViewCadastro#setListaBean(java.util.List)
	 */
	@Override
	public void setListaBean(List<AdmFuncionario> listaEntidade) {
		super.setListaEntidade(listaEntidade);
	}

	/**
	 * Gets the adm cargos.
	 *
	 * @return the adm cargos
	 */
	public List<AdmCargo> getAdmCargos() {
		if (getBean()!=null && getBean().getId()!=null) 
			return admCargoFuncionarioBC.findCargoByCodFuncionario(getBean().getId());
		else
			return null;
	}

	/**
	 * Gets the lista adm setor.
	 *
	 * @return the lista adm setor
	 */
	public List<AdmSetor> getListaAdmSetor() {
		return listaAdmSetor;
	}

	/**
	 * Sets the lista adm setor.
	 *
	 * @param listaAdmSetor the new lista adm setor
	 */
	public void setListaAdmSetor(List<AdmSetor> listaAdmSetor) {
		this.listaAdmSetor = listaAdmSetor;
	}

	/**
	 * Gets the lista adm cargo.
	 *
	 * @return the lista adm cargo
	 */
	public List<AdmCargo> getListaAdmCargo() {
		return listaAdmCargo;
	}

	/**
	 * Sets the lista adm cargo.
	 *
	 * @param listaAdmCargo the new lista adm cargo
	 */
	public void setListaAdmCargo(List<AdmCargo> listaAdmCargo) {
		this.listaAdmCargo = listaAdmCargo;
	}
	
}
