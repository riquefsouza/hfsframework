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

import br.com.hfsframework.admin.model.AdmCargoPerfil;
import br.com.hfsframework.admin.model.AdmCargoPerfilPK;
import br.com.hfsframework.admin.model.AdmPerfil;

/**
 * The Interface AdmCargoPerfilRepository.
 */
@Repository(forEntity = AdmCargoPerfil.class)
public interface AdmCargoPerfilRepository extends EntityRepository<AdmCargoPerfil, AdmCargoPerfilPK> {

	/**
	 * Find perfil by cod cargo.
	 *
	 * @param codCargo
	 *            the cod cargo
	 * @return the list
	 */
	@Query(named = "AdmCargoPerfil.findPerfilByCodCargo")
	List<AdmPerfil> findPerfilByCodCargo(Long codCargo);
	
	/**
	 * Delete by perfil.
	 *
	 * @param perfilSeq
	 *            the perfil seq
	 */
	@Query(named = "AdmCargoPerfil.deleteByPerfil")
	void deleteByPerfil(Long perfilSeq);

	/**
	 * Delete by cod cargo.
	 *
	 * @param codCargo
	 *            the cod cargo
	 */
	@Query(named = "AdmCargoPerfil.deleteCodCargo")
	void deleteByCodCargo(Long codCargo);

}
