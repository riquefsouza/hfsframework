/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admMenu;

import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.model.AdmMenu;
import br.com.hfsframework.base.BaseLazySorter;

/**
 * The Class AdmMenuLazySorter.
 */
public class AdmMenuLazySorter extends BaseLazySorter<AdmMenu> {

	/**
	 * Instantiates a new adm menu lazy sorter.
	 *
	 * @param sortField
	 *            the sort field
	 * @param sortOrder
	 *            the sort order
	 */
	public AdmMenuLazySorter(String sortField, SortOrder sortOrder) {
		super(sortField, sortOrder);
	}

}
