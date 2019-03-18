/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.admin.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.hfsframework.admin.data.AdmPerfilRepository;
import br.com.hfsframework.admin.model.AdmMenu;
import br.com.hfsframework.admin.model.AdmPagina;
import br.com.hfsframework.admin.model.AdmPerfil;
import br.com.hfsframework.admin.model.AdmUsuario;
import br.com.hfsframework.admin.model.AdmUsuarioPerfil;
import br.com.hfsframework.admin.model.AdmUsuarioPerfilPK;
import br.com.hfsframework.base.BaseBusinessController;
import br.com.hfsframework.security.model.MenuVO;
import br.com.hfsframework.security.model.PerfilVO;
import br.com.hfsframework.security.model.PermissaoVO;
import br.com.hfsframework.security.model.UsuarioAutenticadoVO;
import br.com.hfsframework.util.exceptions.TransacaoException;

/**
 * The Class AdmPerfilBC.
 */
public class AdmPerfilBC extends BaseBusinessController<AdmPerfil, Long, AdmPerfilRepository> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The adm menu BC. */
	@Inject
	private AdmMenuBC admMenuBC;
	
	/** The adm usuario BC. */
	@Inject
	private AdmUsuarioBC admUsuarioBC;
	
	/* The adm usuario perfil BC. */
	@Inject
	private AdmUsuarioPerfilBC admUsuarioPerfilBC;

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseBusinessController#load(java.io.Serializable)
	 */
	@Override
	public AdmPerfil load(Long id) {
		AdmPerfil item = repositorio.findBy(id);		
		item.setAdmUsuarios(this.findUsuariosPorPerfil(item));
		return item;
	}
	
	/**
	 * Atribui o adm usuario cargo.
	 *
	 * @param lista
	 *            the lista
	 * @return the list
	 */
	private List<AdmPerfil> setAdmUsuarioCargo(List<AdmPerfil> lista) {
		for (AdmPerfil item : lista) {
			item.setAdmUsuarios(this.findUsuariosPorPerfil(item));
		}
		
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseBusinessController#findAll()
	 */
	@Override
	public List<AdmPerfil> findAll() {
		List<AdmPerfil> lista = repositorio.findAll();		
		return setAdmUsuarioCargo(lista);
	}
	
	/**
	 * Atribui o cargos usuarios.
	 *
	 * @param bean
	 *            the bean
	 */
	private void setPerfisUsuarios(AdmPerfil bean) {
		AdmUsuarioPerfil admUsuarioPerfil; 
		AdmUsuarioPerfilPK admUsuarioPerfilPK;

		for (AdmUsuario usuario : bean.getAdmUsuarios()) {
			
			admUsuarioPerfilPK = new AdmUsuarioPerfilPK();
			admUsuarioPerfilPK.setPerfilSeq(bean.getId());
			admUsuarioPerfilPK.setUsuarioSeq(usuario.getId());
			
			admUsuarioPerfil = new AdmUsuarioPerfil();
			admUsuarioPerfil.setId(admUsuarioPerfilPK);
			admUsuarioPerfil.setAdmPerfil(bean);
			
			admUsuarioPerfilBC.insert(admUsuarioPerfil);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseBusinessController#delete(java.lang.Object)
	 */
	@Override
	@Transactional
	public void delete(AdmPerfil bean) throws TransacaoException {
		super.delete(bean);		
		admUsuarioPerfilBC.deleteByPerfil(bean.getId());
	}


	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseBusinessController#insert(java.lang.Object)
	 */
	@Override
	@Transactional
	public AdmPerfil insert(AdmPerfil bean) throws TransacaoException {
		AdmPerfil novo = super.insert(bean);
		if (novo!=null) {
			setPerfisUsuarios(novo);
		}
		return novo;
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseBusinessController#update(java.lang.Object)
	 */
	@Override
	@Transactional
	public AdmPerfil update(AdmPerfil bean) throws TransacaoException {
		AdmPerfil alterado = super.update(bean);

		admUsuarioPerfilBC.deleteByPerfil(bean.getId());
		
		admUsuarioPerfilBC.deleteByUsuarios(bean.getAdmUsuarios());
		
		setPerfisUsuarios(bean);
		
		return alterado;
	}	
	
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
	 * Gets the papeis.
	 *
	 * @param usuarioAutenticado
	 *            the usuario autenticado
	 * @return the papeis
	 */
	public HashSet<AdmPerfil> getPapeis(UsuarioAutenticadoVO usuarioAutenticado) {
		HashSet<AdmPerfil> hs = new HashSet<AdmPerfil>();

		AdmUsuario admUsuario = new AdmUsuario(usuarioAutenticado.getUsuario());		
		List<AdmPerfil> lst = repositorio.findPerfisPorUsuario(admUsuario.getId());		
		lst = setAdmUsuarioCargo(lst);
		
		for (AdmPerfil g : lst) {
			hs.add(g);
		}
		AdmPerfil g = load(1L); // GERAL
		hs.add(g);

		return hs;
	}

	/**
	 * Gets the permissao.
	 *
	 * @param usuarioAutenticado
	 *            the usuario autenticado
	 * @return the permissao
	 */
	public List<PermissaoVO> getPermissao(UsuarioAutenticadoVO usuarioAutenticado) {
		List<PermissaoVO> lista = new ArrayList<PermissaoVO>();
		PermissaoVO permissao;
		
		AdmUsuario admUsuario = new AdmUsuario(usuarioAutenticado.getUsuario());
		List<AdmPerfil> perfis = repositorio.findPerfisPorUsuario(admUsuario.getId());
		perfis = setAdmUsuarioCargo(perfis);
		
		for (AdmPerfil perfil : perfis) {
			permissao = new PermissaoVO();
			permissao.setPerfil(perfil.toPerfilVO());
			
			for (AdmPagina admPagina : perfil.getAdmPaginas()) {
				permissao.getPaginas().add(admPagina.toPaginaVO());
			}
			
			lista.add(permissao);
		}
		
		return lista;
	}
	
	/**
	 * Find admin menu pai by perfil.
	 *
	 * @param perfil
	 *            the perfil
	 * @return the list
	 */
	public List<AdmMenu> findAdminMenuPaiByPerfil(AdmPerfil perfil){
		List<AdmMenu> lista = repositorio.findAdminMenuPaiByPerfil(perfil);
		for (AdmMenu admMenu : lista) {
			admMenu.setAdmSubMenus(repositorio.findAdminMenuByPerfil(perfil, admMenu));
		}
		return lista;
	}

	/**
	 * Find menu pai by perfil.
	 *
	 * @param perfil
	 *            the perfil
	 * @return the list
	 */
	public List<AdmMenu> findMenuPaiByPerfil(AdmPerfil perfil){
		List<AdmMenu> lista = repositorio.findMenuPaiByPerfil(perfil);
		for (AdmMenu admMenu : lista) {
			admMenu.setAdmSubMenus(repositorio.findMenuByPerfil(perfil, admMenu));
		}
		return lista;
	}
	
	/**
	 * Find admin menu pai by perfil.
	 *
	 * @param perfil
	 *            the perfil
	 * @return the list
	 */
	public List<MenuVO> findAdminMenuPaiByPerfil(PerfilVO perfil){
		AdmPerfil perfilAdminPai = new AdmPerfil(perfil);
		List<AdmMenu> listaAdminMenuPai = this.findAdminMenuPaiByPerfil(perfilAdminPai);						
		return admMenuBC.toListaMenuVO(listaAdminMenuPai);
	}
	
	/**
	 * Find menu pai by perfil.
	 *
	 * @param perfil
	 *            the perfil
	 * @return the list
	 */
	public List<MenuVO> findMenuPaiByPerfil(PerfilVO perfil){
		AdmPerfil perfilPai = new AdmPerfil(perfil);
		List<AdmMenu> listaMenuPai = this.findMenuPaiByPerfil(perfilPai);
		return admMenuBC.toListaMenuVO(listaMenuPai);		
	}
	
	/**
	 * Find usuarios por perfil.
	 *
	 * @param perfil
	 *            the perfil
	 * @return the list
	 */
	public List<AdmUsuario> findUsuariosPorPerfil(AdmPerfil perfil){
		List<AdmUsuario> lista = new ArrayList<AdmUsuario>();
		List<Long> listaCod = repositorio.findUsuariosPorPerfil(perfil);
		
		for (Long item : listaCod) {
			lista.add(admUsuarioBC.load(item));
		}
		
		return lista;
	}
	
	/**
	 * Find menu pai by id perfis.
	 *
	 * @param listaIdPerfil the lista id perfil
	 * @return the list
	 */
	public List<AdmMenu> findMenuPaiByIdPerfis(List<Long> listaIdPerfil){
		List<AdmMenu> lista = repositorio.findMenuPaiByIdPerfis(listaIdPerfil);
		for (AdmMenu admMenu : lista) {
			admMenu.setAdmSubMenus(repositorio.findMenuByIdPerfis(listaIdPerfil, admMenu));
		}
		return lista;
	}
	
	/**
	 * Find menu pai by perfil.
	 *
	 * @param listaIdPerfil the lista id perfil
	 * @return the list
	 */
	public List<MenuVO> findMenuPaiByPerfil(List<Long> listaIdPerfil){
		List<AdmMenu> listaMenuPai = this.findMenuPaiByIdPerfis(listaIdPerfil);
		return admMenuBC.toListaMenuVO(listaMenuPai);		
	}

	/**
	 * Find admin menu pai by id perfis.
	 *
	 * @param listaIdPerfil the lista id perfil
	 * @return the list
	 */
	public List<AdmMenu> findAdminMenuPaiByIdPerfis(List<Long> listaIdPerfil){
		List<AdmMenu> lista = repositorio.findAdminMenuPaiByIdPerfis(listaIdPerfil);
		for (AdmMenu admMenu : lista) {
			admMenu.setAdmSubMenus(repositorio.findAdminMenuByIdPerfis(listaIdPerfil, admMenu));
		}
		return lista;
	}
	
	/**
	 * Find admin menu pai by perfil.
	 *
	 * @param listaIdPerfil the lista id perfil
	 * @return the list
	 */
	public List<MenuVO> findAdminMenuPaiByPerfil(List<Long> listaIdPerfil){
		List<AdmMenu> listaAdminMenuPai = this.findAdminMenuPaiByIdPerfis(listaIdPerfil);						
		return admMenuBC.toListaMenuVO(listaAdminMenuPai);
	}	
}
