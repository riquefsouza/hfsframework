/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.logging.log4j.Logger;

import br.com.hfsframework.util.exceptions.TransacaoException;

/**
 * The Class BasePersistence.
 *
 * @param <T>
 *            the generic type
 * @param <I>
 *            the generic type
 */
public abstract class BasePersistence<T, I extends Serializable> implements IBaseCrud<T, I> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The entidade classe. */
	private Class<T> entidadeClasse;
	
	/** The log. */
	@Inject
	protected Logger log;
	
	/** The em. */
	@Inject
	private EntityManager em;
		
	/**
	 * Instantiates a new base persistence.
	 *
	 * @param entidadeClasse
	 *            the entidade classe
	 */
	public BasePersistence(Class<T> entidadeClasse){
		this.entidadeClasse = entidadeClasse;
	}
	
	/**
	 * Gets the entity manager.
	 *
	 * @return the entity manager
	 */
	protected EntityManager getEntityManager(){
		return em;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#load(java.io.Serializable)
	 */
	@Override
	public T load(I id) {
		return em.find(entidadeClasse, id);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#findAll()
	 */
	@Override
	public List<T> findAll() {
        CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entidadeClasse);
        cq.select(cq.from(entidadeClasse));
        return em.createQuery(cq).getResultList();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#insert(java.lang.Object)
	 */
	@Override
	@Transactional
	public T insert(T bean) throws TransacaoException {
		try {
			em.persist(bean);
			em.flush();
			return bean;
		} catch (Exception e) {
			throw new TransacaoException(log, ERRO_INSERT + e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#update(java.lang.Object)
	 */
	@Override
	@Transactional
	public T update(T bean) throws TransacaoException {
		T rbean;
		try {
			rbean = em.merge(bean);
			em.flush();
			return rbean;			
		} catch (Exception e) {
			throw new TransacaoException(log, ERRO_UPDATE + e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#delete(java.lang.Object)
	 */
	@Override
	@Transactional
	public void delete(T bean) throws TransacaoException {
		try {
			em.remove(em.merge(bean));
			em.flush();
		} catch (Exception e) {
			throw new TransacaoException(log, ERRO_DELETE + e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#listarPaginada(int, int)
	 */
	@Override
	public List<T> listarPaginada(int numeroPagina, int tamanhoPagina) {
		//SQL_PAGINACAO = "select * from (select tabela.*, id linha from (select * from TABELA order by id) tabela where id < ((? * ?) + 1 )) where linha >= (((?-1) * ?) + 1)";		
        return null;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#listarPorIntervalo(int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> listarPorIntervalo(int intervaloInicial, int intervaloFinal) {
		//SQL_INTERVALO = "select * from (select tabela.*, id linha from (select * from TABELA order by id) tabela where id <= ?) where linha >= ?";
		
        CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entidadeClasse);
        cq.select(cq.from(entidadeClasse));
        Query q = em.createQuery(cq);
        q.setMaxResults(intervaloFinal - intervaloInicial + 1);
        q.setFirstResult(intervaloInicial);
        return q.getResultList();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#getDescricao(java.io.Serializable)
	 */
	@Override
	public String getDescricao(I id) {
		return null;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#existeNovo(java.lang.String)
	 */
	@Override
	public boolean existeNovo(String novo) {
		return false;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#existeAntigo(java.io.Serializable, java.lang.String)
	 */
	@Override
	public boolean existeAntigo(I id, String novo) {
		return false;
	}

	/**
	 * Count.
	 *
	 * @return the long
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public long count(){
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(entidadeClasse);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).longValue();
	}
}
