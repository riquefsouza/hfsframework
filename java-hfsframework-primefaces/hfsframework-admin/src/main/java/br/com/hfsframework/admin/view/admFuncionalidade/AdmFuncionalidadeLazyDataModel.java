/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admFuncionalidade;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.AdmFuncionalidadeBC;
import br.com.hfsframework.admin.model.AdmFuncionalidade;
import br.com.hfsframework.base.BaseLazyModel;

/**
 * The Class AdmFuncionalidadeLazyDataModel.
 */
public class AdmFuncionalidadeLazyDataModel extends LazyDataModel<AdmFuncionalidade> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The base lazy model. */
	private BaseLazyModel<AdmFuncionalidade, Long, AdmFuncionalidadeBC> baseLazyModel;

	/**
	 * Instantiates a new adm funcionalidade lazy data model.
	 *
	 * @param bc
	 *            the bc
	 */
	public AdmFuncionalidadeLazyDataModel(AdmFuncionalidadeBC bc) {
		super();
		this.baseLazyModel = new BaseLazyModel<AdmFuncionalidade, Long, AdmFuncionalidadeBC>(bc);
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowData(java.lang.String)
	 */
	@Override
	public AdmFuncionalidade getRowData(String rowKey) {
		for (AdmFuncionalidade admFuncionalidade : this.baseLazyModel.getDatasource()) {
			if (admFuncionalidade.getId().toString().equals(rowKey))
				return admFuncionalidade;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowKey(java.lang.Object)
	 */
	@Override
	public Object getRowKey(AdmFuncionalidade admFuncionalidade) {
		return admFuncionalidade.getId();
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<AdmFuncionalidade> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<AdmFuncionalidade> data = this.baseLazyModel.carregar(first, pageSize, sortField, sortOrder, filters, false);

		// sort
		if (sortField != null) {
			Collections.sort(data, new AdmFuncionalidadeLazySorter(sortField, sortOrder));
		}

		if (filters.keySet().size() > 0 && this.baseLazyModel.isAchou()) {
			this.setRowCount(data.size());
		} else {
			this.setRowCount(this.baseLazyModel.getDataSize());
		}

		return data;
	}
}
