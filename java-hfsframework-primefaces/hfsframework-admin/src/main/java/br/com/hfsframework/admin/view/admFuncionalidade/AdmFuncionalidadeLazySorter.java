/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admFuncionalidade;

import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.model.AdmFuncionalidade;
import br.com.hfsframework.base.BaseLazySorter;

/**
 * The Class AdmFuncionalidadeLazySorter.
 */
public class AdmFuncionalidadeLazySorter extends BaseLazySorter<AdmFuncionalidade> {

	/**
	 * Instantiates a new adm funcionalidade lazy sorter.
	 *
	 * @param sortField
	 *            the sort field
	 * @param sortOrder
	 *            the sort order
	 */
	public AdmFuncionalidadeLazySorter(String sortField, SortOrder sortOrder) {
		super(sortField, sortOrder);
	}

}
