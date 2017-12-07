using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.admin.data;

namespace hfsframework.admin
{
    public class AdmFuncionarioPerfilBC: BaseBusinessController<AdmFuncionarioPerfil, long, AdmFuncionarioPerfilRepository>
    {
        private static AdmFuncionarioPerfilBC instancia = new AdmFuncionarioPerfilBC();

        public static AdmFuncionarioPerfilBC Instancia
        {
            get
            {
                return instancia;
            }
        }

        private AdmFuncionarioPerfilBC():base(new AdmFuncionarioPerfilRepository())
        {
        }
    }
}
