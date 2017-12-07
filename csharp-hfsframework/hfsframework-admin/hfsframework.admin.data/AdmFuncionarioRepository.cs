using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.util;
using NHibernate;

namespace hfsframework.admin.data
{
    public class AdmFuncionarioRepository: EntityRepository<AdmFuncionario, long>
    {
        public AdmFuncionario findByMatriculaAndCPF(long id, long cpf)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmFuncionario.findByMatriculaAndCPF");
                q.SetInt64(0, id);
                q.SetInt64(1, cpf);
                return q.UniqueResult<AdmFuncionario>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

    }
}
