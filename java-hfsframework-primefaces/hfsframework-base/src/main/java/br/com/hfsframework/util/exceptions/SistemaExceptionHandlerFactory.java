/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.exceptions;

import javax.faces.context.ExceptionHandlerFactory;

/**
 * A factory for creating SistemaExceptionHandler objects.
 */
public class SistemaExceptionHandlerFactory
  extends ExceptionHandlerFactory
{
  
  /** The parent. */
  private final ExceptionHandlerFactory parent;
  
  /**
	 * Instantiates a new sistema exception handler factory.
	 *
	 * @param parent
	 *            the parent
	 */
  public SistemaExceptionHandlerFactory(ExceptionHandlerFactory parent)
  {
    this.parent = parent;
  }
  
  /* (non-Javadoc)
   * @see javax.faces.context.ExceptionHandlerFactory#getExceptionHandler()
   */
  public SistemaExceptionHandler getExceptionHandler()
  {
    return new SistemaExceptionHandler(this.parent.getExceptionHandler());
  }
}
