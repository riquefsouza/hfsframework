/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admUsuario;

import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.model.AdmUsuario;
import br.com.hfsframework.base.BaseLazySorter;

public class AdmUsuarioLazySorter extends BaseLazySorter<AdmUsuario> {

	public AdmUsuarioLazySorter(String sortField, SortOrder sortOrder) {
		super(sortField, sortOrder);
	}

}
