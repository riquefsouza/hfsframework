/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base.relatorio;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.data.api.EntityRepository;

import br.com.hfsframework.base.BaseBusinessController;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

/**
 * The Class BaseViewRelatorio.
 *
 * @param <T>
 *            the generic type
 * @param <I>
 *            the generic type
 * @param <B>
 *            the generic type
 */
@Named
@ViewScoped
@TratamentoErrosEsperados
public abstract class BaseViewRelatorio<T, I extends Serializable, 
	B extends BaseBusinessController<T, I, ? extends EntityRepository<T, I>>>
		extends BaseViewRelatorioController implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The business controller. */
	@Inject
	private B businessController;

	/** The entidade. */
	@Inject
	private T entidade;

	/**
	 * Instantiates a new base view relatorio.
	 */
	public BaseViewRelatorio() {
		super();
	}

	/**
	 * Inicia o.
	 */
	@PostConstruct
	public void init() {
		super.init();
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
	 * Pega o the business controller.
	 *
	 * @return o the business controller
	 */
	public B getBusinessController() {
		return businessController;
	}
}
