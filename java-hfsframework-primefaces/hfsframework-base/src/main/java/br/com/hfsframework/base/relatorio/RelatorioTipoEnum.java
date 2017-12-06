/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base.relatorio;

/**
 * The Enum RelatorioTipoEnum.
 */
public enum RelatorioTipoEnum {
	
	/** The pdf. */
	//Documentos
	PDF("application/pdf", "Portable Document Format (.pdf)"),
	
	/** The docx. */
	DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "Microsoft Word XML (.docx)"),
	
	/** The rtf. */
	RTF("application/rtf", "Rich Text Format (.rtf)"),
	
	/** The odt. */
	ODT("application/vnd.oasis.opendocument.text", "OpenDocument Text (.odt)"),

	//Planilhas
	/** The xlsx. */
	//XLS("application/vnd.ms-excel", "Microsoft Excel (.xls)"),
	XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "Microsoft Excel XML (.xlsx)"),
	
	/** The ods. */
	ODS("application/vnd.oasis.opendocument.spreadsheet", "OpenDocument Spreadsheet (.ods)"),

	/** The csv. */
	//Texto puro
	CSV("text/plain", "Valores Separados Por Vírgula (.csv)"),
	
	/** The txt. */
	TXT("text/plain", "Somente Texto (.txt)"),	 	

	/** The pptx. */
	//Outros
	PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation", "Microsoft Powerpoint XML (.pptx)"),	
	
	/** The html. */
	//HTML("text/html", "Linguagem de Marcação de Hipertexto (.html)");
	HTML("application/zip", "Linguagem de Marcação de Hipertexto (.html)");
	
	/** The tipo conteudo. */
	private String tipoConteudo;

	/** The descricao. */
	private String descricao;

	/**
	 * Instantiates a new relatorio tipo enum.
	 *
	 * @param tipoConteudo
	 *            the tipo conteudo
	 * @param descricao
	 *            the descricao
	 */
	private RelatorioTipoEnum(String tipoConteudo, String descricao) {
		this.tipoConteudo = tipoConteudo;
		this.descricao = descricao;
	}

	/**
	 * Pega o the tipo conteudo.
	 *
	 * @return o the tipo conteudo
	 */
	public String getTipoConteudo() {
		return tipoConteudo;
	}

	/**
	 * Pega o the descricao.
	 *
	 * @return o the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

}
