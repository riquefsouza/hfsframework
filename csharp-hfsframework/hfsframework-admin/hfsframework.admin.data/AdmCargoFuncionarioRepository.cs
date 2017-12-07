using System.Collections.Generic;
using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.util;
using NHibernate;

namespace hfsframework.admin.data
{
    public class AdmCargoFuncionarioRepository: EntityRepository<AdmCargoFuncionario, long>
    {
        public IList<long> findCargoByCodFuncionario(long codFuncionario)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmCargoFuncionario.findCargoByCodFuncionario");
                q.SetInt64(0, codFuncionario);
                return q.List<long>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public IList<long> findFuncionarioByCodCargo(long codCargo)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmCargoFuncionario.findFuncionarioByCodCargo");
                q.SetInt64(0, codCargo);
                return q.List<long>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

    }
}
