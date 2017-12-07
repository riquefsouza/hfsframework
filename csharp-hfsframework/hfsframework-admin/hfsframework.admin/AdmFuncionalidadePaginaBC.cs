using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.admin.data;

namespace hfsframework.admin
{
    public class AdmFuncionalidadePaginaBC: BaseBusinessController<AdmFuncionalidadePagina, long, AdmFuncionalidadePaginaRepository>
    {
        private static AdmFuncionalidadePaginaBC instancia = new AdmFuncionalidadePaginaBC();

        public static AdmFuncionalidadePaginaBC Instancia
        {
            get
            {
                return instancia;
            }
        }

        private AdmFuncionalidadePaginaBC():base(new AdmFuncionalidadePaginaRepository())
        {
        }
    }
}
