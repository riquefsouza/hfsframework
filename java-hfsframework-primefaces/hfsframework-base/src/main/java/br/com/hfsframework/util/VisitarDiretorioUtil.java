/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * The Class VisitarDiretorioUtil.
 */
public final class VisitarDiretorioUtil {
	
	/** The instancia. */
	private static VisitarDiretorioUtil instancia;

	/** The lista dir. */
	private ArrayList<String> listaDir;
	
	/** The lista file. */
	private ArrayList<File> listaFile;

	/**
	 * Instantiates a new visitar diretorio.
	 */
	private VisitarDiretorioUtil() {
		listaDir = new ArrayList<String>();
		listaFile = new ArrayList<File>();
	}

	/**
	 * Pega o the instancia.
	 *
	 * @return o the instancia
	 */
	public static VisitarDiretorioUtil getInstancia() {
		if (instancia == null) {
			instancia = new VisitarDiretorioUtil();
		}
		return instancia;
	}

	/**
	 * Pega o the lista dir.
	 *
	 * @return o the lista dir
	 */
	public ArrayList<String> getListaDir() {
		return listaDir;
	}

	/**
	 * Pega o the lista file.
	 *
	 * @return o the lista file
	 */
	public ArrayList<File> getListaFile() {
		return listaFile;
	}

	/**
	 * Listar diretorio.
	 *
	 * @param sCaminho
	 *            the s caminho
	 * @param sExtensao
	 *            the s extensao
	 * @return true, if successful
	 */
	public boolean ListarDiretorio(String sCaminho, final String sExtensao) {
		listaDir.clear();
		listaFile.clear();
		FilenameFilter filtro = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(sExtensao);
			}
		};
		visitaTodosDiretoriosArquivos(new File(sCaminho), filtro);
		listaDir.remove(0); // sempre vai remover o primeiro caminho
		listaFile.remove(0);
		return (listaDir.size() > 0);
	}

	/**
	 * Listar diretorio.
	 *
	 * @param sCaminho
	 *            the s caminho
	 * @return true, if successful
	 */
	public boolean ListarDiretorio(String sCaminho) {
		listaDir.clear();
		listaFile.clear();
		visitaTodosDiretoriosArquivos(new File(sCaminho));
		listaDir.remove(0); // sempre vai remover o primeiro caminho
		listaFile.remove(0);
		return (listaDir.size() > 0);
	}

	/**
	 * Processa todos diretorios arquivos.
	 *
	 * @param dir
	 *            the dir
	 */
	private void processaTodosDiretoriosArquivos(File dir) {
		listaFile.add(dir);
		listaDir.add(dir.getAbsolutePath());
	}

	/**
	 * Visita todos diretorios arquivos.
	 *
	 * @param dir
	 *            the dir
	 * @param filtro
	 *            the filtro
	 */
	private void visitaTodosDiretoriosArquivos(File dir, FilenameFilter filtro) {
		processaTodosDiretoriosArquivos(dir);

		if (dir.isDirectory()) {
			String[] children = dir.list(filtro);
			for (int i = 0; i < children.length; i++) {
				visitaTodosDiretoriosArquivos(new File(dir, children[i]),
						filtro);
			}
		}
	}

	/**
	 * Visita todos diretorios arquivos.
	 *
	 * @param dir
	 *            the dir
	 */
	private void visitaTodosDiretoriosArquivos(File dir) {
		processaTodosDiretoriosArquivos(dir);

		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				visitaTodosDiretoriosArquivos(new File(dir, children[i]));
			}
		}
	}
}