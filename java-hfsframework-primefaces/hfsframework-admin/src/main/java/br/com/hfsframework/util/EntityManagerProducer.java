/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * The Class EntityManagerProducer.
 */
public class EntityManagerProducer {

	/** The emf. */
	@PersistenceUnit(unitName = "padraoPU")
	//@PersistenceContext
	private EntityManagerFactory emf;

	/**
	 * Cria o.
	 *
	 * @return the entity manager
	 */
	@Produces
	@Default
	//@RequestScoped	
	//@TransactionScoped
	public EntityManager create() {
		return emf.createEntityManager();
	}

	/**
	 * Gets the cache.
	 *
	 * @return the cache
	 */
	@Produces
	public Cache getCache() {
		return emf.getCache();
	}

	/**
	 * Close.
	 *
	 * @param em
	 *            the em
	 */
	public void close(@Disposes @Default EntityManager em) {
		if (em.isOpen()) {
			em.close();
		}
	}
}
