/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admPagina;

import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.model.AdmPagina;
import br.com.hfsframework.base.BaseLazySorter;

/**
 * The Class AdmPaginaLazySorter.
 */
public class AdmPaginaLazySorter extends BaseLazySorter<AdmPagina> {

	/**
	 * Instantiates a new adm pagina lazy sorter.
	 *
	 * @param sortField
	 *            the sort field
	 * @param sortOrder
	 *            the sort order
	 */
	public AdmPaginaLazySorter(String sortField, SortOrder sortOrder) {
		super(sortField, sortOrder);
	}

}
