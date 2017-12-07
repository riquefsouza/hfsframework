using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.util;
using NHibernate;

namespace hfsframework.admin.data
{
    public class AdmCargoRepository: EntityRepository<AdmCargo, long>
    {
        public override bool existeNovo(string novo)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmCargo.countNovo");
                q.SetString(0, novo.ToLower());
                return (q.UniqueResult<long>() > 0);
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public override bool existeAntigo(long id, string novo)
        {
            string antigo = getDescricao(id);

            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {                
                IQuery q = session.GetNamedQuery("AdmCargo.countAntigo");
                q.SetString(0, antigo.ToLower());
                q.SetString(1, novo.ToLower());
                return (q.UniqueResult<long>() > 0);
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public override string getDescricao(long id)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmCargo.getDescricaoById");
                q.SetInt64(0, long.Parse(id.ToString()));
                return q.UniqueResult<string>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }
    }
}
