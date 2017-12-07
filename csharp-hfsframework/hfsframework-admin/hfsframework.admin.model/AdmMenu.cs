using System;
using System.Collections.Generic;
using hfsframework.security.model;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmMenu : IComparable<AdmMenu>
    {
	    public virtual long Id { get; set; }
	    public virtual string Descricao { get; set; }
        public virtual int Ordem { get; set; }
        public virtual long IdFuncionalidade { get; set; }
        public virtual AdmFuncionalidade AdmFuncionalidade { get; set; }
        public virtual AdmMenu AdmMenuPai { get; set; }
        public virtual List<AdmMenu> AdmSubMenus { get; set; }

        public AdmMenu()
        {
            AdmSubMenus = new List<AdmMenu>();
            limpar();
        }

        public virtual void limpar()
        {
            this.Id = -1;
            this.Descricao = null;
            this.Ordem = -1;
            this.IdFuncionalidade = -1;
            this.AdmFuncionalidade = new AdmFuncionalidade();
            this.AdmMenuPai = null;
            this.AdmSubMenus.Clear();
        }

        public virtual List<AdmMenu> GetAdmSubMenus()
        {
            if (this.AdmSubMenus != null && this.AdmSubMenus.Count > 0)
            {
                AdmSubMenus.Sort();
            }
            return this.AdmSubMenus;
        }

        public virtual int CompareTo(AdmMenu other)
        {
            return this.Ordem.CompareTo(other.Ordem);
            //return this.Descricao.CompareTo(other.Descricao);
        }

        public virtual AdmMenu AddAdmSubMenus(AdmMenu admSubMenus)
        {
            GetAdmSubMenus().Add(admSubMenus);
            admSubMenus.AdmMenuPai = this;

            return admSubMenus;
        }

        public virtual AdmMenu removeAdmSubMenus(AdmMenu admSubMenus)
        {
            GetAdmSubMenus().Remove(admSubMenus);
            admSubMenus.AdmMenuPai = null;

            return admSubMenus;
        }

        public virtual string GetManagedBean()
        {
            return this.AdmFuncionalidade != null ? this.AdmFuncionalidade.AdmPaginaInicial.ManagedBean : null;
        }

        public virtual string GetUrl()
        {
            return this.AdmFuncionalidade != null ? this.AdmFuncionalidade.AdmPaginaInicial.Url : null;
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmMenu other = (AdmMenu)obj;
            if (other == null)
                return false;
            if (AdmMenuPai == null)
            {
                if (other.AdmMenuPai != null)
                    return false;
            }
            else if (!AdmMenuPai.Equals(other.AdmMenuPai))
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
            result = prime * result + ((AdmMenuPai == null) ? 0 : AdmMenuPai.GetHashCode());
            result = prime * result + ((Descricao == null) ? 0 : Descricao.GetHashCode());
            result = prime * result + Id.GetHashCode();
            return result;
        }

        public override string ToString()
        {
            return Descricao;
        }

        public virtual MenuVO ToMenuVO()
        {
            MenuVO m = new MenuVO();

            m.Id = Id;
            m.Descricao = Descricao;
            m.Ordem = Ordem;
            m.IdFuncionalidade = IdFuncionalidade;
            if (AdmFuncionalidade != null)
            {
                m.Funcionalidade = AdmFuncionalidade.ToFuncionalidadeVO();
            }

            foreach (AdmMenu admSubMenu in AdmSubMenus)
            {
                m.GetSubMenus().Add(admSubMenu.ToMenuVO());
            }

            return m;
        }

    }
}
