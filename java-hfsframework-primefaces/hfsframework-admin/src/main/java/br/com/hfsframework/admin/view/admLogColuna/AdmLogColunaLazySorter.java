/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admLogColuna;

import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.model.AdmLogColuna;
import br.com.hfsframework.base.BaseLazySorter;

/**
 * The Class AdmLogColunaLazySorter.
 */
public class AdmLogColunaLazySorter extends BaseLazySorter<AdmLogColuna> {

	/**
	 * Instantiates a new adm log coluna lazy sorter.
	 *
	 * @param sortField
	 *            the sort field
	 * @param sortOrder
	 *            the sort order
	 */
	public AdmLogColunaLazySorter(String sortField, SortOrder sortOrder) {
		super(sortField, sortOrder);
	}

}
