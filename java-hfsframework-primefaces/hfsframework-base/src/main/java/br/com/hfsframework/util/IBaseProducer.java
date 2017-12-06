/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util;

/**
 * The Interface IBaseProducer.
 *
 * @param <R>
 *            the generic type
 * @param <T>
 *            the generic type
 */
public abstract interface IBaseProducer<R, T> {
	
	/**
	 * Aplicar.
	 *
	 * @param obj
	 *            the obj
	 * @return the r
	 */
	public abstract R aplicar(T obj);
	
}
