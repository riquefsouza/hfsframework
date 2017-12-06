/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.view.admFuncionario;

import org.primefaces.model.SortOrder;

import br.com.hfsframework.admin.model.AdmFuncionario;
import br.com.hfsframework.base.BaseLazySorter;

public class AdmFuncionarioLazySorter extends BaseLazySorter<AdmFuncionario> {

	public AdmFuncionarioLazySorter(String sortField, SortOrder sortOrder) {
		super(sortField, sortOrder);
	}

}
