/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin;

import br.com.hfsframework.admin.data.AdmParametroRepository;
import br.com.hfsframework.admin.model.AdmParametro;
import br.com.hfsframework.base.BaseBusinessController;

/**
 * The Class AdmParametroBC.
 */
public class AdmParametroBC extends BaseBusinessController<AdmParametro, Long, AdmParametroRepository> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseBusinessController#getDescricao(java.io.Serializable)
	 */
	@Override
	public String getDescricao(Long id){
		return repositorio.getDescricao(id);
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseBusinessController#existeNovo(java.lang.String)
	 */
	@Override
	public boolean existeNovo(String novo){
		Long existe = repositorio.countNovo(novo.toLowerCase());
		return (existe > 0);
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseBusinessController#existeAntigoNovo(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean existeAntigo(Long id, String novo){
		String antigo = getDescricao(id);
		Long existe = repositorio.countAntigo(antigo.toLowerCase(), novo.toLowerCase());
		return (existe > 0);
	}
		
	/**
	 * Gets the valor by codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the valor by codigo
	 */
	public String getValorByCodigo(String codigo){
		return repositorio.getValorByCodigo(codigo);
	}
}
