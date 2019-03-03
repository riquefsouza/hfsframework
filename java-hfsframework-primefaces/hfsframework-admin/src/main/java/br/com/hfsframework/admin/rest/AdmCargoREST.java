/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.rest;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.hfsframework.admin.business.AdmCargoBC;
import br.com.hfsframework.admin.model.AdmCargo;
import br.com.hfsframework.util.exceptions.TransacaoException;

/**
 * The Class AdmCargoREST.
 */
@Path("admcargo")
public class AdmCargoREST implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The bc. */
	@Inject
	private AdmCargoBC bc;

	/**
	 * Load.
	 *
	 * @param id
	 *            the id
	 * @return the adm cargo
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public AdmCargo load(@PathParam("id") Long id) {
		return bc.load(id);
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<AdmCargo> findAll() {
		return bc.findAll();
	}

	/**
	 * Insert.
	 *
	 * @param bean
	 *            the bean
	 * @return the adm cargo
	 * @throws TransacaoException
	 *             the transacao exception
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public AdmCargo insert(AdmCargo bean) throws TransacaoException {
		return bc.insert(bean);
	}

	/**
	 * Update.
	 *
	 * @param id
	 *            the id
	 * @param bean
	 *            the bean
	 * @return the adm cargo
	 * @throws TransacaoException
	 *             the transacao exception
	 */
	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public AdmCargo update(@PathParam("id") Long id, AdmCargo bean) throws TransacaoException {
		return bc.update(bean);
	}

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 * @throws TransacaoException
	 *             the transacao exception
	 */
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id) throws TransacaoException {
		bc.delete(bc.load(id));
	}

	/**
	 * Listar por intervalo.
	 *
	 * @param intervaloInicial
	 *            the intervalo inicial
	 * @param intervaloFinal
	 *            the intervalo final
	 * @return the list
	 */
	@GET
	@Path("{inicial}/{final}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<AdmCargo> listarPorIntervalo(@PathParam("inicial") Integer intervaloInicial,
			@PathParam("final") Integer intervaloFinal) {
		return bc.listarPorIntervalo(intervaloInicial, intervaloFinal);
	}

	/**
	 * Listar paginada.
	 *
	 * @param numeroPagina
	 *            the numero pagina
	 * @param tamanhoPagina
	 *            the tamanho pagina
	 * @return the list
	 */
	@GET
	@Path("{numpagina}/{tampagina}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<AdmCargo> listarPaginada(@PathParam("numpagina") Integer numeroPagina, 
			@PathParam("tampagina") Integer tamanhoPagina) {
		return bc.listarPaginada(numeroPagina, tamanhoPagina);
	}
}
