/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admParametro;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.AdmParametroBC;
import br.com.hfsframework.admin.model.AdmParametro;
import br.com.hfsframework.base.BaseLazyModel;

/**
 * The Class AdmParametroLazyDataModel.
 */
public class AdmParametroLazyDataModel extends LazyDataModel<AdmParametro> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The base lazy model. */
	private BaseLazyModel<AdmParametro, Long, AdmParametroBC> baseLazyModel;

	/**
	 * Instantiates a new adm parametro lazy data model.
	 *
	 * @param bc
	 *            the bc
	 */
	public AdmParametroLazyDataModel(AdmParametroBC bc) {
		super();
		this.baseLazyModel = new BaseLazyModel<AdmParametro, Long, AdmParametroBC>(bc);
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowData(java.lang.String)
	 */
	@Override
	public AdmParametro getRowData(String rowKey) {
		for (AdmParametro admParametro : this.baseLazyModel.getDatasource()) {
			if (admParametro.getId().toString().equals(rowKey))
				return admParametro;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowKey(java.lang.Object)
	 */
	@Override
	public Object getRowKey(AdmParametro admParametro) {
		return admParametro.getId();
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<AdmParametro> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<AdmParametro> data = this.baseLazyModel.carregar(first, pageSize, sortField, sortOrder, filters, false);

		// sort
		if (sortField != null) {
			Collections.sort(data, new AdmParametroLazySorter(sortField, sortOrder));
		}

		if (filters.keySet().size() > 0 && this.baseLazyModel.isAchou()) {
			this.setRowCount(data.size());
		} else {
			this.setRowCount(this.baseLazyModel.getDataSize());
		}

		return data;
	}
}
