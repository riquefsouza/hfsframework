using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.admin.data;

namespace hfsframework.admin
{
    public class AdmPaginaBC: BaseBusinessController<AdmPagina, long, AdmPaginaRepository>
    {
        private static AdmPaginaBC instancia = new AdmPaginaBC();

        public static AdmPaginaBC Instancia
        {
            get
            {
                return instancia;
            }
        }

        private AdmPaginaBC():base(new AdmPaginaRepository())
        {
        }
    }
}
