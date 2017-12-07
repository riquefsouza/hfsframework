using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.admin.data;

namespace hfsframework.admin
{
    public class AdmLogColunaBC: BaseBusinessController<AdmLogColuna, long, AdmLogColunaRepository>
    {
        private static AdmLogColunaBC instancia = new AdmLogColunaBC();

        public static AdmLogColunaBC Instancia
        {
            get
            {
                return instancia;
            }
        }

        private AdmLogColunaBC():base(new AdmLogColunaRepository())
        {
        }
    }
}
