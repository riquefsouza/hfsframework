/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.inject.Named;
import javax.swing.text.MaskFormatter;

import org.apache.commons.lang3.ObjectUtils;

/**
 * The Class BaseUtil.
 */
@Named
@SuppressWarnings("rawtypes")
public class BaseUtil implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Nvl.
	 *
	 * @param obj
	 *            the obj
	 * @return the string
	 */
	public static String nvlStr(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}

	/**
	 * Nvl int.
	 *
	 * @param obj
	 *            the obj
	 * @return the integer
	 */
	public static Integer nvlInt(Object obj) {
		if (obj == null) {
			return null;
		} else {
			return Integer.parseInt(obj.toString());
		}
	}

	/**
	 * Nvl long.
	 *
	 * @param obj
	 *            the obj
	 * @return the long
	 */
	public static Long nvlLong(Object obj) {
		if (obj == null) {
			return null;
		} else {
			return Long.parseLong(obj.toString());
		}
	}

	/**
	 * Nvl big decimal.
	 *
	 * @param obj
	 *            the obj
	 * @return the big decimal
	 */
	public static BigDecimal nvlBigDecimal(Object obj) {
		if (obj == null) {
			return null;
		} else {
			return new BigDecimal(obj.toString());
		}
	}

	/**
	 * Rand int.
	 *
	 * @param min
	 *            the min
	 * @param max
	 *            the max
	 * @return the int
	 */
	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	/**
	 * Rand big decimal.
	 *
	 * @param min
	 *            the min
	 * @param max
	 *            the max
	 * @return the big decimal
	 */
	public static BigDecimal randBigDecimal(int min, int max) {
		double valor = randInt(min, max * 100) * 0.001;
		BigDecimal bd = new BigDecimal(valor);
		return bd;
	}

	/**
	 * Checks if is negativo.
	 *
	 * @param valor
	 *            the valor
	 * @return true, if is negativo
	 */
	public boolean negativo(BigDecimal valor) {
		return (valor.compareTo(BigDecimal.ZERO) == -1);
	}

	/**
	 * Gets the padrao.
	 *
	 * @param <T>
	 *            the generic type
	 * @param o
	 *            the o
	 * @param padrao
	 *            the padrao
	 * @return the padrao
	 */
	public static <T> T getPadrao(T o, T padrao) {
		return o != null ? o : padrao;
	}

	/**
	 * Checks if is string empty.
	 *
	 * @param s
	 *            the s
	 * @return true, if is string empty
	 */
	public static boolean isStringEmpty(String s) {
		return (s == null) || (s.trim().isEmpty());
	}

	/**
	 * Compare.
	 *
	 * @param ignoreStringCase
	 *            the ignore string case
	 * @param lista
	 *            the lista
	 * @return the int
	 */
	@SuppressWarnings("unchecked")
	private static int compare(boolean ignoreStringCase, Comparable... lista) {
		if ((lista == null) || (lista.length == 0)) {
			return 0;
		}
		int comparacao = 0;
		for (int i = 0; i < lista.length; i += 2) {
			if ((ignoreStringCase) && ((lista[i] instanceof String)) && ((lista[(i + 1)] instanceof String))) {
				comparacao = ((String) lista[i]).compareToIgnoreCase((String) lista[(i + 1)]);
			} else {
				comparacao = ObjectUtils.compare(lista[i], lista[(i + 1)]);
			}
			if (comparacao != 0) {
				return comparacao;
			}
		}
		return comparacao;
	}

	/**
	 * Compare.
	 *
	 * @param lista
	 *            the lista
	 * @return the int
	 */
	public static int compare(Comparable... lista) {
		return compare(false, lista);
	}

	/**
	 * Compare ignore case.
	 *
	 * @param lista
	 *            the lista
	 * @return the int
	 */
	public static int compareIgnoreCase(Comparable... lista) {
		return compare(true, lista);
	}

	/**
	 * Aplicar mascara.
	 *
	 * @param texto
	 *            the texto
	 * @param mascara
	 *            the mascara
	 * @return the string
	 */
	public static String aplicarMascara(String texto, String mascara) {
		if ((!isStringEmpty(texto)) && (!isStringEmpty(mascara))) {
			try {
				MaskFormatter mf = new MaskFormatter(mascara);
				mf.setValueContainsLiteralCharacters(false);
				return mf.valueToString(texto);
			} catch (ParseException e) {
				return null;
			}
		}
		return texto;
	}

	/**
	 * Produzir.
	 *
	 * @param <T>
	 *            the generic type
	 * @param <R>
	 *            the generic type
	 * @param collection
	 *            the collection
	 * @param producer
	 *            the producer
	 * @return the list
	 */
	public static <T, R> List<R> produzir(Collection<T> collection, IBaseProducer<R, T> producer) {
		List<R> retorno = new ArrayList<R>();
		if ((collection != null) && (producer != null)) {
			for (T item : collection) {
				R produced = producer.aplicar(item);
				if (produced != null) {
					retorno.add(produced);
				}
			}
		}
		return retorno;
	}

}
