/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admParametroCategoria;

import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.model.AdmParametroCategoria;
import br.com.hfsframework.base.BaseLazySorter;

/**
 * The Class AdmParametroCategoriaLazySorter.
 */
public class AdmParametroCategoriaLazySorter extends BaseLazySorter<AdmParametroCategoria> {

	/**
	 * Instantiates a new adm parametro categoria lazy sorter.
	 *
	 * @param sortField
	 *            the sort field
	 * @param sortOrder
	 *            the sort order
	 */
	public AdmParametroCategoriaLazySorter(String sortField, SortOrder sortOrder) {
		super(sortField, sortOrder);
	}

}
