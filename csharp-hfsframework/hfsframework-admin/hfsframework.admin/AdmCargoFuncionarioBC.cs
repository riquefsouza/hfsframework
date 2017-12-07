using System.Collections.Generic;
using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.admin.data;

namespace hfsframework.admin
{
    public class AdmCargoFuncionarioBC: BaseBusinessController<AdmCargoFuncionario, long, AdmCargoFuncionarioRepository>
    {
        private static AdmCargoFuncionarioBC instancia = new AdmCargoFuncionarioBC();

        public static AdmCargoFuncionarioBC Instancia
        {
            get
            {
                return instancia;
            }
        }

        private AdmCargoFuncionarioBC():base(new AdmCargoFuncionarioRepository())
        {
        }

        public List<AdmCargo> findCargoByCodFuncionario(long codFuncionario)
        {
            List<AdmCargo> lista = new List<AdmCargo>();
            IList<long> origem = repositorio.findCargoByCodFuncionario(codFuncionario);

            foreach (long item in origem)
            {
                lista.Add(AdmCargoBC.Instancia.load(item));
            }

            return lista;
        }

        public List<AdmFuncionario> findFuncionarioByCodCargo(long codCargo)
        {
            List<AdmFuncionario> lista = new List<AdmFuncionario>();
            IList<long> origem = repositorio.findFuncionarioByCodCargo(codCargo);

            foreach (long item in origem)
            {
                lista.Add(AdmFuncionarioBC.Instancia.load(item));
            }

            return lista;
        }

    }
}
