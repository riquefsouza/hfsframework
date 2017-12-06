/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.deltaspike.data.api.EntityRepository;
import org.primefaces.model.SortOrder;

/**
 * The Class BaseLazyModel.
 *
 * @param <T>
 *            the generic type
 * @param <I>
 *            the generic type
 * @param <B>
 *            the generic type
 */
public class BaseLazyModel<T, I extends Serializable, 
	B extends BaseBusinessController<T, I, ? extends EntityRepository<T, I>>
	> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The datasource. */
	private List<T> datasource;

	/** The data size. */
	private int dataSize;

	/** The achou. */
	private boolean achou;

	/** The business controller. */
	private B businessController;

	/**
	 * Instantiates a new base lazy model.
	 *
	 * @param businessController
	 *            the business controller
	 */
	public BaseLazyModel(B businessController) {
		super();
		this.datasource = new ArrayList<T>();
		this.businessController = businessController;
		this.dataSize = businessController.getRepositorio().count().intValue();
		this.achou = false;
	}

	/**
	 * Instantiates a new base lazy model.
	 *
	 * @param businessController
	 *            the business controller
	 * @param dataSize
	 *            the data size
	 */
	public BaseLazyModel(B businessController, int dataSize) {
		super();
		this.datasource = new ArrayList<T>();
		this.businessController = businessController;
		this.dataSize = dataSize;
		this.achou = false;
	}
	
	/**
	 * Carregar.
	 *
	 * @param first
	 *            the first
	 * @param pageSize
	 *            the page size
	 * @param sortField
	 *            the sort field
	 * @param sortOrder
	 *            the sort order
	 * @param filters
	 *            the filters
	 * @param usarQueryNativa
	 *            the usar query nativa
	 * @return the list
	 */
	public List<T> carregar(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, boolean usarQueryNativa) {
		List<T> ds;
		
		if (!usarQueryNativa)
			ds = businessController.getRepositorio().findAll(first, pageSize);
		else {
			try {
				ds = businessController.listarPorIntervalo(first+1, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				ds = businessController.listarPorIntervalo(first+1, first + (dataSize % pageSize));
			}
		}
		return carregar(first, pageSize, sortField, sortOrder, filters, ds);
	}
	
	/**
	 * Carregar.
	 *
	 * @param first
	 *            the first
	 * @param pageSize
	 *            the page size
	 * @param sortField
	 *            the sort field
	 * @param sortOrder
	 *            the sort order
	 * @param filters
	 *            the filters
	 * @param ds
	 *            the ds
	 * @return the list
	 */
	public List<T> carregar(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, List<T> ds) {
		List<T> data = new ArrayList<T>();
		achou = false;

		// paginate
		if (dataSize > pageSize) {
			try {
				datasource = ds;
			} catch (Exception e) {
				return new ArrayList<T>();
			}
		}

		// filter
		for (T entidade : datasource) {
			boolean match = true;

			if (filters != null && filters.size() > 0) {
				for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);

						Method metodo1 = entidade.getClass().getMethod("get" + StringUtils.capitalize(filterProperty),
								new Class[] {});
						String fieldValue = String.valueOf(metodo1.invoke(entidade, new Object[] {}));

						if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
							match = true;
							achou = true;
						} else {
							match = false;
							break;
						}
					} catch (Exception e) {
						match = false;
					}
				}
			}

			if (match) {
				data.add(entidade);
			}
		}

		return data;
	}

	/**
	 * Pega o the datasource.
	 *
	 * @return o the datasource
	 */
	public List<T> getDatasource() {
		return datasource;
	}

	/**
	 * Pega o the data size.
	 *
	 * @return o the data size
	 */
	public int getDataSize() {
		return dataSize;
	}

	/**
	 * Checa se é the achou.
	 *
	 * @return o the achou
	 */
	public boolean isAchou() {
		return achou;
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
