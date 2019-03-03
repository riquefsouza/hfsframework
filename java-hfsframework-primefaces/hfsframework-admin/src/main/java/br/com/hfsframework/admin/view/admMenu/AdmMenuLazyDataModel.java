/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admMenu;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.business.AdmMenuBC;
import br.com.hfsframework.admin.model.AdmMenu;
import br.com.hfsframework.base.BaseLazyModel;

/**
 * The Class AdmMenuLazyDataModel.
 */
public class AdmMenuLazyDataModel extends LazyDataModel<AdmMenu> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The base lazy model. */
	private BaseLazyModel<AdmMenu, Long, AdmMenuBC> baseLazyModel;

	/**
	 * Instantiates a new adm menu lazy data model.
	 *
	 * @param bc
	 *            the bc
	 */
	public AdmMenuLazyDataModel(AdmMenuBC bc) {
		super();
		this.baseLazyModel = new BaseLazyModel<AdmMenu, Long, AdmMenuBC>(bc);
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowData(java.lang.String)
	 */
	@Override
	public AdmMenu getRowData(String rowKey) {
		for (AdmMenu admMenu : this.baseLazyModel.getDatasource()) {
			if (admMenu.getId().toString().equals(rowKey))
				return admMenu;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowKey(java.lang.Object)
	 */
	@Override
	public Object getRowKey(AdmMenu admMenu) {
		return admMenu.getId();
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<AdmMenu> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<AdmMenu> data = this.baseLazyModel.carregar(first, pageSize, sortField, sortOrder, filters, false);

		// sort
		if (sortField != null) {
			Collections.sort(data, new AdmMenuLazySorter(sortField, sortOrder));
		}

		if (filters.keySet().size() > 0 && this.baseLazyModel.isAchou()) {
			this.setRowCount(data.size());
		} else {
			this.setRowCount(this.baseLazyModel.getDataSize());
		}

		return data;
	}
}
