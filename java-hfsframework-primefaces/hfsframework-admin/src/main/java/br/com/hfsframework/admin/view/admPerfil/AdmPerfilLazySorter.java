/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admPerfil;

import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.model.AdmPerfil;
import br.com.hfsframework.base.BaseLazySorter;

/**
 * The Class AdmPerfilLazySorter.
 */
public class AdmPerfilLazySorter extends BaseLazySorter<AdmPerfil> {

	/**
	 * Instantiates a new adm perfil lazy sorter.
	 *
	 * @param sortField
	 *            the sort field
	 * @param sortOrder
	 *            the sort order
	 */
	public AdmPerfilLazySorter(String sortField, SortOrder sortOrder) {
		super(sortField, sortOrder);
	}

}
