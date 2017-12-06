/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.base;

/**
 * The Enum BaseQueryNativoEnum.
 */
public enum BaseQueryNativoEnum {

	/** The paginacao. */
	PAGINACAO("select * from (select tabela.*, id linha from (select * from ?1 order by id) tabela where id < ((?2 * ?3) + 1 )) where linha >= (((?2-1) * ?3) + 1)"),
	
	/** The intervalo. */
	INTERVALO("select * from (select tabela.*, id linha from (select * from ?1 order by id) tabela where id <= ?3) where linha >= ?2");	

	/** The sql nativo. */
	private String sqlNativo;

	/**
	 * Instantiates a new base query nativo enum.
	 *
	 * @param sqlNativo
	 *            the sql nativo
	 */
	private BaseQueryNativoEnum(String sqlNativo) {
		this.sqlNativo = sqlNativo;
	}

	/**
	 * Pega o the sql nativo.
	 *
	 * @return o the sql nativo
	 */
	public String getSqlNativo() {
		return sqlNativo;
	}

}
