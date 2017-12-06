/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.arquivo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class ArquivoUtil.
 */
public final class ArquivoUtil implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static Logger log = LogManager.getLogger(ArquivoUtil.class);

	/**
	 * Ler numero linhas.
	 *
	 * @param file
	 *            the file
	 * @param lines
	 *            the lines
	 * @return the string
	 */
	public static String lerNumeroLinhas(File file, int lines) {
		StringBuilder sb = new StringBuilder();
		String linha;
		int total = lines - 1;
		ReversedLinesFileReader rlfr = null;

		if (lines > 0) {
			try {
				for (int i = 0; i < lines; i++) {
					sb.append("\n");
				}

				rlfr = new ReversedLinesFileReader(file);
				while ((linha = rlfr.readLine()) != null) {
					sb.insert(total, linha);

					if (total == 0) {
						break;
					}

					total--;
				}
				return sb.toString();
			} catch (IOException e) {
				log.error(e);
			} finally {
				try {
					rlfr.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
		return "";
	}

	/**
	 * Ler linhas.
	 *
	 * @param file
	 *            the file
	 * @param lista
	 *            the lista
	 */
	public static void lerLinhas(File file, List<VisualizarArqLogServidorVO> lista) {
		VisualizarArqLogServidorVO vo;
		try {
			LineIterator it = FileUtils.lineIterator(file, "UTF-8");
			try {
				while (it.hasNext()) {
					vo = new VisualizarArqLogServidorVO(it.nextLine());
					lista.add(vo);
				}
			} finally {
				it.close();
			}
		} catch (IOException e) {
			log.error(e);
		}
	}
	
	public static void lerNumeroLinhas(File file, List<VisualizarArqLogServidorVO> lista, int lines) {
		String linha;
		int total = lines - 1;
		ReversedLinesFileReader rlfr = null;
		VisualizarArqLogServidorVO vo;

		if (lines > 0) {
			try {
				for (int i = 0; i < lines; i++) {
					vo = new VisualizarArqLogServidorVO("");
					lista.add(vo);
				}
				
				rlfr = new ReversedLinesFileReader(file);
				while ((linha = rlfr.readLine()) != null) {
					vo = new VisualizarArqLogServidorVO(linha);
					lista.set(total, vo);

					if (total == 0) {
						break;
					}

					total--;
				}
			} catch (IOException e) {
				log.error(e);
			} finally {
				try {
					rlfr.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
	}
	
}
