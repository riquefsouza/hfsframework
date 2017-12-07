using System;
using System.Collections.Generic;

namespace hfsframework.security.model
{
    [Serializable]
    public class UsuarioAutenticadoVO
    {
        public string UserName { get; set; }
        public string DisplayName { get; set; }
        public string Email { get; set; }
        public List<PermissaoVO> listaPermissao;
        public List<MenuVO> listaMenus;
        public List<MenuVO> listaAdminMenus;
        public FuncionarioVO Funcionario;
        public UsuarioVO Usuario;

        public UsuarioAutenticadoVO()
        {
            this.listaPermissao = new List<PermissaoVO>();
            this.Funcionario = new FuncionarioVO();
            this.Usuario = new UsuarioVO();
            this.listaMenus = new List<MenuVO>();
            this.listaAdminMenus = new List<MenuVO>();

            limpar();
        }

        public void limpar()
        {
            this.UserName = "";
            this.DisplayName = "";
            this.Email = "";
            this.listaPermissao.Clear();
            this.listaMenus.Clear();
            this.listaAdminMenus.Clear();
            this.Funcionario.limpar();
            this.Usuario.limpar();
        }

        public UsuarioAutenticadoVO(string userName)
        {
            this.UserName = userName;
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            UsuarioAutenticadoVO outro = (UsuarioAutenticadoVO)obj;
            if (outro == null)
                return false;
            if (UserName.Equals(outro.UserName))
                return true;
            return false;
        }

        public override int GetHashCode()
        {
            return UserName.GetHashCode();
        }

        public PerfilVO GetPerfil(long idPerfil)
        {
            PerfilVO admPerfil = null;
            foreach (PermissaoVO permissaoVO in listaPermissao)
            {
                if (permissaoVO.Perfil.Id == idPerfil)
                {
                    admPerfil = permissaoVO.Perfil;
                    break;
                }
            }
            return admPerfil;
        }

        public bool IsPerfil(long idPerfil)
        {
            return (GetPerfil(idPerfil) != null);
        }

        public PerfilVO GetPerfil(string perfil)
        {
            PerfilVO admPerfil = null;
            foreach (PermissaoVO permissaoVO in listaPermissao)
            {
                if (permissaoVO.Perfil.Descricao.Equals(perfil, 
                    StringComparison.CurrentCultureIgnoreCase))
                {
                    admPerfil = permissaoVO.Perfil;
                    break;
                }
            }
            return admPerfil;
        }

        public bool IsPerfil(string perfil)
        {
            return (GetPerfil(perfil) != null);
        }

        public PerfilVO GetPerfilGeral()
        {
            PerfilVO admPerfil = null;
            foreach (PermissaoVO permissaoVO in listaPermissao)
            {
                if (permissaoVO.Perfil.Geral)
                {
                    admPerfil = permissaoVO.Perfil;
                    break;
                }
            }
            return admPerfil;
        }

        public PerfilVO GetPerfilAdministrador()
        {
            PerfilVO admPerfil = null;
            foreach (PermissaoVO permissaoVO in listaPermissao)
            {
                if (permissaoVO.Perfil.Administrador)
                {
                    admPerfil = permissaoVO.Perfil;
                    break;
                }
            }
            return admPerfil;
        }

        public bool IsGeral()
        {
            PerfilVO perfil = this.GetPerfilGeral();
            if (perfil != null)
            {
                return perfil.Geral;
            }
            return false;
        }

        public bool IsAdministrador()
        {
            PerfilVO perfil = this.GetPerfilAdministrador();
            if (perfil != null)
            {
                return perfil.Administrador;
            }
            return false;
        }

        public PaginaVO getPaginaByMenu(long idMenu)
        {
            PaginaVO admPagina = null;

            if (listaMenus != null && listaMenus.Count > 0)
            {
                foreach (MenuVO admMenu in listaMenus)
                {
                    admPagina = admMenu.Funcionalidade.PaginaInicial;
                    break;
                }
            }

            if (listaAdminMenus != null && listaAdminMenus.Count > 0)
            {
                foreach (MenuVO admMenu in listaAdminMenus)
                {
                    admPagina = admMenu.Funcionalidade.PaginaInicial;
                    break;
                }
            }

            return admPagina;
        }

        public bool isPossuiPermissao(string url, string tela)
        {
            if (AplicacaoUtil.Instancia.isDebugMode())
            {
                return true;
            }
            if (url == null)
            {
                return false;
            }
            int pos = url.IndexOf("private");
            url = pos > -1 ? url.Substring(pos + 8, url.Length) : url;

            if (url.Equals(tela))
            {
                return true;
            }

            foreach (PermissaoVO permissao in listaPermissao)
            {
                foreach (PaginaVO pagFuncionalidade in permissao.PaginasFuncionalidade)
                {
                    if (pagFuncionalidade.Url.Equals(url))
                    {
                        return true;
                    }
                }
                foreach (PaginaVO admPagina in permissao.Paginas)
                {
                    if (admPagina.Url.Equals(url))
                    {
                        return true;
                    }
                }
            }

            return false;
        }

        public override string ToString()
        {
            return "UsuarioAutenticadoVO [userName=" + UserName + ", displayName=" + DisplayName + ", email=" + Email
                    + ", listaPermissao=" + listaPermissao + ", listaMenus=" + listaMenus + ", listaAdminMenus="
                    + listaAdminMenus + ", funcionario=" + Funcionario + ", usuario=" + Usuario + "]";
        }

    }
}
