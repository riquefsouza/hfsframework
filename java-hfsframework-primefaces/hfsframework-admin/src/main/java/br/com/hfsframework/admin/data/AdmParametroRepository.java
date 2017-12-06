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

import br.com.hfsframework.admin.model.AdmParametro;

/**
 * The Interface AdmParametroRepository.
 */
@Repository(forEntity = AdmParametro.class)
public interface AdmParametroRepository extends EntityRepository<AdmParametro, Long> {

	/**
	 * Gets the descricao.
	 *
	 * @param id
	 *            the id
	 * @return the descricao
	 */
	@Query(named = "AdmParametro.getDescricaoById")
	String getDescricao(Long id);
	
	/**
	 * Count novo.
	 *
	 * @param novo
	 *            the novo
	 * @return the long
	 */
	@Query(named = "AdmParametro.countNovo")
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
	@Query(named = "AdmParametro.countAntigo")
	Long countAntigo(String antigo, String novo);
		
	/**
	 * Gets the valor by codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the valor by codigo
	 */
	@Query(named = "AdmParametro.getValorByCodigo")
	String getValorByCodigo(String codigo);
}
