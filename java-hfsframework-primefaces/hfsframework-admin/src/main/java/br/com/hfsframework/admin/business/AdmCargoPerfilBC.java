/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.business;

import java.util.List;

import javax.transaction.Transactional;

import br.com.hfsframework.admin.data.AdmCargoPerfilRepository;
import br.com.hfsframework.admin.model.AdmCargo;
import br.com.hfsframework.admin.model.AdmCargoPerfil;
import br.com.hfsframework.admin.model.AdmCargoPerfilPK;
import br.com.hfsframework.admin.model.AdmPerfil;
import br.com.hfsframework.base.BaseBusinessController;
import br.com.hfsframework.util.exceptions.TransacaoException;

/**
 * The Class AdmCargoPerfilBC.
 */
public class AdmCargoPerfilBC extends BaseBusinessController<AdmCargoPerfil, AdmCargoPerfilPK, AdmCargoPerfilRepository> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Find perfil by cod cargo.
	 *
	 * @param codCargo
	 *            the cod cargo
	 * @return the list
	 */
	public List<AdmPerfil> findPerfilByCodCargo(Long codCargo){
		return repositorio.findPerfilByCodCargo(codCargo);
	}
	
	/**
	 * Delete by perfil.
	 *
	 * @param perfilSeq
	 *            the perfil seq
	 * @throws TransacaoException
	 *             the transacao exception
	 */
	@Transactional
	public void deleteByPerfil(Long perfilSeq) throws TransacaoException {
		try {
			repositorio.deleteByPerfil(perfilSeq);
		} catch (Exception e) {
			throw new TransacaoException(log, ERRO_DELETE + e.getMessage(), e);
		}
	}

	/**
	 * Delete by cargos.
	 *
	 * @param cargos
	 *            the cargos
	 * @throws TransacaoException
	 *             the transacao exception
	 */
	@Transactional
	public void deleteByCargos(List<AdmCargo> cargos) throws TransacaoException {
		try {
			for (AdmCargo cargo : cargos) {
				repositorio.deleteByCodCargo(cargo.getId());
			}
		} catch (Exception e) {
			throw new TransacaoException(log, ERRO_DELETE + e.getMessage(), e);
		}
	}
	
}
