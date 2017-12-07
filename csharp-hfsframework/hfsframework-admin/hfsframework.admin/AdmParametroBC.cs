using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.admin.data;

namespace hfsframework.admin
{
    public class AdmParametroBC: BaseBusinessController<AdmParametro, long, AdmParametroRepository>
    {
        private static AdmParametroBC instancia = new AdmParametroBC();

        public static AdmParametroBC Instancia
        {
            get
            {
                return instancia;
            }
        }

        private AdmParametroBC():base(new AdmParametroRepository())
        {
        }
    }
}
