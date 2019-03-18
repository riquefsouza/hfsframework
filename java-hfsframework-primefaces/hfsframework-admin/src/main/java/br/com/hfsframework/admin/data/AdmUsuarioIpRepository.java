/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.data;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Modifying;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import br.com.hfsframework.admin.model.AdmUsuarioIp;
import br.com.hfsframework.admin.model.AdmUsuarioIpPK;

// TODO: Auto-generated Javadoc
/**
 * The Interface AdmUsuarioIpRepository.
 */
@Repository(forEntity =  AdmUsuarioIp.class)
public interface AdmUsuarioIpRepository extends EntityRepository<AdmUsuarioIp, AdmUsuarioIpPK> {

	/**
	 * Update ativo by id.
	 *
	 * @param ativo the ativo
	 * @param idUsuario the idUsuario
	 * @param ip the ip
	 * @return the int
	 */
	@Modifying
	@Query("UPDATE AdmUsuarioIp as u SET u.ativo=?1 WHERE u.id.usuarioSeq=?2 AND u.id.ip = ?3")
	int updateAtivoById(Boolean ativo, Long idUsuario, String ip);
	
	/**
	 * Update ativo by idUsuario.
	 *
	 * @param ativo the ativo
	 * @param idUsuario the idUsuario
	 * @return the int
	 */
	@Modifying
	@Query("UPDATE AdmUsuarioIp as u SET u.ativo=?1 WHERE u.id.usuarioSeq=?2")
	int updateAtivoByIdUsuario(Boolean ativo, Long idUsuario);
}
