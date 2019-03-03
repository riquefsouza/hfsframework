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
import br.com.hfsframework.admin.model.AdmCargo;
import br.com.hfsframework.admin.model.AdmCargoPerfil;
import br.com.hfsframework.admin.model.AdmCargoPerfilPK;
import br.com.hfsframework.admin.model.AdmFuncionalidade;
import br.com.hfsframework.admin.model.AdmFuncionario;
import br.com.hfsframework.admin.model.AdmFuncionarioPerfil;
import br.com.hfsframework.admin.model.AdmFuncionarioPerfilPK;
import br.com.hfsframework.admin.model.AdmMenu;
import br.com.hfsframework.admin.model.AdmPagina;
import br.com.hfsframework.admin.model.AdmPerfil;
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
	
	/** The adm funcionario BC. */
	@Inject
	private AdmFuncionarioBC admFuncionarioBC;
	
	/** The adm cargo BC. */
	@Inject
	private AdmCargoBC admCargoBC;
	
	/* The adm funcionario perfil BC. */
	@Inject
	private AdmFuncionarioPerfilBC admFuncionarioPerfilBC;

	/** The adm cargo perfil BC. */
	@Inject
	private AdmCargoPerfilBC admCargoPerfilBC;

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseBusinessController#load(java.io.Serializable)
	 */
	@Override
	public AdmPerfil load(Long id) {
		AdmPerfil item = repositorio.findBy(id);		
		item.setAdmFuncionarios(this.findFuncionariosPorPerfil(item));
		item.setAdmCargos(this.findCargosPorPerfil(item));
		return item;
	}
	
	/**
	 * Atribui o adm funcionario cargo.
	 *
	 * @param lista
	 *            the lista
	 * @return the list
	 */
	private List<AdmPerfil> setAdmFuncionarioCargo(List<AdmPerfil> lista) {
		for (AdmPerfil item : lista) {
			item.setAdmFuncionarios(this.findFuncionariosPorPerfil(item));
			item.setAdmCargos(this.findCargosPorPerfil(item));
		}
		
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseBusinessController#findAll()
	 */
	@Override
	public List<AdmPerfil> findAll() {
		List<AdmPerfil> lista = repositorio.findAll();		
		return setAdmFuncionarioCargo(lista);
	}
	
	/**
	 * Atribui o cargos funcionarios.
	 *
	 * @param bean
	 *            the bean
	 */
	private void setCargosFuncionarios(AdmPerfil bean) {
		AdmCargoPerfil admCargoPerfil; 
		AdmCargoPerfilPK admCargoPerfilPK;
		
		for (AdmCargo cargo : bean.getAdmCargos()) {

			admCargoPerfilPK = new AdmCargoPerfilPK();
			admCargoPerfilPK.setPerfilSeq(bean.getId());
			admCargoPerfilPK.setCodCargo(cargo.getId());
			
			admCargoPerfil = new AdmCargoPerfil();
			admCargoPerfil.setId(admCargoPerfilPK);
			admCargoPerfil.setAdmPerfil(bean);
			
			admCargoPerfilBC.insert(admCargoPerfil);
		}

		AdmFuncionarioPerfil admFuncionarioPerfil; 
		AdmFuncionarioPerfilPK admFuncionarioPerfilPK;

		for (AdmFuncionario funcionario : bean.getAdmFuncionarios()) {
			
			admFuncionarioPerfilPK = new AdmFuncionarioPerfilPK();
			admFuncionarioPerfilPK.setPerfilSeq(bean.getId());
			admFuncionarioPerfilPK.setCodFuncionario(funcionario.getId());
			
			admFuncionarioPerfil = new AdmFuncionarioPerfil();
			admFuncionarioPerfil.setId(admFuncionarioPerfilPK);
			admFuncionarioPerfil.setAdmPerfil(bean);
			
			admFuncionarioPerfilBC.insert(admFuncionarioPerfil);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseBusinessController#delete(java.lang.Object)
	 */
	@Override
	@Transactional
	public void delete(AdmPerfil bean) throws TransacaoException {
		super.delete(bean);		
		admCargoPerfilBC.deleteByPerfil(bean.getId());
		admFuncionarioPerfilBC.deleteByPerfil(bean.getId());
	}


	/* (non-Javadoc)
	 * @see br.com.hfsframework.base.BaseBusinessController#insert(java.lang.Object)
	 */
	@Override
	@Transactional
	public AdmPerfil insert(AdmPerfil bean) throws TransacaoException {
		AdmPerfil novo = super.insert(bean);
		if (novo!=null) {
			setCargosFuncionarios(novo);
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

		admCargoPerfilBC.deleteByPerfil(bean.getId());
		admFuncionarioPerfilBC.deleteByPerfil(bean.getId());
		
		//admCargoPerfilBC.deleteByCargos(bean.getAdmCargos());
		admFuncionarioPerfilBC.deleteByFuncionarios(bean.getAdmFuncionarios());
		
		setCargosFuncionarios(bean);
		
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

		AdmFuncionario admFuncionario = new AdmFuncionario(usuarioAutenticado.getFuncionario());		
		List<AdmPerfil> lst = repositorio.findPerfisPorFuncionario(admFuncionario.getId());		
		lst = setAdmFuncionarioCargo(lst);
		
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
		List<AdmPagina> paginasFuncionalidade;
		
		AdmFuncionario admFuncionario = new AdmFuncionario(usuarioAutenticado.getFuncionario());
		List<AdmPerfil> perfis = repositorio.findPerfisPorFuncionario(admFuncionario.getId());
		perfis = setAdmFuncionarioCargo(perfis);
		
		for (AdmPerfil perfil : perfis) {
			permissao = new PermissaoVO();
			permissao.setPerfil(perfil.toPerfilVO());

			for (AdmFuncionalidade admFuncionalidade : perfil.getAdmFuncionalidades()) {
				permissao.getFuncionalidades().add(admFuncionalidade.toFuncionalidadeVO());	
			}
			
			paginasFuncionalidade = new ArrayList<AdmPagina>();
			for (AdmFuncionalidade admFuncionalidade : perfil.getAdmFuncionalidades()) {
				paginasFuncionalidade.add(admFuncionalidade.getAdmPaginaInicial());	
			}
			
			for (AdmPagina admPaginaFunc : paginasFuncionalidade) {
				permissao.getPaginasFuncionalidade().add(admPaginaFunc.toPaginaVO());	
			}
			
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
	 * Find funcionarios por perfil.
	 *
	 * @param perfil
	 *            the perfil
	 * @return the list
	 */
	public List<AdmFuncionario> findFuncionariosPorPerfil(AdmPerfil perfil){
		List<AdmFuncionario> lista = new ArrayList<AdmFuncionario>();
		List<Long> listaCod = repositorio.findFuncionariosPorPerfil(perfil);
		
		for (Long item : listaCod) {
			lista.add(admFuncionarioBC.load(item));
		}
		
		return lista;
	}
	
	/**
	 * Find cargos por perfil.
	 *
	 * @param perfil
	 *            the perfil
	 * @return the list
	 */
	public List<AdmCargo> findCargosPorPerfil(AdmPerfil perfil){
		List<AdmCargo> lista = new ArrayList<AdmCargo>();
		List<Long> listaCod = repositorio.findCargosPorPerfil(perfil);
		
		for (Long item : listaCod) {
			lista.add(admCargoBC.load(item));
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
