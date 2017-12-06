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
import br.com.hfsframework.admin.model.AdmPagina;
import br.com.hfsframework.admin.model.AdmPerfil;

/**
 * The Interface AdmPaginaRepository.
 */
@Repository(forEntity = AdmPagina.class)
public interface AdmPaginaRepository extends EntityRepository<AdmPagina, Long> {

	/**
	 * Gets the descricao.
	 *
	 * @param id
	 *            the id
	 * @return the descricao
	 */
	@Query(named = "AdmPagina.getDescricaoById")
	String getDescricao(Long id);
	
	/**
	 * Count novo.
	 *
	 * @param novo
	 *            the novo
	 * @return the long
	 */
	@Query(named = "AdmPagina.countNovo")
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
	@Query(named = "AdmPagina.countAntigo")
	Long countAntigo(String antigo, String novo);
	
	/**
	 * Find perfis por pagina.
	 *
	 * @param pagina
	 *            the pagina
	 * @return the list
	 */
	@Query(named = "AdmPagina.findPerfisPorPagina")
	List<AdmPerfil> findPerfisPorPagina(AdmPagina pagina);

	/**
	 * Find funcionalidades por pagina.
	 *
	 * @param pagina
	 *            the pagina
	 * @return the list
	 */
	@Query(named = "AdmPagina.findFuncionalidadesPorPagina")
	List<AdmFuncionalidade> findFuncionalidadesPorPagina(AdmPagina pagina);

}
