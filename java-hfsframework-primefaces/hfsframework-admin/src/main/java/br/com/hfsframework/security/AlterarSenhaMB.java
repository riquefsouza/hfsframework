/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.security;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import org.primefaces.context.RequestContext;

import br.com.hfsframework.admin.AdmUsuarioBC;
import br.com.hfsframework.base.BaseViewController;
import br.com.hfsframework.security.model.UsuarioAutenticadoVO;
import br.com.hfsframework.util.MensagemBundleProvedor;
import br.com.hfsframework.util.interceptors.TratamentoErrosEsperados;

// TODO: Auto-generated Javadoc
/**
 * The Class AlterarSenhaMB.
 */
@Named
@ViewAccessScoped
@TratamentoErrosEsperados
public class AlterarSenhaMB extends BaseViewController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The adm usuario BC. */
	@Inject
	private AdmUsuarioBC admUsuarioBC;
	
	/** The bundle. */
	private ResourceBundle bundle;
	
	/** The senha atual. */
	private String senhaAtual;
	
	/** The senha nova. */
	private String senhaNova;
	
	/** The confirma senha nova. */
	private String confirmaSenhaNova;
	
	public AlterarSenhaMB(){
		super();
		this.bundle = new MensagemBundleProvedor().getBundle();
	}

	/**
	 * Gets the senha atual.
	 *
	 * @return the senha atual
	 */
	public String getSenhaAtual() {
		return senhaAtual;
	}

	/**
	 * Sets the senha atual.
	 *
	 * @param senhaAtual the new senha atual
	 */
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	/**
	 * Gets the senha nova.
	 *
	 * @return the senha nova
	 */
	public String getSenhaNova() {
		return senhaNova;
	}

	/**
	 * Sets the senha nova.
	 *
	 * @param senhaNova the new senha nova
	 */
	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	/**
	 * Gets the confirma senha nova.
	 *
	 * @return the confirma senha nova
	 */
	public String getConfirmaSenhaNova() {
		return confirmaSenhaNova;
	}

	/**
	 * Sets the confirma senha nova.
	 *
	 * @param confirmaSenhaNova the new confirma senha nova
	 */
	public void setConfirmaSenhaNova(String confirmaSenhaNova) {
		this.confirmaSenhaNova = confirmaSenhaNova;
	}

	/**
	 * Cancelar.
	 *
	 * @return the string
	 */
	public String cancelar() {
		return getPaginaDesktop();
	}
	
	/**
	 * Preparar para salvar.
	 */
	public void prepararParaSalvar() {
		if ((senhaNova == null && confirmaSenhaNova == null && senhaAtual == null)
				|| (senhaNova.equals("") && confirmaSenhaNova.equals("") && senhaAtual.equals(""))) {
			addMessageInfoDialog("Por favor, preencha todos os campos");
		} else if ((senhaNova == null && confirmaSenhaNova == null)
				|| (senhaNova.equals("") && confirmaSenhaNova.equals(""))) {
			addMessageInfoDialog("Por favor, preencha todos os campos");
		} else {
			String senha = DigestUtils.shaHex(senhaAtual);
			
			if (senha.equals(getUsuarioAutenticado().getUsuario().getSenha())) {

				if (senhaNova.equals(confirmaSenhaNova)) {
					RequestContext.getCurrentInstance().execute("PF('dlgAlterarSenha').show()");
				} else {
					addMessageAlertaDialog(bundle.getString("ms.msgSenhaNovaConfirmaSenhaNaoConfere"));
				}
			} else {
				addMessageAlertaDialog(bundle.getString("ms.msgSenhaAtualNaoConfere"));
			}
		}
	}
	
	/**
	 * Alterar senha usuario.
	 */
	public void alterarSenhaUsuario() {
		try {
			String senha = DigestUtils.shaHex(confirmaSenhaNova);
	
			UsuarioAutenticadoVO usuarioAut = getUsuarioAutenticado();
			usuarioAut.getUsuario().setSenha(senha);
	
			admUsuarioBC.updateSenha(usuarioAut.getUsuario().getSenha(), 
					usuarioAut.getUsuario().getLogin());
			
			setUsuarioAutenticado(usuarioAut);
	
			addMessageInfoDialog(bundle.getString("ms.msgSenhaAlterada"));			
		} catch (Exception e) {
			gerarMensagemErro(e, ERRO_SALVAR);
		}		
	}
}