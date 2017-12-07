using System;
using System.Collections.Generic;
using hfsframework.security.model;

namespace hfsframework.admin.model
{
    [Serializable]
    public class AdmPerfil
    {
	    public virtual long Id { get; set; }
	    public virtual string Descricao { get; set; }
	    public virtual string Geral { get; set; }
	    public virtual string Administrador { get; set; }
        public virtual List<AdmFuncionalidade> AdmFuncionalidades { get; set; }
        public virtual List<AdmPagina> AdmPaginas { get; set; }
        public virtual List<AdmCargo> AdmCargos { get; set; }
        public virtual List<AdmFuncionario> AdmFuncionarios { get; set; }

        public AdmPerfil()
        {
            this.AdmFuncionalidades = new List<AdmFuncionalidade>();
            this.AdmPaginas = new List<AdmPagina>();
            this.AdmFuncionarios = new List<AdmFuncionario>();
            this.AdmCargos = new List<AdmCargo>();
            limpar();
        }

        public AdmPerfil(PerfilVO p): base()
        {
            this.Id = p.Id;
            this.Administrador = p.Administrador ? "S" : "N";
            this.Descricao = p.Descricao;
            this.Geral = p.Geral ? "S" : "N";

            foreach (FuncionalidadeVO funcionalidade in p.Funcionalidades)
            {
                this.AdmFuncionalidades.Add(new AdmFuncionalidade(funcionalidade));
            }
            foreach (PaginaVO pagina in p.Paginas)
            {
                this.AdmPaginas.Add(new AdmPagina(pagina));
            }
            foreach (FuncionarioVO funcionario in p.Funcionarios)
            {
                this.AdmFuncionarios.Add(new AdmFuncionario(funcionario));
            }
            foreach (CargoVO cargo in p.Cargos)
            {
                this.AdmCargos.Add(new AdmCargo(cargo));
            }
        }

        public virtual void limpar()
        {
            this.Id = -1;
            this.Administrador = "N";
            this.Descricao = null;
            this.Geral = "N";
            this.AdmFuncionalidades.Clear();
            this.AdmPaginas.Clear();
            this.AdmFuncionarios.Clear();
            this.AdmCargos.Clear();
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            AdmPerfil outro = (AdmPerfil)obj;
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

        public virtual PerfilVO ToPerfilVO()
        {
            PerfilVO p = new PerfilVO();

            p.Id = Id;
            p.Administrador = Administrador.Equals("S");
            p.Descricao = Descricao;
            p.Geral = Geral.Equals("S");

            foreach (AdmFuncionalidade admFuncionalidade in AdmFuncionalidades)
            {
                p.Funcionalidades.Add(admFuncionalidade.ToFuncionalidadeVO());
            }

            foreach (AdmPagina admPagina in AdmPaginas)
            {
                p.Paginas.Add(admPagina.ToPaginaVO());
            }

            foreach (AdmFuncionario admFuncionario in AdmFuncionarios)
            {
                p.Funcionarios.Add(admFuncionario.ToFuncionarioVO());
            }

            foreach (AdmCargo admCargo in AdmCargos)
            {
                p.Cargos.Add(admCargo.ToCargoVO());
            }

            return p;
        }

    }
}
