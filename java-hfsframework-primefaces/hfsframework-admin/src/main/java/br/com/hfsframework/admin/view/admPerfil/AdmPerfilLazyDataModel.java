/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admPerfil;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.AdmPerfilBC;
import br.com.hfsframework.admin.model.AdmPerfil;
import br.com.hfsframework.base.BaseLazyModel;

/**
 * The Class AdmPerfilLazyDataModel.
 */
public class AdmPerfilLazyDataModel extends LazyDataModel<AdmPerfil> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The base lazy model. */
	private BaseLazyModel<AdmPerfil, Long, AdmPerfilBC> baseLazyModel;

	/**
	 * Instantiates a new adm perfil lazy data model.
	 *
	 * @param bc
	 *            the bc
	 */
	public AdmPerfilLazyDataModel(AdmPerfilBC bc) {
		super();
		this.baseLazyModel = new BaseLazyModel<AdmPerfil, Long, AdmPerfilBC>(bc);
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowData(java.lang.String)
	 */
	@Override
	public AdmPerfil getRowData(String rowKey) {
		for (AdmPerfil admPerfil : this.baseLazyModel.getDatasource()) {
			if (admPerfil.getId().toString().equals(rowKey))
				return admPerfil;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#getRowKey(java.lang.Object)
	 */
	@Override
	public Object getRowKey(AdmPerfil admPerfil) {
		return admPerfil.getId();
	}

	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<AdmPerfil> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<AdmPerfil> data = this.baseLazyModel.carregar(first, pageSize, sortField, sortOrder, filters, false);

		// sort
		if (sortField != null) {
			Collections.sort(data, new AdmPerfilLazySorter(sortField, sortOrder));
		}

		if (filters.keySet().size() > 0 && this.baseLazyModel.isAchou()) {
			this.setRowCount(data.size());
		} else {
			this.setRowCount(this.baseLazyModel.getDataSize());
		}

		return data;
	}
}
