/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admSetor;

import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.model.AdmSetor;
import br.com.hfsframework.base.BaseLazySorter;

public class AdmSetorLazySorter extends BaseLazySorter<AdmSetor> {

	public AdmSetorLazySorter(String sortField, SortOrder sortOrder) {
		super(sortField, sortOrder);
	}

}
