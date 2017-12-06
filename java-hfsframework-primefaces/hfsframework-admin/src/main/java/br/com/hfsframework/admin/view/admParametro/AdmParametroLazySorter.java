/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admParametro;

import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.model.AdmParametro;
import br.com.hfsframework.base.BaseLazySorter;

/**
 * The Class AdmParametroLazySorter.
 */
public class AdmParametroLazySorter extends BaseLazySorter<AdmParametro> {

	/**
	 * Instantiates a new adm parametro lazy sorter.
	 *
	 * @param sortField
	 *            the sort field
	 * @param sortOrder
	 *            the sort order
	 */
	public AdmParametroLazySorter(String sortField, SortOrder sortOrder) {
		super(sortField, sortOrder);
	}

}
