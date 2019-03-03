package br.com.hfsframework;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import br.com.hfsframework.admin.rest.AdmCargoFuncionarioREST;
import br.com.hfsframework.admin.rest.AdmCargoPerfilREST;
import br.com.hfsframework.admin.rest.AdmCargoREST;
import br.com.hfsframework.admin.rest.AdmFuncionalidadeREST;
import br.com.hfsframework.admin.rest.AdmFuncionarioPerfilREST;
import br.com.hfsframework.admin.rest.AdmFuncionarioREST;
import br.com.hfsframework.admin.rest.AdmLogColunaREST;
import br.com.hfsframework.admin.rest.AdmMenuREST;
import br.com.hfsframework.admin.rest.AdmPaginaREST;
import br.com.hfsframework.admin.rest.AdmParametroCategoriaREST;
import br.com.hfsframework.admin.rest.AdmParametroREST;
import br.com.hfsframework.admin.rest.AdmPerfilREST;
import br.com.hfsframework.admin.rest.AdmSetorREST;
import br.com.hfsframework.admin.rest.AdmUsuarioREST;
import br.com.hfsframework.admin.rest.VwAdmLogREST;
import br.com.hfsframework.admin.rest.VwAdmLogValorREST;

//@ApplicationPath("admin")
public class AplicacaoAdminREST extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(AdmCargoFuncionarioREST.class);
        resources.add(AdmCargoPerfilREST.class);
        resources.add(AdmCargoREST.class);
        resources.add(AdmFuncionalidadeREST.class);
        resources.add(AdmFuncionarioPerfilREST.class);
        resources.add(AdmFuncionarioREST.class);
        resources.add(AdmLogColunaREST.class);
        resources.add(AdmMenuREST.class);
        resources.add(AdmPaginaREST.class);
        resources.add(AdmParametroCategoriaREST.class);
        resources.add(AdmParametroREST.class);
        resources.add(AdmPerfilREST.class);
        resources.add(AdmSetorREST.class);
        resources.add(AdmUsuarioREST.class);
        resources.add(VwAdmLogREST.class);
        resources.add(VwAdmLogValorREST.class);
    }
	
}
