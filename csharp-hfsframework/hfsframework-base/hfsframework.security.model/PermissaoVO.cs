using System;
using System.Collections.Generic;

namespace hfsframework.security.model
{
    [Serializable]
    public class PermissaoVO
    {
        public PerfilVO Perfil;
        public List<FuncionalidadeVO> Funcionalidades;
        public List<PaginaVO> PaginasFuncionalidade;
        public List<PaginaVO> Paginas;

        public PermissaoVO()
        {
            this.Funcionalidades = new List<FuncionalidadeVO>();
            this.PaginasFuncionalidade = new List<PaginaVO>();
            this.Paginas = new List<PaginaVO>();

            limpar();
        }

        public void limpar()
        {
            this.Perfil = new PerfilVO();
            this.Funcionalidades.Clear();
            this.PaginasFuncionalidade.Clear();
            this.Paginas.Clear();
        }

        public override string ToString()
        {
            return "PermissaoVO [perfil=" + Perfil + ", funcionalidades=" + Funcionalidades + ", paginasFuncionalidade="
                    + PaginasFuncionalidade + ", paginas=" + Paginas + "]";
        }

    }
}
