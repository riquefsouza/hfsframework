/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.template;

/**
 * The Enum TemplateEnum.
 */
public enum TemplateEnum {
	
	/** The Repository. */
	Repository("data", "Repository", "Data Access Object"),
	
	/** The bc. */
	BC("business", "BC", "Business Controller"),
	
	/** The mb. */
	MB("view", "MB", "Managed Bean"),
	
	/** The Rel MB. */
	RelMB("view", "RelMB", "Managed Bean de Relatório"),
	
	/** The Converter. */
	Converter("converters", "Converter", "Converter"),
	
	/** The Lazy sorter. */
	LazySorter("view", "LazySorter", "LazySorter"),
	
	/** The Lazy data model. */
	LazyDataModel("view", "LazyDataModel", "LazyDataModel"),
	
	/** The listar. */
	listar("", "listar", "listar página"),
	
	/** The editar. */
	editar("", "editar", "editar página"),
	
	/** The reportar. */
	reportar("", "reportar", "reportar página"),
	
	/** The retrato. */
	retrato("relatorios", "Retrato", "Relatório de Orientação Retrato"),
	
	/** The paisagem. */
	paisagem("relatorios", "Paisagem", "Relatório de Orientação Paisagem");
	
	/** The diretorio. */
	private String diretorio;
	
	/** The tipo. */
	private String tipo;
	
	/** The descricao. */
	private String descricao;
	
	/**
	 * Instantiates a new template enum.
	 *
	 * @param diretorio
	 *            the diretorio
	 * @param tipo
	 *            the tipo
	 * @param descricao
	 *            the descricao
	 */
	private TemplateEnum(String diretorio, String tipo, String descricao){
		this.diretorio = diretorio;
		this.tipo = tipo;
		this.descricao = descricao;
	}

	/**
	 * Pega o the diretorio.
	 *
	 * @return o the diretorio
	 */
	public String getDiretorio() {
		return diretorio;
	}

	/**
	 * Pega o the tipo.
	 *
	 * @return o the tipo
	 */
	public String getTipo() {
		return tipo;
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
