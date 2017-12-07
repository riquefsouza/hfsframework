using System;
using System.Collections.Generic;

namespace hfsframework.security.model
{
    [Serializable]
    public class PaginaVO
    {
	    public long Id { get; set; }	    
	    public string ManagedBean { get; set; }
        public string Url { get; set; }
        public List<FuncionalidadeVO> Funcionalidades { get; set; }
        public List<PerfilVO> Perfils { get; set; }

        public PaginaVO()
        {
            this.Funcionalidades = new List<FuncionalidadeVO>();
            this.Perfils = new List<PerfilVO>();
            limpar();
        }

        public void limpar()
        {
            this.Id = -1;
            this.ManagedBean = null;
            this.Url = null;
            this.Funcionalidades.Clear();
            this.Perfils.Clear();
        }

        public FuncionalidadeVO AddFuncionalidades(FuncionalidadeVO funcionalidades)
        {
            this.Funcionalidades.Add(funcionalidades);
            funcionalidades.PaginaInicial = this;

            return funcionalidades;
        }

        public FuncionalidadeVO removeFuncionalidades(FuncionalidadeVO funcionalidades)
        {
            this.Funcionalidades.Remove(funcionalidades);
            funcionalidades.PaginaInicial = null;

            return funcionalidades;
        }

        public string GetPerfisPagina()
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
            PaginaVO outro = (PaginaVO)obj;
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

    }
}
