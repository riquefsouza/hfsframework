/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class PermissaoVO.
 */
public class PermissaoVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The perfil. */
	private PerfilVO perfil;
	
	/** The funcionalidades. */
	private List<FuncionalidadeVO> funcionalidades;
	
	/** The paginas funcionalidade. */
	private List<PaginaVO> paginasFuncionalidade;
	
	/** The paginas. */
	private List<PaginaVO> paginas;

	/**
	 * Instantiates a new permissao VO.
	 */
	public PermissaoVO() {
		super();

		this.funcionalidades = new ArrayList<FuncionalidadeVO>();
		this.paginasFuncionalidade = new ArrayList<PaginaVO>();
		this.paginas = new ArrayList<PaginaVO>();

		limpar();
	}

	/**
	 * Limpar.
	 */
	public void limpar() {
		this.perfil = new PerfilVO();
		this.funcionalidades.clear();
		this.paginasFuncionalidade.clear();
		this.paginas.clear();
	}

	/**
	 * Pega o the perfil.
	 *
	 * @return o the perfil
	 */
	public PerfilVO getPerfil() {
		return perfil;
	}

	/**
	 * Atribui o the perfil.
	 *
	 * @param perfil
	 *            o novo the perfil
	 */
	public void setPerfil(PerfilVO perfil) {
		this.perfil = perfil;
	}

	/**
	 * Pega o the funcionalidades.
	 *
	 * @return o the funcionalidades
	 */
	public List<FuncionalidadeVO> getFuncionalidades() {
		return funcionalidades;
	}

	/**
	 * Atribui o the funcionalidades.
	 *
	 * @param funcionalidades
	 *            o novo the funcionalidades
	 */
	public void setFuncionalidades(List<FuncionalidadeVO> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	/**
	 * Pega o the paginas funcionalidade.
	 *
	 * @return o the paginas funcionalidade
	 */
	public List<PaginaVO> getPaginasFuncionalidade() {
		return paginasFuncionalidade;
	}

	/**
	 * Atribui o the paginas funcionalidade.
	 *
	 * @param paginasFuncionalidade
	 *            o novo the paginas funcionalidade
	 */
	public void setPaginasFuncionalidade(List<PaginaVO> paginasFuncionalidade) {
		this.paginasFuncionalidade = paginasFuncionalidade;
	}

	/**
	 * Pega o the paginas.
	 *
	 * @return o the paginas
	 */
	public List<PaginaVO> getPaginas() {
		return paginas;
	}

	/**
	 * Atribui o the paginas.
	 *
	 * @param paginas
	 *            o novo the paginas
	 */
	public void setPaginas(List<PaginaVO> paginas) {
		this.paginas = paginas;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PermissaoVO [perfil=" + perfil + ", funcionalidades=" + funcionalidades + ", paginasFuncionalidade="
				+ paginasFuncionalidade + ", paginas=" + paginas + "]";
	}



}
