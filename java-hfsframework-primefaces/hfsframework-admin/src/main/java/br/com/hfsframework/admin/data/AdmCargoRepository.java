/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.data;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import br.com.hfsframework.admin.model.AdmCargo;

/**
 * The Interface AdmCargoRepository.
 */
@Repository(forEntity = AdmCargo.class)
public interface AdmCargoRepository extends EntityRepository<AdmCargo, Long> {

}
