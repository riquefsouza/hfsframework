using System;
using System.Collections.Generic;
using hfsframework.security.model;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmPagina
    {
	    public virtual long Id { get; set; }	    
	    public virtual string ManagedBean { get; set; }
        public virtual string Url { get; set; }
        public virtual List<AdmFuncionalidade> AdmFuncionalidades { get; set; }
        public virtual List<AdmPerfil> AdmPerfils { get; set; }

        public AdmPagina()
        {
            this.AdmFuncionalidades = new List<AdmFuncionalidade>();
            this.AdmPerfils = new List<AdmPerfil>();
            limpar();
        }

        public AdmPagina(PaginaVO p): base()
        {
            this.Id = p.Id;
            this.ManagedBean = p.ManagedBean;
            this.Url = p.Url;
        }

        public virtual void limpar()
        {
            this.Id = -1;
            this.ManagedBean = null;
            this.Url = null;
            this.AdmFuncionalidades.Clear();
            this.AdmPerfils.Clear();
        }

        public virtual AdmFuncionalidade addAdmFuncionalidades(AdmFuncionalidade admFuncionalidades)
        {
            AdmFuncionalidades.Add(admFuncionalidades);
            admFuncionalidades.AdmPaginaInicial = this;

            return admFuncionalidades;
        }

        public virtual AdmFuncionalidade removeAdmFuncionalidades(AdmFuncionalidade admFuncionalidades)
        {
            AdmFuncionalidades.Remove(admFuncionalidades);
            admFuncionalidades.AdmPaginaInicial = null;

            return admFuncionalidades;
        }

        public virtual string GetPerfisPagina()
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
            AdmPagina outro = (AdmPagina)obj;
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
            return Url;
        }

        public virtual PaginaVO ToPaginaVO()
        {
            PaginaVO p = new PaginaVO();

            p.Id = Id;
            p.ManagedBean = ManagedBean;
            p.Url = Url;

            return p;
        }

    }
}
