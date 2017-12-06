/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admPagina;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.AdmPaginaBC;
import br.com.hfsframework.admin.model.AdmPagina;
import br.com.hfsframework.base.BaseLazyModel;

/**
 * The Class AdmPaginaLazyDataModel.
 */
public class AdmPaginaLazyDataModel extends LazyDataModel<AdmPagina> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The base lazy model. */
	private BaseLazyModel<AdmPagina, Long, AdmPaginaBC> baseLazyModel;

	/**
	 * Instantiates a new adm pagina lazy data model.
	 *
	 * @param bc
	 *            the bc
	 */
	public AdmPaginaLazyDataModel(AdmPaginaBC bc) {
		super();
		this.baseLazyModel = new BaseLazyModel<AdmPagina, Long, AdmPaginaBC>(bc);
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowData(java.lang.String)
	 */
	@Override
	public AdmPagina getRowData(String rowKey) {
		for (AdmPagina admPagina : this.baseLazyModel.getDatasource()) {
			if (admPagina.getId().toString().equals(rowKey))
				return admPagina;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowKey(java.lang.Object)
	 */
	@Override
	public Object getRowKey(AdmPagina admPagina) {
		return admPagina.getId();
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<AdmPagina> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<AdmPagina> data = this.baseLazyModel.carregar(first, pageSize, sortField, sortOrder, filters, false);

		// sort
		if (sortField != null) {
			Collections.sort(data, new AdmPaginaLazySorter(sortField, sortOrder));
		}

		if (filters.keySet().size() > 0 && this.baseLazyModel.isAchou()) {
			this.setRowCount(data.size());
		} else {
			this.setRowCount(this.baseLazyModel.getDataSize());
		}

		return data;
	}
}
