using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.admin.data;

namespace hfsframework.admin
{
    public class AdmFuncionarioBC: BaseBusinessController<AdmFuncionario, long, AdmFuncionarioRepository>
    {
        private static AdmFuncionarioBC instancia = new AdmFuncionarioBC();

        public static AdmFuncionarioBC Instancia
        {
            get
            {
                return instancia;
            }
        }

        private AdmFuncionarioBC():base(new AdmFuncionarioRepository())
        {
        }
    }
}
