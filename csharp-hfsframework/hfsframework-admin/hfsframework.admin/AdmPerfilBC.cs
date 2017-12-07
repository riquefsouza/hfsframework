using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.admin.data;

namespace hfsframework.admin
{
    public class AdmPerfilBC: BaseBusinessController<AdmPerfil, long, AdmPerfilRepository>
    {
        private static AdmPerfilBC instancia = new AdmPerfilBC();

        public static AdmPerfilBC Instancia
        {
            get
            {
                return instancia;
            }
        }

        private AdmPerfilBC():base(new AdmPerfilRepository())
        {
        }
    }
}
