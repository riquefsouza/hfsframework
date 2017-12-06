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
import javax.transaction.Transactional;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.logging.log4j.Logger;

import br.com.hfsframework.AplicacaoUtil;
import br.com.hfsframework.util.exceptions.TransacaoException;

/**
 * The Class BaseBusinessController.
 *
 * @param <T>
 *            the generic type
 * @param <I>
 *            the generic type
 * @param <C>
 *            the generic type
 */
public abstract class BaseBusinessController<T, I extends Serializable, C extends EntityRepository<T, I>>
		implements IBaseCrud<T, I> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	@Inject
	protected Logger log;

	/** The repositorio. */
	@Inject
	protected C repositorio;

	/** The aplicacao util. */
	@Inject
	protected AplicacaoUtil aplicacaoUtil;
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#load(java.io.Serializable)
	 */
	@Override
	public T load(I id) {
		return repositorio.findBy(id);
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#findAll()
	 */
	@Override
	public List<T> findAll() {
		return repositorio.findAll();
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#delete(java.lang.Object)
	 */
	@Override
	@Transactional
	public void delete(T bean) throws TransacaoException {
		try {
			repositorio.attachAndRemove(bean);
			repositorio.flush();
		} catch (Exception e) {
			throw new TransacaoException(log, ERRO_DELETE + e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#insert(java.lang.Object)
	 */
	@Override
	@Transactional
	public T insert(T bean) throws TransacaoException {
		try {
			return repositorio.saveAndFlush(bean);
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
		try {
			return repositorio.saveAndFlush(bean);
		} catch (Exception e) {
			throw new TransacaoException(log, ERRO_UPDATE + e.getMessage(), e);
		}
	}

	/**
	 * Pega o the repositorio.
	 *
	 * @return o the repositorio
	 */
	public C getRepositorio() {
		return repositorio;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#listarPaginada(int, int)
	 */
	@Override
	public List<T> listarPaginada(int numeroPagina, int tamanhoPagina){
		//SQL_PAGINACAO = "select * from (select tabela.*, id linha from (select * from TABELA order by id) tabela where id < ((? * ?) + 1 )) where linha >= (((?-1) * ?) + 1)";		
        return null;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#listarPorIntervalo(int, int)
	 */
	@Override
	public List<T> listarPorIntervalo(int intervaloInicial, int intervaloFinal){
		//SQL_INTERVALO = "select * from (select tabela.*, id linha from (select * from TABELA order by id) tabela where id <= ?) where linha >= ?";
		return null;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#getDescricao(java.io.Serializable)
	 */
	@Override
	public String getDescricao(I id){
		return "";
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#existeNovo(java.lang.String)
	 */
	@Override
	public boolean existeNovo(String novo){
		return false;
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.IBaseCrud#existeAntigoNovo(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean existeAntigo(I id, String novo){
		return false;
	}
	
}
