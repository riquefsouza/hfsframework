/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.data;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.hfsframework.admin.model.AdmLogColuna;

/**
 * The Interface AdmLogColunaRepository.
 */
@Repository(forEntity = AdmLogColuna.class)
public interface AdmLogColunaRepository extends EntityRepository<AdmLogColuna, String> {

}
