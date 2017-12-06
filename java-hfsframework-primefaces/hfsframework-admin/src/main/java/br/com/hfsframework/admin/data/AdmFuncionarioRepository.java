/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.data;

import java.math.BigDecimal;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import br.com.hfsframework.admin.model.AdmFuncionario;

/**
 * The Interface AdmFuncionarioRepository.
 */
@Repository(forEntity = AdmFuncionario.class)
public interface AdmFuncionarioRepository extends EntityRepository<AdmFuncionario, Long> {

	/**
	 * Find by matricula and CPF.
	 *
	 * @param id
	 *            the id
	 * @param cpf
	 *            the cpf
	 * @return the vw adm funcionario
	 */
	@Query(named = "AdmFuncionario.findByMatriculaAndCPF")
	AdmFuncionario findByMatriculaAndCPF(Long id, BigDecimal cpf);
}
