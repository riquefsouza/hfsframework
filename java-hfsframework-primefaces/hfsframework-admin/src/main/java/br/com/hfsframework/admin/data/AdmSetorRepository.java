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

import br.com.hfsframework.admin.model.AdmSetor;

/**
 * The Interface AdmSetorRepository.
 */
@Repository(forEntity = AdmSetor.class)
public interface AdmSetorRepository extends EntityRepository<AdmSetor, String> {

	/**
	 * Find by setor pai.
	 *
	 * @param paiSetor
	 *            the pai setor
	 * @return the list
	 */
	@Query(named = "AdmSetor.findBySetorPai")
	List<AdmSetor> findBySetorPai(String paiSetor);
}
