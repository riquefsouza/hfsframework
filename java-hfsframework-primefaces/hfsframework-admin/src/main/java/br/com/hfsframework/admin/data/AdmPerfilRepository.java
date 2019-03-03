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

import br.com.hfsframework.admin.model.AdmFuncionalidade;
import br.com.hfsframework.admin.model.AdmMenu;
import br.com.hfsframework.admin.model.AdmPagina;
import br.com.hfsframework.admin.model.AdmPerfil;

/**
 * The Interface AdmPerfilRepository.
 */
@Repository(forEntity = AdmPerfil.class)
public interface AdmPerfilRepository extends EntityRepository<AdmPerfil, Long> {

	/**
	 * Gets the descricao.
	 *
	 * @param id
	 *            the id
	 * @return the descricao
	 */
	@Query(named = "AdmPerfil.getDescricaoById")
	String getDescricao(Long id);
	
	/**
	 * Count novo.
	 *
	 * @param novo
	 *            the novo
	 * @return the long
	 */
	@Query(named = "AdmPerfil.countNovo")
	Long countNovo(String novo);

	/**
	 * Count antigo novo.
	 *
	 * @param antigo
	 *            the antigo
	 * @param novo
	 *            the novo
	 * @return the long
	 */
	@Query(named = "AdmPerfil.countAntigo")
	Long countAntigo(String antigo, String novo);
	
	/**
	 * Find cargos por perfil.
	 *
	 * @param perfil
	 *            the perfil
	 * @return the list
	 */
	@Query(named = "AdmPerfil.findCargosPorPerfil")
	List<Long> findCargosPorPerfil(AdmPerfil perfil);

	/**
	 * Find paginas por perfil.
	 *
	 * @param perfil
	 *            the perfil
	 * @return the list
	 */
	@Query(named = "AdmPerfil.findPaginasPorPerfil")
	List<AdmPagina> findPaginasPorPerfil(AdmPerfil perfil);

	/**
	 * Find funcionarios por perfil.
	 *
	 * @param perfil
	 *            the perfil
	 * @return the list
	 */
	@Query(named = "AdmPerfil.findFuncionariosPorPerfil")
	List<Long> findFuncionariosPorPerfil(AdmPerfil perfil);

	/**
	 * Find funcionalidades por perfil.
	 *
	 * @param perfil
	 *            the perfil
	 * @return the list
	 */
	@Query(named = "AdmPerfil.findFuncionalidadesPorPerfil")
	List<AdmFuncionalidade> findFuncionalidadesPorPerfil(AdmPerfil perfil);
	
	/**
	 * Find perfis por funcionario.
	 *
	 * @param idFuncionario
	 *            the id funcionario
	 * @return the list
	 */
	@Query(named = "AdmPerfil.findPerfisPorFuncionario")	
	List<AdmPerfil> findPerfisPorFuncionario(Long idFuncionario);
	
	/**
	 * Find admin menu pai by perfil.
	 *
	 * @param perfil
	 *            the perfil
	 * @return the list
	 */
	@Query(named="AdmPerfil.findAdminMenuPaiByPerfil")
	List<AdmMenu> findAdminMenuPaiByPerfil(AdmPerfil perfil);

	/**
	 * Find menu pai by perfil.
	 *
	 * @param perfil
	 *            the perfil
	 * @return the list
	 */
	@Query(named="AdmPerfil.findMenuPaiByPerfil")
	List<AdmMenu> findMenuPaiByPerfil(AdmPerfil perfil);

	/**
	 * Find admin menu by perfil.
	 *
	 * @param perfil
	 *            the perfil
	 * @param menuPai
	 *            the menu pai
	 * @return the list
	 */
	@Query(named="AdmPerfil.findAdminMenuByPerfil")
	List<AdmMenu> findAdminMenuByPerfil(AdmPerfil perfil, AdmMenu menuPai);
	
	/**
	 * Find menu by perfil.
	 *
	 * @param perfil
	 *            the perfil
	 * @param menuPai
	 *            the menu pai
	 * @return the list
	 */
	@Query(named="AdmPerfil.findMenuByPerfil")
	List<AdmMenu> findMenuByPerfil(AdmPerfil perfil, AdmMenu menuPai);
	
	/**
	 * Find admin menu pai by id perfis.
	 *
	 * @param listaIdPerfil the lista id perfil
	 * @return the list
	 */
	@Query(named="AdmPerfil.findAdminMenuPaiByIdPerfis")
	List<AdmMenu> findAdminMenuPaiByIdPerfis(List<Long> listaIdPerfil);

	/**
	 * Find menu pai by id perfis.
	 *
	 * @param listaIdPerfil the lista id perfil
	 * @return the list
	 */
	@Query(named="AdmPerfil.findMenuPaiByIdPerfis")
	List<AdmMenu> findMenuPaiByIdPerfis(List<Long> listaIdPerfil);

	/**
	 * Find admin menu by id perfis.
	 *
	 * @param listaIdPerfil the lista id perfil
	 * @param menuPai the menu pai
	 * @return the list
	 */
	@Query(named="AdmPerfil.findAdminMenuByIdPerfis")
	List<AdmMenu> findAdminMenuByIdPerfis(List<Long> listaIdPerfil, AdmMenu menuPai);
	
	/**
	 * Find menu by id perfis.
	 *
	 * @param listaIdPerfil the lista id perfil
	 * @param menuPai the menu pai
	 * @return the list
	 */
	@Query(named="AdmPerfil.findMenuByIdPerfis")
	List<AdmMenu> findMenuByIdPerfis(List<Long> listaIdPerfil, AdmMenu menuPai);

	List<AdmPerfil> findByGeral(Boolean geral);
	
}

