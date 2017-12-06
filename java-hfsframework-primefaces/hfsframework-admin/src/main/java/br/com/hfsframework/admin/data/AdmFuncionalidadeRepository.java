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
 * The Interface AdmFuncionalidadeRepository.
 */
@Repository(forEntity = AdmFuncionalidade.class)
public interface AdmFuncionalidadeRepository extends EntityRepository<AdmFuncionalidade, Long> {

	/**
	 * Gets the descricao.
	 *
	 * @param id
	 *            the id
	 * @return the descricao
	 */
	@Query(named = "AdmFuncionalidade.getDescricaoById")
	String getDescricao(Long id);
	
	/**
	 * Count novo.
	 *
	 * @param novo
	 *            the novo
	 * @return the long
	 */
	@Query(named = "AdmFuncionalidade.countNovo")
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
	@Query(named = "AdmFuncionalidade.countAntigo")
	Long countAntigo(String antigo, String novo);
	
	/**
	 * Find perfis por funcionalidade.
	 *
	 * @param funcionalidade
	 *            the funcionalidade
	 * @return the list
	 */
	@Query(named = "AdmFuncionalidade.findPerfisPorFuncionalidade")
	List<AdmPerfil> findPerfisPorFuncionalidade(AdmFuncionalidade funcionalidade);

	/**
	 * Find paginas por funcionalidade.
	 *
	 * @param funcionalidade
	 *            the funcionalidade
	 * @return the list
	 */
	@Query(named = "AdmFuncionalidade.findPaginasPorFuncionalidade")
	List<AdmPagina> findPaginasPorFuncionalidade(AdmFuncionalidade funcionalidade);

	/**
	 * Find menus por funcionalidade.
	 *
	 * @param funcionalidade
	 *            the funcionalidade
	 * @return the list
	 */
	@Query(named = "AdmFuncionalidade.findMenusPorFuncionalidade")
	List<AdmMenu> findMenusPorFuncionalidade(AdmFuncionalidade funcionalidade);

	/**
	 * Find perfis por URL.
	 *
	 * @param url
	 *            the url
	 * @return the list
	 */
	@Query(named = "AdmFuncionalidade.findPerfisPorURL")
	List<AdmPerfil> findPerfisPorURL(String url);
	
}
