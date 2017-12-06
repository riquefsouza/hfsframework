/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.security;

import org.apache.deltaspike.core.api.config.view.DefaultErrorView;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;
import org.apache.deltaspike.security.api.authorization.Secured;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pages.
 */
@Folder(name = "/")
public interface Pages {

	/**
	 * The Class Login.
	 */
	class Login extends DefaultErrorView {
	}

	/**
	 * The Interface Secure.
	 */
	@Folder(name = "/private")
	@Secured(LoggedInAccessDecisionVoter.class)
	interface Secure {

		/**
		 * The Class Menu.
		 */
		class Menu implements ViewConfig {
		}

		/**
		 * The Class Desktop.
		 */
		class Desktop implements ViewConfig {
		}

		/**
		 * The Class AlterarSenha.
		 */
		class AlterarSenha implements ViewConfig {
		}
		
		/**
		 * The Class ListarAdmFuncionalidade.
		 */
		@View(basePath = "/private/admin/admFuncionalidade/")
		class ListarAdmFuncionalidade implements ViewConfig {
		}

		/**
		 * The Class EditarAdmFuncionalidade.
		 */
		@View(basePath = "/private/admin/admFuncionalidade/")
		class EditarAdmFuncionalidade implements ViewConfig {
		}

		/**
		 * The Class ListarAdmMenu.
		 */
		@View(basePath = "/private/admin/admMenu/")
		class ListarAdmMenu implements ViewConfig {
		}

		/**
		 * The Class ListarAdmPagina.
		 */
		@View(basePath = "/private/admin/admPagina/")
		class ListarAdmPagina implements ViewConfig {
		}

		/**
		 * The Class EditarAdmPagina.
		 */
		@View(basePath = "/private/admin/admPagina/")
		class EditarAdmPagina implements ViewConfig {
		}

		/**
		 * The Class ListarAdmParametro.
		 */
		@View(basePath = "/private/admin/admParametro/")
		class ListarAdmParametro implements ViewConfig {
		}

		/**
		 * The Class EditarAdmParametro.
		 */
		@View(basePath = "/private/admin/admParametro/")
		class EditarAdmParametro implements ViewConfig {
		}

		/**
		 * The Class ListarAdmParametroCategoria.
		 */
		@View(basePath = "/private/admin/admParametroCategoria/")
		class ListarAdmParametroCategoria implements ViewConfig {
		}

		/**
		 * The Class EditarAdmParametroCategoria.
		 */
		@View(basePath = "/private/admin/admParametroCategoria/")
		class EditarAdmParametroCategoria implements ViewConfig {
		}

		/**
		 * The Class ListarAdmPerfil.
		 */
		@View(basePath = "/private/admin/admPerfil/")
		class ListarAdmPerfil implements ViewConfig {
		}

		/**
		 * The Class EditarAdmPerfil.
		 */
		@View(basePath = "/private/admin/admPerfil/")
		class EditarAdmPerfil implements ViewConfig {
		}

		/**
		 * The Class ListarAdmCargo.
		 */
		@View(basePath = "/private/admin/admCargo/")
		class ListarAdmCargo implements ViewConfig {
		}

		/**
		 * The Class EditarAdmCargo.
		 */
		@View(basePath = "/private/admin/admCargo/")
		class EditarAdmCargo implements ViewConfig {
		}
		
		/**
		 * The Class ListarAdmFuncionario.
		 */
		@View(basePath = "/private/admin/admFuncionario/")
		class ListarAdmFuncionario implements ViewConfig {
		}

		/**
		 * The Class EditarAdmFuncionario.
		 */
		@View(basePath = "/private/admin/admFuncionario/")
		class EditarAdmFuncionario implements ViewConfig {
		}
		
		/**
		 * The Class ListarAdmSetor.
		 */
		@View(basePath = "/private/admin/admSetor/")
		class ListarAdmSetor implements ViewConfig {
		}

		/**
		 * The Class EditarAdmSetor.
		 */
		@View(basePath = "/private/admin/admSetor/")
		class EditarAdmSetor implements ViewConfig {
		}
		
		/**
		 * The Class ListarVwAdmLog.
		 */
		@View(basePath = "/private/admin/vwAdmLog/")
		class ListarVwAdmLog implements ViewConfig {
		}
		
		/**
		 * The Class ListarAdmLogColuna.
		 */
		@View(basePath = "/private/admin/admLogColuna/")
		class ListarAdmLogColuna implements ViewConfig {
		}

		/**
		 * The Class EditarAdmLogColuna.
		 */
		@View(basePath = "/private/admin/admLogColuna/")
		class EditarAdmLogColuna implements ViewConfig {
		}
		
		/**
		 * The Class ListarAdmUsuario.
		 */
		@View(basePath = "/private/admin/admUsuario/")
		class ListarAdmUsuario implements ViewConfig {
		}
	
	}

}
