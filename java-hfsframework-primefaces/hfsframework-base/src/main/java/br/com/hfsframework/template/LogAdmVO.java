/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.template;

public class LogAdmVO {

	private String esquema;
	private String tabela;	
	private String tabelaAbreviada;
	private String entidade;
	private String chave;
	private String campos;
	private String colunas;
	private String xColunas;			
	private String newColunas;
	private String oldColunas;
	
	public String getTabela() {
		return tabela;
	}

	public void setTabela(String tabela) {
		this.tabela = tabela;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getCampos() {
		return campos;
	}

	public void setCampos(String campos) {
		this.campos = campos;
	}

	public String getTabelaAbreviada() {
		return tabelaAbreviada;
	}

	public void setTabelaAbreviada(String tabelaAbreviada) {
		this.tabelaAbreviada = tabelaAbreviada;
	}

	public String getEsquema() {
		return esquema;
	}

	public void setEsquema(String esquema) {
		this.esquema = esquema;
	}

	public String getColunas() {
		return colunas;
	}

	public void setColunas(String colunas) {
		this.colunas = colunas;
	}

	public String getxColunas() {
		return xColunas;
	}

	public void setxColunas(String xColunas) {
		this.xColunas = xColunas;
	}

	public String getNewColunas() {
		return newColunas;
	}

	public void setNewColunas(String newColunas) {
		this.newColunas = newColunas;
	}

	public String getOldColunas() {
		return oldColunas;
	}

	public void setOldColunas(String oldColunas) {
		this.oldColunas = oldColunas;
	}

	public String getEntidade() {
		return entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	@Override
	public String toString() {
		return "LogAdmVO [esquema=" + esquema + ", tabela=" + tabela + ", tabelaAbreviada=" + tabelaAbreviada
				+ ", entidade=" + entidade + ", chave=" + chave + ", campos=" + campos + ", colunas=" + colunas
				+ ", xColunas=" + xColunas + ", newColunas=" + newColunas + ", oldColunas=" + oldColunas + "]";
	}
		
}
