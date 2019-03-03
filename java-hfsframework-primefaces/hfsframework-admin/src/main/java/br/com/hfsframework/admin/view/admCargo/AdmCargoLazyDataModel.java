/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admCargo;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.business.AdmCargoBC;
import br.com.hfsframework.admin.model.AdmCargo;
import br.com.hfsframework.base.BaseLazyModel;

public class AdmCargoLazyDataModel extends LazyDataModel<AdmCargo> {
	
	private static final long serialVersionUID = 1L;

	private BaseLazyModel<AdmCargo, Long, AdmCargoBC> baseLazyModel;

	public AdmCargoLazyDataModel(AdmCargoBC bc) {
		super();
		this.baseLazyModel = new BaseLazyModel<AdmCargo, Long, AdmCargoBC>(bc);
	}

	@Override
	public AdmCargo getRowData(String rowKey) {
		for (AdmCargo admCargo : this.baseLazyModel.getDatasource()) {
			if (admCargo.getId().toString().equals(rowKey))
				return admCargo;
		}
		return null;
	}

	@Override
	public Object getRowKey(AdmCargo admCargo) {
		return admCargo.getId();
	}

	@Override
	public List<AdmCargo> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<AdmCargo> data = this.baseLazyModel.carregar(first, pageSize, sortField, sortOrder, filters, false);

		// sort
		if (sortField != null) {
			Collections.sort(data, new AdmCargoLazySorter(sortField, sortOrder));
		}

		if (filters.keySet().size() > 0 && this.baseLazyModel.isAchou()) {
			this.setRowCount(data.size());
		} else {
			this.setRowCount(this.baseLazyModel.getDataSize());
		}

		return data;
	}
}
