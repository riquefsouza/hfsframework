/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.data;

import java.math.BigDecimal;
import java.util.List;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Modifying;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import br.com.hfsframework.admin.model.AdmUsuario;

// TODO: Auto-generated Javadoc
/**
 * The Interface AdmUsuarioRepository.
 */
@Repository(forEntity = AdmUsuario.class)
public interface AdmUsuarioRepository extends EntityRepository<AdmUsuario, Long> {

	@Query(named = "AdmUsuario.findByLogin")
	AdmUsuario findByLogin(String login);
	
	/**
	 * Login.
	 *
	 * @param login the login
	 * @param senha the senha
	 * @return the list
	 */
	@Query(named = "AdmUsuario.login")
	List<AdmUsuario> login(String login, String senha);

	/**
	 * Update by login.
	 *
	 * @param cpf the cpf
	 * @param email the email
	 * @param ldapDN the ldap DN
	 * @param nome the nome
	 * @param id the id
	 * @param login the login
	 * @return the int
	 */
	@Modifying
	@Query("UPDATE AdmUsuario as u SET u.cpf=?1, u.email=?2, u.ldapDN=?3, u.nome=?4 WHERE u.id=?5 AND u.login = ?6")
	int updateByLogin(BigDecimal cpf, String email, String ldapDN, String nome,	Long id, String login);

	/**
	 * Update senha.
	 *
	 * @param senha the senha
	 * @param login the login
	 * @return the int
	 */
	@Modifying
	@Query("UPDATE AdmUsuario as u SET u.senha = ?1 WHERE u.login = ?2")
	int updateSenha(String senha, String login);
	
	/**
	 * Find IP by oracle.
	 *
	 * @return the list
	 */
	@Query(named = "AdmUsuario.findIPByOracle", isNative = true)
	List<Object> findIPByOracle();
	
	/**
	 * Find IP by postgre SQL.
	 *
	 * @return the list
	 */
	@Query(named = "AdmUsuario.findIPByPostgreSQL", isNative = true)
	List<Object> findIPByPostgreSQL();
	
	/**
	 * Sets the login postgre SQL.
	 *
	 * @param login the login
	 * @return the list
	 */
	@Query(named = "AdmUsuario.setLoginPostgreSQL", isNative = true)
	List<Object> setLoginPostgreSQL(String login);
	
	/**
	 * Sets the IP postgre SQL.
	 *
	 * @param ip the ip
	 * @return the list
	 */
	@Query(named = "AdmUsuario.setIPPostgreSQL", isNative = true)
	List<Object> setIPPostgreSQL(String ip);
}
