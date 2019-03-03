/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admLogColuna;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.business.AdmLogColunaBC;
import br.com.hfsframework.admin.model.AdmLogColuna;
import br.com.hfsframework.base.BaseLazyModel;

/**
 * The Class AdmLogColunaLazyDataModel.
 */
public class AdmLogColunaLazyDataModel extends LazyDataModel<AdmLogColuna> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The base lazy model. */
	private BaseLazyModel<AdmLogColuna, String, AdmLogColunaBC> baseLazyModel;

	/**
	 * Instantiates a new adm log coluna lazy data model.
	 *
	 * @param bc
	 *            the bc
	 */
	public AdmLogColunaLazyDataModel(AdmLogColunaBC bc) {
		super();
		this.baseLazyModel = new BaseLazyModel<AdmLogColuna, String, AdmLogColunaBC>(bc);
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowData(java.lang.String)
	 */
	@Override
	public AdmLogColuna getRowData(String rowKey) {
		for (AdmLogColuna admLogColuna : this.baseLazyModel.getDatasource()) {
			if (admLogColuna.getNome().toString().equals(rowKey))
				return admLogColuna;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowKey(java.lang.Object)
	 */
	@Override
	public Object getRowKey(AdmLogColuna admLogColuna) {
		return admLogColuna.getNome();
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<AdmLogColuna> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<AdmLogColuna> data = this.baseLazyModel.carregar(first, pageSize, sortField, sortOrder, filters, false);

		// sort
		if (sortField != null) {
			Collections.sort(data, new AdmLogColunaLazySorter(sortField, sortOrder));
		}

		if (filters.keySet().size() > 0 && this.baseLazyModel.isAchou()) {
			this.setRowCount(data.size());
		} else {
			this.setRowCount(this.baseLazyModel.getDataSize());
		}

		return data;
	}
}
