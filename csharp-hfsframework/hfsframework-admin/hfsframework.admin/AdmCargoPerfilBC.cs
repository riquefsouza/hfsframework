using System.Collections.Generic;
using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.admin.data;

namespace hfsframework.admin
{
    public class AdmCargoPerfilBC: BaseBusinessController<AdmCargoPerfil, long, AdmCargoPerfilRepository>
    {
        private static AdmCargoPerfilBC instancia = new AdmCargoPerfilBC();

        public static AdmCargoPerfilBC Instancia
        {
            get
            {
                return instancia;
            }
        }

        private AdmCargoPerfilBC():base(new AdmCargoPerfilRepository())
        {
        }

        public List<AdmPerfil> findPerfilByCodCargo(long codCargo)
        {
            return new List<AdmPerfil>(repositorio.findPerfilByCodCargo(codCargo));
        }

        public void deleteByPerfil(long perfilSeq)
        {
            repositorio.deleteByPerfil(perfilSeq);
        }

        public void deleteByCodCargo(long codCargo)
        {
            repositorio.deleteByCodCargo(codCargo);
        }

    }
}
