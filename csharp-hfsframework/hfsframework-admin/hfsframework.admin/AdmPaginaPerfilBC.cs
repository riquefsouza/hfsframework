using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.admin.data;

namespace hfsframework.admin
{
    public class AdmPaginaPerfilBC: BaseBusinessController<AdmPaginaPerfil, long, AdmPaginaPerfilRepository>
    {
        private static AdmPaginaPerfilBC instancia = new AdmPaginaPerfilBC();

        public static AdmPaginaPerfilBC Instancia
        {
            get
            {
                return instancia;
            }
        }

        private AdmPaginaPerfilBC():base(new AdmPaginaPerfilRepository())
        {
        }
    }
}
