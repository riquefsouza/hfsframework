/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admParametroCategoria;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.business.AdmParametroCategoriaBC;
import br.com.hfsframework.admin.model.AdmParametroCategoria;
import br.com.hfsframework.base.BaseLazyModel;

/**
 * The Class AdmParametroCategoriaLazyDataModel.
 */
public class AdmParametroCategoriaLazyDataModel extends LazyDataModel<AdmParametroCategoria> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The base lazy model. */
	private BaseLazyModel<AdmParametroCategoria, Long, AdmParametroCategoriaBC> baseLazyModel;

	/**
	 * Instantiates a new adm parametro categoria lazy data model.
	 *
	 * @param bc
	 *            the bc
	 */
	public AdmParametroCategoriaLazyDataModel(AdmParametroCategoriaBC bc) {
		super();
		this.baseLazyModel = new BaseLazyModel<AdmParametroCategoria, Long, AdmParametroCategoriaBC>(bc);
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowData(java.lang.String)
	 */
	@Override
	public AdmParametroCategoria getRowData(String rowKey) {
		for (AdmParametroCategoria admParametroCategoria : this.baseLazyModel.getDatasource()) {
			if (admParametroCategoria.getId().toString().equals(rowKey))
				return admParametroCategoria;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowKey(java.lang.Object)
	 */
	@Override
	public Object getRowKey(AdmParametroCategoria admParametroCategoria) {
		return admParametroCategoria.getId();
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<AdmParametroCategoria> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<AdmParametroCategoria> data = this.baseLazyModel.carregar(first, pageSize, sortField, sortOrder, filters, false);

		// sort
		if (sortField != null) {
			Collections.sort(data, new AdmParametroCategoriaLazySorter(sortField, sortOrder));
		}

		if (filters.keySet().size() > 0 && this.baseLazyModel.isAchou()) {
			this.setRowCount(data.size());
		} else {
			this.setRowCount(this.baseLazyModel.getDataSize());
		}

		return data;
	}
}
