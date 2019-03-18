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

import br.com.hfsframework.admin.model.VwAdmLog;

/**
 * The Interface VwAdmLogRepository.
 */
@Repository(forEntity = VwAdmLog.class)
public interface VwAdmLogRepository extends EntityRepository<VwAdmLog, Long> {

	@Query(named = "VwAdmLog.findEntidades")
	List<String> findEntidades();

	/**
	 * Find by filtros.
	 *
	 * @param entidade
	 *            the entidade
	 * @param usuario
	 *            the usuario
	 * @param ip
	 *            the ip
	 * @param operacao
	 *            the operacao
	 * @param chave
	 *            the chave
	 * @param dataInicio
	 *            the data inicio
	 * @param dataFim
	 *            the data fim
	 * @return the list
	 */
	@Query(named = "VwAdmLog.findByFiltros")
	List<VwAdmLog> findByFiltros(String entidade, String usuario, String ip, String operacao, String chave,
			Long dataInicio, Long dataFim);


	
}
