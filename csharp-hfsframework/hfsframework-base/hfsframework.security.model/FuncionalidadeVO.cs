using System;
using System.Collections.Generic;

namespace hfsframework.security.model
{
    [Serializable]
    public class FuncionalidadeVO
    {
	    public long Id { get; set; }
        public string Descricao { get; set; }
        public PaginaVO PaginaInicial { get; set; }
        public List<PaginaVO> Paginas { get; set; }
        public List<PerfilVO> Perfils { get; set; }
        public List<MenuVO> Menus { get; set; }

        public FuncionalidadeVO()
        {
            this.Paginas = new List<PaginaVO>();
            this.Perfils = new List<PerfilVO>();
            this.Menus = new List<MenuVO>();
            limpar();
        }

        public void limpar()
        {
            this.Id = -1;
            this.Descricao = null;
            this.PaginaInicial = new PaginaVO();
            this.Paginas.Clear();
            this.Perfils.Clear();
            this.Menus.Clear();
        }

        public MenuVO AddMenus(MenuVO menus)
        {
            this.Menus.Add(menus);
            menus.Funcionalidade = this;

            return menus;
        }

        public MenuVO RemoveMenus(MenuVO menus)
        {
            this.Menus.Remove(menus);
            menus.Funcionalidade = null;

            return menus;
        }

        public string getPerfisFuncionalidade()
        {
            string ret = "";
            foreach (PerfilVO item in Perfils)
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
            FuncionalidadeVO outro = (FuncionalidadeVO)obj;
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

    }
}
