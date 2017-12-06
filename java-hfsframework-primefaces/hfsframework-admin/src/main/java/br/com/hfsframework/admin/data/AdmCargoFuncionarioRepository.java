/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.data;

import java.util.List;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import br.com.hfsframework.admin.model.AdmCargoFuncionario;
import br.com.hfsframework.admin.model.AdmCargoFuncionarioPK;

/**
 * The Interface AdmCargoFuncionarioRepository.
 */
@Repository(forEntity = AdmCargoFuncionario.class)
public interface AdmCargoFuncionarioRepository extends EntityRepository<AdmCargoFuncionario, AdmCargoFuncionarioPK> {

	/**
	 * Find cargo by cod funcionario.
	 *
	 * @param codFuncionario
	 *            the cod funcionario
	 * @return the list
	 */
	@Query(named = "AdmCargoFuncionario.findCargoByCodFuncionario")
	List<Long> findCargoByCodFuncionario(Long codFuncionario);
	
	/**
	 * Find funcionario by cod cargo.
	 *
	 * @param codCargo
	 *            the cod cargo
	 * @return the list
	 */
	@Query(named = "AdmCargoFuncionario.findFuncionarioByCodCargo")
	List<Long> findFuncionarioByCodCargo(Long codCargo);
	
}
