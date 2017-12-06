/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Modifying;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import br.com.hfsframework.admin.model.AdmUsuario;
import br.com.hfsframework.admin.model.AdmUsuarioPK;

// TODO: Auto-generated Javadoc
/**
 * The Interface AdmUsuarioRepository.
 */
@Repository(forEntity = AdmUsuario.class)
public interface AdmUsuarioRepository extends EntityRepository<AdmUsuario, AdmUsuarioPK> {

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
	 * @param data the data
	 * @param email the email
	 * @param ldapDN the ldap DN
	 * @param nome the nome
	 * @param matricula the matricula
	 * @param ip the ip
	 * @param login the login
	 * @return the int
	 */
	@Modifying
	@Query("UPDATE AdmUsuario as u SET u.cpf=?1, u.data=?2, u.email=?3, u.ldapDN=?4, u.nome=?5 WHERE u.id.matricula=?6 AND u.id.ip=?7 AND u.login = ?8")
	int updateByLogin(BigDecimal cpf, Date data, String email, String ldapDN, String nome, 
			Long matricula, String ip, String login);

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
