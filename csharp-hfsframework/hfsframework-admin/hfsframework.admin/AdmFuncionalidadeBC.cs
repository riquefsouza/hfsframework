using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.admin.data;

namespace hfsframework.admin
{
    public class AdmFuncionalidadeBC: BaseBusinessController<AdmFuncionalidade, long, AdmFuncionalidadeRepository>
    {
        private static AdmFuncionalidadeBC instancia = new AdmFuncionalidadeBC();

        public static AdmFuncionalidadeBC Instancia
        {
            get
            {
                return instancia;
            }
        }

        private AdmFuncionalidadeBC():base(new AdmFuncionalidadeRepository())
        {
        }
    }
}
