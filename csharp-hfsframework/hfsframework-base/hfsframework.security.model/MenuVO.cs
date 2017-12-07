using System;
using System.Collections.Generic;

namespace hfsframework.security.model
{
    [Serializable]
    public class MenuVO : IComparable<MenuVO>
    {
	    public long Id { get; set; }
	    public string Descricao { get; set; }
        public int Ordem { get; set; }
        public long IdFuncionalidade { get; set; }
        public FuncionalidadeVO Funcionalidade { get; set; }
        public MenuVO MenuPai { get; set; }
        public List<MenuVO> SubMenus { get; set; }

        public MenuVO()
        {
            this.SubMenus = new List<MenuVO>();
            limpar();
        }

        public void limpar()
        {
            this.Id = -1;
            this.Descricao = null;
            this.Ordem = -1;
            this.IdFuncionalidade = -1;
            this.Funcionalidade = new FuncionalidadeVO();
            this.MenuPai = null;
            this.SubMenus.Clear();
        }

        public List<MenuVO> GetSubMenus()
        {
            if (this.SubMenus != null && this.SubMenus.Count > 0)
            {
                SubMenus.Sort();
            }
            return this.SubMenus;
        }

        public int CompareTo(MenuVO other)
        {
            return this.Ordem.CompareTo(other.Ordem);
            //return this.Descricao.CompareTo(other.Descricao);
        }

        public MenuVO AddSubMenus(MenuVO subMenus)
        {
            GetSubMenus().Add(subMenus);
            subMenus.MenuPai = this;

            return subMenus;
        }

        public MenuVO removeSubMenus(MenuVO subMenus)
        {
            GetSubMenus().Remove(subMenus);
            subMenus.MenuPai = null;

            return subMenus;
        }

        public string GetManagedBean()
        {
            return this.Funcionalidade != null ? this.Funcionalidade.PaginaInicial.ManagedBean : null;
        }

        public string GetUrl()
        {
            return this.Funcionalidade != null ? this.Funcionalidade.PaginaInicial.Url : null;
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            MenuVO other = (MenuVO)obj;
            if (other == null)
                return false;
            if (MenuPai == null)
            {
                if (other.MenuPai != null)
                    return false;
            }
            else if (!MenuPai.Equals(other.MenuPai))
                return false;
            if (Descricao == null)
            {
                if (other.Descricao != null)
                    return false;
            }
            else if (!Descricao.Equals(other.Descricao))
                return false;
            /*
            if (Id == null)
            {
                if (other.Id != null)
                    return false;
            }
            else */
            if (!Id.Equals(other.Id))
                return false;
            return true;
        }

        public override int GetHashCode()
        {
            const int prime = 31;
            int result = 1;
            result = prime * result + ((MenuPai == null) ? 0 : MenuPai.GetHashCode());
            result = prime * result + ((Descricao == null) ? 0 : Descricao.GetHashCode());
            result = prime * result + Id.GetHashCode();
            return result;
        }

        public override string ToString()
        {
            return Descricao;
        }
    }
}
