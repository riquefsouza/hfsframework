/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.business;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.hfsframework.admin.data.AdmCargoFuncionarioRepository;
import br.com.hfsframework.admin.model.AdmCargo;
import br.com.hfsframework.admin.model.AdmCargoFuncionario;
import br.com.hfsframework.admin.model.AdmCargoFuncionarioPK;
import br.com.hfsframework.admin.model.AdmFuncionario;
import br.com.hfsframework.base.BaseBusinessController;

/**
 * The Class AdmCargoFuncionarioBC.
 */
public class AdmCargoFuncionarioBC extends
		BaseBusinessController<AdmCargoFuncionario, AdmCargoFuncionarioPK, AdmCargoFuncionarioRepository> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The vw adm cargo BC. */
	@Inject
	private AdmCargoBC admCargoBC;

	/** The vw adm funcionario BC. */
	@Inject
	private AdmFuncionarioBC admFuncionarioBC;

	/**
	 * Find cargo by cod funcionario.
	 *
	 * @param codFuncionario
	 *            the cod funcionario
	 * @return the list
	 */
	public List<AdmCargo> findCargoByCodFuncionario(Long codFuncionario){
		List<AdmCargo> lista = new ArrayList<AdmCargo>();
		List<Long> origem = repositorio.findCargoByCodFuncionario(codFuncionario);
		
		for (Long item: origem) {
			lista.add(admCargoBC.load(item));
		}
		
		return lista;
	}
	
	/**
	 * Find funcionario by cod cargo.
	 *
	 * @param codCargo
	 *            the cod cargo
	 * @return the list
	 */
	public List<AdmFuncionario> findFuncionarioByCodCargo(Long codCargo){
		List<AdmFuncionario> lista = new ArrayList<AdmFuncionario>();
		List<Long> origem = repositorio.findFuncionarioByCodCargo(codCargo);
		
		for (Long item: origem) {
			lista.add(admFuncionarioBC.load(item));
		}
		
		return lista;
	}
	
}
