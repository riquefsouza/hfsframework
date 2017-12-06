/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base.relatorio;

import java.lang.reflect.Field;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * The Class BaseRelatorioProducer.
 */
public class BaseRelatorioProducer {
	
	/** The Constant DEFAULT_PATH. */
	private static final String DEFAULT_PATH = "relatorios/";
	
	/** The Constant DEFAULT_EXTENSION. */
	private static final String DEFAULT_EXTENSION = ".jasper";

	/**
	 * Instantiates a new base relatorio producer.
	 */
	public BaseRelatorioProducer() {
	}

	/**
	 * Cria o.
	 *
	 * @param ip
	 *            the ip
	 * @return the i base relatorio
	 */
	@Produces
	@Default
	public static IBaseRelatorio create(InjectionPoint ip) {
		Field field = (Field) ip.getMember();
		String path = DEFAULT_PATH + field.getName() + DEFAULT_EXTENSION;
		if (field.isAnnotationPresent(RelatorioPath.class)) {
			path = DEFAULT_PATH + ((RelatorioPath) field.getAnnotation(RelatorioPath.class)).value() + DEFAULT_EXTENSION;
		}

		return create(path);
	}

	/**
	 * Cria o.
	 *
	 * @param path
	 *            the path
	 * @return the i base relatorio
	 */
	public static IBaseRelatorio create(String path) {
		return new BaseRelatorioImpl(path);
	}
}
