/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admFuncionario;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.business.AdmFuncionarioBC;
import br.com.hfsframework.admin.model.AdmFuncionario;
import br.com.hfsframework.base.BaseLazyModel;

public class AdmFuncionarioLazyDataModel extends LazyDataModel<AdmFuncionario> {
	
	private static final long serialVersionUID = 1L;

	private BaseLazyModel<AdmFuncionario, Long, AdmFuncionarioBC> baseLazyModel;

	public AdmFuncionarioLazyDataModel(AdmFuncionarioBC bc) {
		super();
		this.baseLazyModel = new BaseLazyModel<AdmFuncionario, Long, AdmFuncionarioBC>(bc);
	}

	@Override
	public AdmFuncionario getRowData(String rowKey) {
		for (AdmFuncionario admFuncionario : this.baseLazyModel.getDatasource()) {
			if (admFuncionario.getId().toString().equals(rowKey))
				return admFuncionario;
		}
		return null;
	}

	@Override
	public Object getRowKey(AdmFuncionario admFuncionario) {
		return admFuncionario.getId();
	}

	@Override
	public List<AdmFuncionario> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<AdmFuncionario> data = this.baseLazyModel.carregar(first, pageSize, sortField, sortOrder, filters, false);

		// sort
		if (sortField != null) {
			Collections.sort(data, new AdmFuncionarioLazySorter(sortField, sortOrder));
		}

		if (filters.keySet().size() > 0 && this.baseLazyModel.isAchou()) {
			this.setRowCount(data.size());
		} else {
			this.setRowCount(this.baseLazyModel.getDataSize());
		}

		return data;
	}
}
