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

import br.com.hfsframework.admin.model.VwAdmLogValor;

/**
 * The Interface VwAdmLogValorRepository.
 */
@Repository(forEntity = VwAdmLogValor.class)
public interface VwAdmLogValorRepository extends EntityRepository<VwAdmLogValor, Long> {

	/**
	 * Find by filtros.
	 *
	 * @param usuario
	 *            the usuario
	 * @param dataNumero
	 *            the data numero
	 * @param operacao
	 *            the operacao
	 * @param ip
	 *            the ip
	 * @param entidade
	 *            the entidade
	 * @param tabela
	 *            the tabela
	 * @param chave
	 *            the chave
	 * @return the list
	 */
	@Query(named = "VwAdmLogValor.findByFiltros")
	List<VwAdmLogValor> findByFiltros(String usuario, Long dataNumero, String operacao, String ip, String entidade,
			String tabela, String chave);

}
