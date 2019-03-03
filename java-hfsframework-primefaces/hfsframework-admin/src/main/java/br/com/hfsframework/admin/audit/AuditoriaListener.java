/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.audit;

import org.hibernate.envers.RevisionListener;

import br.com.hfsframework.AplicacaoUtil;
import br.com.hfsframework.security.model.UsuarioAutenticadoVO;

public class AuditoriaListener implements RevisionListener {

	/* (non-Javadoc)
	 * @see org.hibernate.envers.RevisionListener#newRevision(java.lang.Object)
	 */
	@Override
	public void newRevision(Object revisionEntity) {
		AplicacaoUtil aplicacaoUtil = new AplicacaoUtil();
		
		if (!aplicacaoUtil.isDebugMode()) {			
			UsuarioAutenticadoVO usu = aplicacaoUtil.getUsuarioAutenticado();	
		
			AdmAuditoriaRevisao revEntity = (AdmAuditoriaRevisao) revisionEntity;
			
			revEntity.setLogin(usu.getUsuario().getLogin());
			revEntity.setIp(usu.getUsuario().getIp());
		}
	}
}
