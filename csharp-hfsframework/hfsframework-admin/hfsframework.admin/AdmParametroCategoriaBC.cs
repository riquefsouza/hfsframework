using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.admin.data;

namespace hfsframework.admin
{
    public class AdmParametroCategoriaBC: BaseBusinessController<AdmParametroCategoria, long, AdmParametroCategoriaRepository>
    {
        private static AdmParametroCategoriaBC instancia = new AdmParametroCategoriaBC();

        public static AdmParametroCategoriaBC Instancia
        {
            get
            {
                return instancia;
            }
        }

        private AdmParametroCategoriaBC():base(new AdmParametroCategoriaRepository())
        {
        }
    }
}
