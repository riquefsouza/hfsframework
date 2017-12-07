using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.admin.data;

namespace hfsframework.admin
{
    public class AdmUsuarioBC: BaseBusinessController<AdmUsuario, long, AdmUsuarioRepository>
    {
        private static AdmUsuarioBC instancia = new AdmUsuarioBC();

        public static AdmUsuarioBC Instancia
        {
            get
            {
                return instancia;
            }
        }

        private AdmUsuarioBC():base(new AdmUsuarioRepository())
        {
        }
    }
}
