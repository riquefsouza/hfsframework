/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admCargo;

import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.model.AdmCargo;
import br.com.hfsframework.base.BaseLazySorter;

public class AdmCargoLazySorter extends BaseLazySorter<AdmCargo> {

	public AdmCargoLazySorter(String sortField, SortOrder sortOrder) {
		super(sortField, sortOrder);
	}

}
