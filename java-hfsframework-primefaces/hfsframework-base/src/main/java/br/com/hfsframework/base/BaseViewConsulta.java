/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import org.apache.deltaspike.data.api.EntityRepository;

import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

/**
 * The Class BaseViewConsulta.
 *
 * @param <T>
 *            the generic type
 * @param <I>
 *            the generic type
 * @param <B>
 *            the generic type
 * @param <L>
 *            the generic type
 */
@Named
@ViewAccessScoped
@TratamentoErrosEsperados
public abstract class BaseViewConsulta<T, I extends Serializable, 
	B extends BaseBusinessController<T, I, ? extends EntityRepository<T, I>>, 
			L extends ViewConfig> extends BaseViewController implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The pagina listar. */
	private Class<L> paginaListar;
	
	/** The business controller. */
	@Inject
	private B businessController;

	/** The lista entidade. */
	private List<T> listaEntidade;

	/** The entidade. */
	@Inject
	private T entidade;
	
	/**
	 * Instantiates a new base view cadastro.
	 *
	 * @param paginaListar
	 *            the pagina listar
	 */
	public BaseViewConsulta(Class<L> paginaListar){
		super();		
		this.paginaListar = paginaListar;
	}

	/**
	 * Atualiza lista data table.
	 */
	protected void atualizaListaDataTable() {
		this.listaEntidade = businessController.findAll();
	}

	/**
	 * Pega o the pagina listar.
	 *
	 * @return o the pagina listar
	 */
	public String getPaginaListar() {
		return viewConfigResolver.getViewConfigDescriptor(paginaListar).getPath();
	}

	/**
	 * Cancelar.
	 *
	 * @return the string
	 */
	public String cancelar() {
		return getPaginaDesktop();
	}

	/**
	 * Pega o the entidade.
	 *
	 * @return o the entidade
	 */
	public T getEntidade() {
		return this.entidade;
	}

	/**
	 * Atribui o the entidade.
	 *
	 * @param entidade
	 *            o novo the entidade
	 */
	public void setEntidade(T entidade) {
		this.entidade = entidade;
	}

	/**
	 * Pega o the lista entidade.
	 *
	 * @return o the lista entidade
	 */
	public List<T> getListaEntidade() {
		return this.listaEntidade;
	}

	/**
	 * Atribui o the lista entidade.
	 *
	 * @param listaEntidade
	 *            o novo the lista entidade
	 */
	public void setListaEntidade(List<T> listaEntidade) {
		this.listaEntidade = listaEntidade;
	}

	/**
	 * Pega o the business controller.
	 *
	 * @return o the business controller
	 */
	public B getBusinessController() {
		return businessController;
	}


}
