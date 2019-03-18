package br.com.hfsframework.admin.data;

import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.hfsframework.admin.model.AdmUsuarioPerfil;
import br.com.hfsframework.admin.model.AdmUsuarioPerfilPK;

@Repository(forEntity = AdmUsuarioPerfil.class)
public interface AdmUsuarioPerfilRepository extends EntityRepository<AdmUsuarioPerfil, AdmUsuarioPerfilPK> {

	/**
	 * Delete by perfil.
	 *
	 * @param perfilSeq
	 *            the perfil seq
	 */
	@Query(named = "AdmUsuarioPerfil.deleteByPerfil")
	void deleteByPerfil(Long perfilSeq);

	/**
	 * Delete by cod funcionario.
	 *
	 * @param codUsuario
	 *            the cod funcionario
	 */
	@Query(named = "AdmUsuarioPerfil.deleteByCodUsuario")
	void deleteByCodUsuario(Long codUsuario);
}
