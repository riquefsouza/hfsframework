using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.admin.data;

namespace hfsframework.admin
{
    public class AdmSetorBC: BaseBusinessController<AdmSetor, long, AdmSetorRepository>
    {
        private static AdmSetorBC instancia = new AdmSetorBC();

        public static AdmSetorBC Instancia
        {
            get
            {
                return instancia;
            }
        }

        private AdmSetorBC():base(new AdmSetorRepository())
        {
        }
    }
}
