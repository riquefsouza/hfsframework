/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.metadados;

import java.io.Serializable;

public class MetadadosConfig implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String conexaoURL;

	private String driverClasse;

	private String usuario;

	private String senha;

	public MetadadosConfig() {
		super();
	}

	public MetadadosConfig(String conexaoURL, String driverClasse, String usuario, String senha) {
		super();
		this.conexaoURL = conexaoURL;
		this.driverClasse = driverClasse;
		this.usuario = usuario;
		this.senha = senha;
	}

	public static MetadadosConfig hfsbancoDS() {
		String conexaoURL = "jdbc:postgresql://localhost:5432/hfsbanco";
		String driverClasse = "org.postgresql.Driver";
		String usuario = "postgres";
		String senha = "abcd1234";

		MetadadosConfig cfg = new MetadadosConfig(conexaoURL, driverClasse, usuario, senha);
		return cfg;
	}

	public String getConexaoURL() {
		return conexaoURL;
	}

	public void setConexaoURL(String conexaoURL) {
		this.conexaoURL = conexaoURL;
	}

	public String getDriverClasse() {
		return driverClasse;
	}

	public void setDriverClasse(String driverClasse) {
		this.driverClasse = driverClasse;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "MetadadosConfig [conexaoURL=" + conexaoURL + ", driverClasse=" + driverClasse + ", usuario=" + usuario
				+ ", senha=" + senha + "]";
	}

}
