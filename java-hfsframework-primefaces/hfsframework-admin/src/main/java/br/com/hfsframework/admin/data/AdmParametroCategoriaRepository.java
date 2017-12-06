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

import br.com.hfsframework.admin.model.AdmParametroCategoria;

/**
 * The Interface AdmParametroCategoriaRepository.
 */
@Repository(forEntity = AdmParametroCategoria.class)
public interface AdmParametroCategoriaRepository extends EntityRepository<AdmParametroCategoria, Long> {

	/**
	 * Gets the descricao.
	 *
	 * @param id
	 *            the id
	 * @return the descricao
	 */
	@Query(named = "AdmParametroCategoria.getDescricaoById")
	String getDescricao(Long id);
	
	/**
	 * Count novo.
	 *
	 * @param novo
	 *            the novo
	 * @return the long
	 */
	@Query(named = "AdmParametroCategoria.countNovo")
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
	@Query(named = "AdmParametroCategoria.countAntigo")
	Long countAntigo(String antigo, String novo);
	
}
