using hfsframework.security.model;
using log4net;

namespace hfsframework
{
    public sealed class AplicacaoUtil
    {
        private static AplicacaoUtil instancia = new AplicacaoUtil();

        private static ILog log = LogManager.GetLogger(typeof(AplicacaoUtil));

        public static AplicacaoUtil Instancia
        {
            get
            {
                return instancia;
            }
        }

        private AplicacaoUtil() {}

        public bool isDebugMode()
        {
            return false;
        }

        /*
        public HttpSession getSessao()
        {
            return hsr.getSession();
        }
        */

        public void setUsuarioAutenticado(UsuarioAutenticadoVO usu)
        {
            //getSessao().setAttribute("usuarioAutenticado", usu);
        }

        public UsuarioAutenticadoVO getUsuarioAutenticado()
        {
            //UsuarioAutenticadoVO usu = (UsuarioAutenticadoVO)getSessao().getAttribute("usuarioAutenticado");
            UsuarioAutenticadoVO usu = new UsuarioAutenticadoVO();
            return usu;
        }

        public void removeUsuarioAutenticado()
        {
            //getSessao().removeAttribute("usuarioAutenticado");
        }

    }
}
