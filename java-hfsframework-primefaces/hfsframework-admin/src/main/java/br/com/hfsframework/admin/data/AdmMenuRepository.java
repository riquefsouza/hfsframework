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

/**
 * The Interface AdmMenuRepository.
 */
@Repository(forEntity = AdmMenu.class)
public interface AdmMenuRepository extends EntityRepository<AdmMenu, Long> {

	/**
	 * Gets the descricao.
	 *
	 * @param id
	 *            the id
	 * @return the descricao
	 */
	@Query(named = "AdmMenu.getDescricaoById")
	String getDescricao(Long id);
	
	/**
	 * Count novo.
	 *
	 * @param novo
	 *            the novo
	 * @return the long
	 */
	@Query(named = "AdmMenu.countNovo")
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
	@Query(named = "AdmMenu.countAntigo")
	Long countAntigo(String antigo, String novo);
	
	/**
	 * Find menu raiz.
	 *
	 * @return the list
	 */
	@Query(named="AdmMenu.findMenuRaiz")
	List<AdmMenu> findMenuRaiz();
	
	/**
	 * Find menu raiz.
	 *
	 * @param nomeItemMenu
	 *            the nome item menu
	 * @return the list
	 */
	@Query(named="AdmMenu.findMenuRaizByDescricao")
	List<AdmMenu> findMenuRaiz(String nomeItemMenu);
	
	/**
	 * Count menu by funcionalidade.
	 *
	 * @param funcionalidade
	 *            the funcionalidade
	 * @return the integer
	 */
	@Query(named="AdmMenu.countMenuByFuncionalidade")
	Integer countMenuByFuncionalidade(AdmFuncionalidade funcionalidade);
	
	/**
	 * Find menus filhos.
	 *
	 * @param menu
	 *            the menu
	 * @return the list
	 */
	@Query(named="AdmMenu.findMenusFilhos")
	List<AdmMenu> findMenusFilhos(AdmMenu menu);
	
	/**
	 * Find admin menu pai by cod funcionario.
	 *
	 * @param funcionalidade
	 *            the funcionalidade
	 * @return the list
	 */
	@Query(named="AdmMenu.findAdminMenuPaiByFuncionalidade")
	List<AdmMenu> findAdminMenuPaiByCodFuncionario(AdmFuncionalidade funcionalidade);

	/**
	 * Find menu pai by cod funcionario.
	 *
	 * @param funcionalidade
	 *            the funcionalidade
	 * @return the list
	 */
	@Query(named="AdmMenu.findMenuPaiByFuncionalidade")
	List<AdmMenu> findMenuPaiByCodFuncionario(AdmFuncionalidade funcionalidade);
	
	/**
	 * Find pagina by menu.
	 *
	 * @param funcionalidade
	 *            the funcionalidade
	 * @param idMenu
	 *            the id menu
	 * @return the adm pagina
	 */
	@Query(named="AdmMenu.findPaginaByMenu")
	AdmPagina findPaginaByMenu(AdmFuncionalidade funcionalidade, Long idMenu);
	
	/*
	 * Find funcionarios por menu.
	 *
	 * @param menu
	 *            the menu
	 * @return the list
	 */
	//@Query(named = "AdmMenu.findFuncionariosPorMenu")
	//List<AdmFuncionarioDTO> findFuncionariosPorMenu(AdmMenu menu);
	
}
