using System;
using System.Collections.Generic;
using hfsframework.security.model;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmFuncionalidade
    {
	    public virtual long Id { get; set; }
        public virtual string Descricao { get; set; }
        public virtual AdmPagina AdmPaginaInicial { get; set; }
        public virtual List<AdmPagina> AdmPaginas { get; set; }
        public virtual List<AdmPerfil> AdmPerfils { get; set; }
        public virtual List<AdmMenu> AdmMenus { get; set; }

        public AdmFuncionalidade()
        {
            AdmPaginas = new List<AdmPagina>();
            AdmPerfils = new List<AdmPerfil>();
            AdmMenus = new List<AdmMenu>();
            limpar();
        }

        public AdmFuncionalidade(FuncionalidadeVO f): base()
        {
            this.Id = f.Id;
            this.Descricao = f.Descricao;
            this.AdmPaginaInicial = new AdmPagina(f.PaginaInicial);
        }

        public virtual void limpar()
        {
            this.Id = -1;
            this.Descricao = null;
            this.AdmPaginaInicial = new AdmPagina();
            this.AdmPaginas.Clear();
            this.AdmPerfils.Clear();
            this.AdmMenus.Clear();
        }

        public virtual AdmMenu AddMenus(AdmMenu menus)
        {
            this.AdmMenus.Add(menus);
            menus.AdmFuncionalidade = this;

            return menus;
        }

        public virtual AdmMenu RemoveMenus(AdmMenu menus)
        {
            this.AdmMenus.Remove(menus);
            menus.AdmFuncionalidade = null;

            return menus;
        }

        public virtual string GetPerfisFuncionalidade()
        {
            string ret = "";
            foreach (AdmPerfil item in AdmPerfils)
            {
                ret += ret + item.Descricao + ", ";
            }
            if (ret != "")
            {
                ret = ret.Substring(0, ret.Length - 2);
            }
            return ret;
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmFuncionalidade outro = (AdmFuncionalidade)obj;
            if (outro == null)
                return false;
            if (Id.Equals(outro.Id))
                return true;
            return false;
        }

        public override int GetHashCode()
        {
            return Id.GetHashCode();
        }

        public override string ToString()
        {
            return Descricao;
        }

        public virtual FuncionalidadeVO ToFuncionalidadeVO()
        {
            FuncionalidadeVO f = new FuncionalidadeVO();

            f.Id = Id;
            f.Descricao = Descricao;
            if (AdmPaginaInicial != null)
            {
                f.PaginaInicial = AdmPaginaInicial.ToPaginaVO();
            }

            return f;
        }

    }
}
