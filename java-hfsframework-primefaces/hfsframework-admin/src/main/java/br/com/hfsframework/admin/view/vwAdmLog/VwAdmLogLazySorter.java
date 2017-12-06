/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.vwAdmLog;

import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.model.VwAdmLog;
import br.com.hfsframework.base.BaseLazySorter;

/**
 * The Class VwAdmLogLazySorter.
 */
public class VwAdmLogLazySorter extends BaseLazySorter<VwAdmLog> {

	/**
	 * Instantiates a new vw adm log lazy sorter.
	 *
	 * @param sortField
	 *            the sort field
	 * @param sortOrder
	 *            the sort order
	 */
	public VwAdmLogLazySorter(String sortField, SortOrder sortOrder) {
		super(sortField, sortOrder);
	}

}
