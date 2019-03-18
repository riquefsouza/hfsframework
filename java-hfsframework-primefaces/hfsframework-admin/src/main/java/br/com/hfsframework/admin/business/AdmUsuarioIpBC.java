/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.business;

import javax.transaction.Transactional;

import br.com.hfsframework.admin.data.AdmUsuarioIpRepository;
import br.com.hfsframework.admin.model.AdmUsuarioIp;
import br.com.hfsframework.admin.model.AdmUsuarioIpPK;
import br.com.hfsframework.base.BaseBusinessController;
import br.com.hfsframework.util.exceptions.TransacaoException;

// TODO: Auto-generated Javadoc
/**
 * The Class AdmUsuarioIpService.
 */
public class AdmUsuarioIpBC extends BaseBusinessController<AdmUsuarioIp, AdmUsuarioIpPK, AdmUsuarioIpRepository> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Update ativo by id.
	 *
	 * @param ativo the ativo
	 * @param id the id
	 * @return the int
	 * @throws TransacaoException the transacao exception
	 */
	@Transactional
	public int updateAtivoById(Boolean ativo, AdmUsuarioIpPK id) throws TransacaoException {
		try {
			return repositorio.updateAtivoById(ativo, id.getUsuarioSeq(), id.getIp());
		} catch (Exception e) {
			throw new TransacaoException(log, ERRO_UPDATE + e.getMessage(), e);
		}
	}
	
	/**
	 * Update ativo by idUsuario.
	 *
	 * @param ativo the ativo
	 * @param idUsuario the idUsuario
	 * @return the int
	 * @throws TransacaoException the transacao exception
	 */
	@Transactional
	public int updateAtivoByIdUsuario(Boolean ativo, Long idUsuario) throws TransacaoException {
		try {
			return repositorio.updateAtivoByIdUsuario(ativo, idUsuario);
		} catch (Exception e) {
			throw new TransacaoException(log, ERRO_UPDATE + e.getMessage(), e);
		}
	}	
}
