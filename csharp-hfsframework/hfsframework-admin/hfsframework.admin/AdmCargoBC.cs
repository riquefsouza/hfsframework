using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.admin.data;

namespace hfsframework.admin
{
    public class AdmCargoBC: BaseBusinessController<AdmCargo, long, AdmCargoRepository> 
    {
        private static AdmCargoBC instancia = new AdmCargoBC();

        public static AdmCargoBC Instancia
        {
            get
            {
                return instancia;
            }
        }

        private AdmCargoBC():base(new AdmCargoRepository())
        {
        }        
    }
}
