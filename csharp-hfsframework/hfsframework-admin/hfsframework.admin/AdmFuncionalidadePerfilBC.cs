using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.admin.data;

namespace hfsframework.admin
{
    public class AdmFuncionalidadePerfilBC: BaseBusinessController<AdmFuncionalidadePerfil, long, AdmFuncionalidadePerfilRepository>
    {
        private static AdmFuncionalidadePerfilBC instancia = new AdmFuncionalidadePerfilBC();

        public static AdmFuncionalidadePerfilBC Instancia
        {
            get
            {
                return instancia;
            }
        }

        private AdmFuncionalidadePerfilBC():base(new AdmFuncionalidadePerfilRepository())
        {
        }
    }
}
