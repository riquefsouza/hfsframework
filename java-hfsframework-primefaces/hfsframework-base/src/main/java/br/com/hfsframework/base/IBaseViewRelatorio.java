/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base;

import java.io.Serializable;

/**
 * The Interface IBaseViewRelatorio.
 */
public interface IBaseViewRelatorio extends Serializable {

	/**
	 * Exportar.
	 */
	void exportar();

	/**
	 * Cancelar.
	 *
	 * @return the string
	 */
	String cancelar();
}
