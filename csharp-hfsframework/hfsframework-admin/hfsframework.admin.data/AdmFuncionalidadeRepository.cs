using System.Collections.Generic;
using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.util;
using NHibernate;

namespace hfsframework.admin.data
{
    public class AdmFuncionalidadeRepository: EntityRepository<AdmFuncionalidade, long>
    {
        public override string getDescricao(long id)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmFuncionalidade.getDescricaoById");
                q.SetInt64(0, long.Parse(id.ToString()));
                return q.UniqueResult<string>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public override bool existeNovo(string novo)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmFuncionalidade.countNovo");
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
                IQuery q = session.GetNamedQuery("AdmFuncionalidade.countAntigo");
                q.SetString(0, antigo.ToLower());
                q.SetString(1, novo.ToLower());
                return (q.UniqueResult<long>() > 0);
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public IList<AdmPerfil> findPerfisPorFuncionalidade(AdmFuncionalidade funcionalidade)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmFuncionalidade.findPerfisPorFuncionalidade");
                q.SetEntity(0, funcionalidade);
                return q.List<AdmPerfil>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public IList<AdmPagina> findPaginasPorFuncionalidade(AdmFuncionalidade funcionalidade)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmFuncionalidade.findPaginasPorFuncionalidade");
                q.SetEntity(0, funcionalidade);
                return q.List<AdmPagina>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public IList<AdmMenu> findMenusPorFuncionalidade(AdmFuncionalidade funcionalidade)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmFuncionalidade.findMenusPorFuncionalidade");
                q.SetEntity(0, funcionalidade);
                return q.List<AdmMenu>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public IList<AdmPerfil> findPerfisPorURL(string url)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmFuncionalidade.findPerfisPorURL");
                q.SetString(0, url);
                q.SetString(1, url);
                return q.List<AdmPerfil>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

    }
}
