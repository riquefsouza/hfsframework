/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admUsuario;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.business.AdmUsuarioBC;
import br.com.hfsframework.admin.model.AdmUsuario;
import br.com.hfsframework.admin.model.AdmUsuarioPK;
import br.com.hfsframework.base.BaseLazyModel;

public class AdmUsuarioLazyDataModel extends LazyDataModel<AdmUsuario> {
	
	private static final long serialVersionUID = 1L;

	private BaseLazyModel<AdmUsuario, AdmUsuarioPK, AdmUsuarioBC> baseLazyModel;

	public AdmUsuarioLazyDataModel(AdmUsuarioBC bc) {
		super();
		this.baseLazyModel = new BaseLazyModel<AdmUsuario, AdmUsuarioPK, AdmUsuarioBC>(bc);
	}

	@Override
	public AdmUsuario getRowData(String rowKey) {
		for (AdmUsuario admUsuario : this.baseLazyModel.getDatasource()) {
			if (admUsuario.getId().toString().equals(rowKey))
				return admUsuario;
		}
		return null;
	}

	@Override
	public Object getRowKey(AdmUsuario admUsuario) {
		return admUsuario.getId();
	}

	@Override
	public List<AdmUsuario> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<AdmUsuario> data = this.baseLazyModel.carregar(first, pageSize, sortField, sortOrder, filters, false);

		// sort
		if (sortField != null) {
			Collections.sort(data, new AdmUsuarioLazySorter(sortField, sortOrder));
		}

		if (filters.keySet().size() > 0 && this.baseLazyModel.isAchou()) {
			this.setRowCount(data.size());
		} else {
			this.setRowCount(this.baseLazyModel.getDataSize());
		}

		return data;
	}
}
