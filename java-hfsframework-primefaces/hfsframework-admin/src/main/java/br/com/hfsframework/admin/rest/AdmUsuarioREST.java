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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;

import br.com.hfsframework.admin.business.AdmUsuarioBC;
import br.com.hfsframework.admin.model.AdmUsuario;
import br.com.hfsframework.admin.model.AdmUsuarioPK;
import br.com.hfsframework.util.exceptions.TransacaoException;

/**
 * The Class AdmUsuarioREST.
 */
@Path("admusuario")
public class AdmUsuarioREST implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The bc. */
	@Inject
	private AdmUsuarioBC bc;

	/**
	 * Gets the primary key.
	 *
	 * @param pathSegment
	 *            the path segment
	 * @return the primary key
	 */
	private AdmUsuarioPK getPrimaryKey(PathSegment pathSegment) {
		AdmUsuarioPK key = new AdmUsuarioPK();
		MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
		List<String> ip = map.get("ip");
		if (ip != null && !ip.isEmpty()) {
			key.setIp(ip.get(0));
		}
		List<String> matricula = map.get("matricula");
		if (matricula != null && !matricula.isEmpty()) {
			key.setMatricula(new Long(matricula.get(0)));
		}
		return key;
	}

	/**
	 * Load.
	 *
	 * @param id
	 *            the id
	 * @return the adm cargo funcionario
	 */
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public AdmUsuario load(@PathParam("id") PathSegment id) {
		AdmUsuarioPK pk = getPrimaryKey(id);
		return bc.load(pk);
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<AdmUsuario> findAll() {
		return bc.findAll();
	}

	/**
	 * Insert.
	 *
	 * @param bean
	 *            the bean
	 * @return the adm cargo funcionario
	 * @throws TransacaoException
	 *             the transacao exception
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public AdmUsuario insert(AdmUsuario bean) throws TransacaoException {
		return bc.insert(bean);
	}

	/**
	 * Update.
	 *
	 * @param id
	 *            the id
	 * @param bean
	 *            the bean
	 * @return the adm cargo funcionario
	 * @throws TransacaoException
	 *             the transacao exception
	 */
	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public AdmUsuario update(@PathParam("id") PathSegment id, AdmUsuario bean)
			throws TransacaoException {
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
	public void delete(@PathParam("id") PathSegment id) throws TransacaoException {
		AdmUsuarioPK pk = getPrimaryKey(id);
		bc.delete(bc.load(pk));
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
	public List<AdmUsuario> listarPorIntervalo(@PathParam("inicial") Integer intervaloInicial,
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
	public List<AdmUsuario> listarPaginada(@PathParam("numpagina") Integer numeroPagina,
			@PathParam("tampagina") Integer tamanhoPagina) {
		return bc.listarPaginada(numeroPagina, tamanhoPagina);
	}
}
