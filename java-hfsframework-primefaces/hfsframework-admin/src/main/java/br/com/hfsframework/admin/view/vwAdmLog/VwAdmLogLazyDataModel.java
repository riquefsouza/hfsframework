/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.vwAdmLog;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.business.VwAdmLogBC;
import br.com.hfsframework.admin.model.VwAdmLog;
import br.com.hfsframework.base.BaseLazyModel;

/**
 * The Class VwAdmLogLazyDataModel.
 */
public class VwAdmLogLazyDataModel extends LazyDataModel<VwAdmLog> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The base lazy model. */
	private BaseLazyModel<VwAdmLog, Long, VwAdmLogBC> baseLazyModel;

	/**
	 * Instantiates a new vw adm log lazy data model.
	 *
	 * @param bc
	 *            the bc
	 */
	public VwAdmLogLazyDataModel(VwAdmLogBC bc) {
		super();
		this.baseLazyModel = new BaseLazyModel<VwAdmLog, Long, VwAdmLogBC>(bc);
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowData(java.lang.String)
	 */
	@Override
	public VwAdmLog getRowData(String rowKey) {
		for (VwAdmLog vwAdmLog : this.baseLazyModel.getDatasource()) {
			if (vwAdmLog.getId().toString().equals(rowKey))
				return vwAdmLog;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowKey(java.lang.Object)
	 */
	@Override
	public Object getRowKey(VwAdmLog vwAdmLog) {
		return vwAdmLog.getId();
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<VwAdmLog> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<VwAdmLog> data = this.baseLazyModel.carregar(first, pageSize, sortField, sortOrder, filters, false);

		// sort
		if (sortField != null) {
			Collections.sort(data, new VwAdmLogLazySorter(sortField, sortOrder));
		}

		if (filters.keySet().size() > 0 && this.baseLazyModel.isAchou()) {
			this.setRowCount(data.size());
		} else {
			this.setRowCount(this.baseLazyModel.getDataSize());
		}

		return data;
	}
}
