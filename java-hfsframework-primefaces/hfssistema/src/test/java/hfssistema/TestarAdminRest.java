package hfssistema;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.hfsframework.admin.model.AdmUsuario;

public class TestarAdminRest {

	private static final String URL = "http://10.1.107.123:8080/hfssistema/admin";
	
	public static AdmUsuario admUsuarioFind() {
		Client cliente = ClientBuilder.newClient();
	    Response serviceResponse = cliente
	          .target(URL)
	          .path("/admusuario/93203")
	          .request(MediaType.APPLICATION_JSON)
	          .get(Response.class);
	    
		AdmUsuario item = serviceResponse.readEntity(new GenericType<AdmUsuario>() {
		});
		
		return item;
	}
	
	public static void main(String[] args) {
		System.out.println(admUsuarioFind());
	}

}
