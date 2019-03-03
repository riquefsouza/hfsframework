/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admSetor;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.business.AdmSetorBC;
import br.com.hfsframework.admin.model.AdmSetor;
import br.com.hfsframework.base.BaseLazyModel;

public class AdmSetorLazyDataModel extends LazyDataModel<AdmSetor> {
	
	private static final long serialVersionUID = 1L;

	private BaseLazyModel<AdmSetor, String, AdmSetorBC> baseLazyModel;

	public AdmSetorLazyDataModel(AdmSetorBC bc) {
		super();
		this.baseLazyModel = new BaseLazyModel<AdmSetor, String, AdmSetorBC>(bc);
	}

	@Override
	public AdmSetor getRowData(String rowKey) {
		for (AdmSetor admSetor : this.baseLazyModel.getDatasource()) {
			if (admSetor.getId().toString().equals(rowKey))
				return admSetor;
		}
		return null;
	}

	@Override
	public Object getRowKey(AdmSetor admSetor) {
		return admSetor.getId();
	}

	@Override
	public List<AdmSetor> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<AdmSetor> data = this.baseLazyModel.carregar(first, pageSize, sortField, sortOrder, filters, false);

		// sort
		if (sortField != null) {
			Collections.sort(data, new AdmSetorLazySorter(sortField, sortOrder));
		}

		if (filters.keySet().size() > 0 && this.baseLazyModel.isAchou()) {
			this.setRowCount(data.size());
		} else {
			this.setRowCount(this.baseLazyModel.getDataSize());
		}

		return data;
	}
}
