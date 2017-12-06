/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.template;

public class LogAdmValorVO {

	private String entidade;
	private String tabela;
	private String tabelaAbreviada;
	private String chave;
	private String coluna;
	private String campo;
	
	public String getEntidade() {
		return entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

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

	public String getColuna() {
		return coluna;
	}

	public void setColuna(String coluna) {
		this.coluna = coluna;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getTabelaAbreviada() {
		return tabelaAbreviada;
	}

	public void setTabelaAbreviada(String tabelaAbreviada) {
		this.tabelaAbreviada = tabelaAbreviada;
	}

	@Override
	public String toString() {
		return "LogAdmValorVO [entidade=" + entidade + ", tabela=" + tabela + ", tabelaAbreviada=" + tabelaAbreviada
				+ ", chave=" + chave + ", coluna=" + coluna + ", campo=" + campo + "]";
	}
	
	
}
