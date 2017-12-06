/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.data;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import br.com.hfsframework.admin.model.AdmFuncionarioPerfil;
import br.com.hfsframework.admin.model.AdmFuncionarioPerfilPK;

/**
 * The Interface AdmFuncionarioPerfilRepository.
 */
@Repository(forEntity = AdmFuncionarioPerfil.class)
public interface AdmFuncionarioPerfilRepository extends EntityRepository<AdmFuncionarioPerfil, AdmFuncionarioPerfilPK> {

	/**
	 * Delete by perfil.
	 *
	 * @param perfilSeq
	 *            the perfil seq
	 */
	@Query(named = "AdmFuncionarioPerfil.deleteByPerfil")
	void deleteByPerfil(Long perfilSeq);
	
	/**
	 * Delete by cod funcionario.
	 *
	 * @param codFuncionario
	 *            the cod funcionario
	 */
	@Query(named = "AdmFuncionarioPerfil.deleteByCodFuncionario")
	void deleteByCodFuncionario(Long codFuncionario);
}
