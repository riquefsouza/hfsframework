using System;
using System.Collections.Generic;

namespace hfsframework.security.model
{
    [Serializable]
    public class PerfilVO
    {
	    public long Id { get; set; }
	    public string Descricao { get; set; }
	    public bool Geral { get; set; }
	    public bool Administrador { get; set; }
        public List<FuncionalidadeVO> Funcionalidades { get; set; }
        public List<PaginaVO> Paginas { get; set; }
        public List<CargoVO> Cargos { get; set; }
        public List<FuncionarioVO> Funcionarios { get; set; }

        public PerfilVO()
        {
            this.Funcionalidades = new List<FuncionalidadeVO>();
            this.Paginas = new List<PaginaVO>();
            this.Funcionarios = new List<FuncionarioVO>();
            this.Cargos = new List<CargoVO>();
            limpar();
        }

        public void limpar()
        {
            this.Id = -1;
            this.Administrador = false;
            this.Descricao = null;
            this.Geral = false;
            this.Funcionalidades.Clear();
            this.Paginas.Clear();
            this.Funcionarios.Clear();
            this.Cargos.Clear();
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            PerfilVO outro = (PerfilVO)obj;
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
