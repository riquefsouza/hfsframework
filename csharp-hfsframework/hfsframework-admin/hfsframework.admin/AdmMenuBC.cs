using System.Collections.Generic;
using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.admin.data;
using hfsframework.security.model;

namespace hfsframework.admin
{
    public class AdmMenuBC: BaseBusinessController<AdmMenu, long, AdmMenuRepository>
    {
        private static AdmMenuBC instancia = new AdmMenuBC();

        public static AdmMenuBC Instancia
        {
            get
            {
                return instancia;
            }
        }

        private static List<AdmMenu> cacheMenuEstaticos = new List<AdmMenu>();

        private AdmMenuBC():base(new AdmMenuRepository())
        {
        }

        public AdmMenu salvarOuAtualizarDragReordenando(AdmMenu menuPaiAntigo, AdmMenu menuPaiNovo, AdmMenu menuMover)
        {
            cacheMenuEstaticos.Clear();
            if ((menuPaiAntigo != null) && (!menuPaiAntigo.Equals(menuPaiNovo)))
            {
                menuPaiAntigo = atualizaSubmenu(menuPaiAntigo);
            }
            if (menuPaiNovo != null)
            {
                menuPaiNovo = atualizaSubmenu(menuPaiNovo);
            }
            menuMover = load(menuMover.Id);
            menuMover.AdmMenuPai = menuPaiNovo;
            return update(menuMover);
        }

        private AdmMenu atualizaSubmenu(AdmMenu menuPai)
        {
            if ((menuPai.AdmSubMenus != null) && (menuPai.AdmSubMenus.Count > 0))
            {
                foreach (AdmMenu menu in menuPai.AdmSubMenus)
                {
                    update(menu);
                }
            }
            return menuPai;
        }

        public AdmMenu salvarOuAtualizar(AdmMenu novoItemMenu)
        {
            cacheMenuEstaticos.Clear();
            if ((novoItemMenu.AdmMenuPai != null)) // && (novoItemMenu.AdmMenuPai.Id != null))
            {
                novoItemMenu.AdmMenuPai = load(novoItemMenu.AdmMenuPai.Id);
            }
            return update(novoItemMenu);
        }

        public void apagar(AdmMenu menu)
        {
            cacheMenuEstaticos.Clear();
            IList<AdmMenu> listaMenuFilhos = repositorio.findMenusFilhos(menu);
            if ((listaMenuFilhos != null) && (listaMenuFilhos.Count > 0))
            {
                foreach (AdmMenu menuFilho in listaMenuFilhos)
                {
                    apagar(menuFilho);
                }
            }
            menu = load(menu.Id);
            if (menu != null)
            {
                delete(menu);
            }
        }

        public List<MenuVO> toListaMenuVO(List<AdmMenu> listaMenu)
        {
            List<MenuVO> lista = new List<MenuVO>();
            foreach (AdmMenu menu in listaMenu)
            {
                lista.Add(menu.ToMenuVO());
            }
            return lista;
        }

    }
}
