using System.Collections.Generic;
using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.util;
using NHibernate;

namespace hfsframework.admin.data
{
    public class AdmMenuRepository: EntityRepository<AdmMenu, long>
    {
        public override bool existeNovo(string novo)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmMenu.countNovo");
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
                IQuery q = session.GetNamedQuery("AdmMenu.countAntigo");
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
                IQuery q = session.GetNamedQuery("AdmMenu.getDescricaoById");
                q.SetInt64(0, long.Parse(id.ToString()));
                return q.UniqueResult<string>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public IList<AdmMenu> findMenuRaiz()
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmMenu.findMenuRaiz");
                return q.List<AdmMenu>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public IList<AdmMenu> findMenuRaiz(string nomeItemMenu)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmMenu.findMenuRaizByDescricao");
                q.SetString(0, nomeItemMenu);
                return q.List<AdmMenu>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public int countMenuByFuncionalidade(AdmFuncionalidade funcionalidade)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmMenu.countMenuByFuncionalidade");
                q.SetEntity(0, funcionalidade);
                return q.UniqueResult<int>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public IList<AdmMenu> findMenusFilhos(AdmMenu menu)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmMenu.findMenusFilhos");
                q.SetEntity(0, menu);
                return q.List<AdmMenu>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public IList<AdmMenu> findAdminMenuPaiByCodFuncionario(AdmFuncionalidade funcionalidade)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmMenu.findAdminMenuPaiByFuncionalidade");
                q.SetEntity(0, funcionalidade);
                return q.List<AdmMenu>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public IList<AdmMenu> findMenuPaiByCodFuncionario(AdmFuncionalidade funcionalidade)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmMenu.findMenuPaiByFuncionalidade");
                q.SetEntity(0, funcionalidade);
                return q.List<AdmMenu>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public AdmPagina findPaginaByMenu(AdmFuncionalidade funcionalidade, long idMenu)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmMenu.findPaginaByMenu");
                q.SetEntity(0, funcionalidade);
                q.SetInt64(1, idMenu);
                return q.UniqueResult<AdmPagina>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

    }
}
