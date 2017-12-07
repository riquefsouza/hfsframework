using System;
using System.Collections.Generic;
using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.util;
using hfsframework.util.exceptions;
using NHibernate;

namespace hfsframework.admin.data
{
    public class AdmCargoPerfilRepository: EntityRepository<AdmCargoPerfil, long>
    {
        public IList<AdmPerfil> findPerfilByCodCargo(long codCargo)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                IQuery q = session.GetNamedQuery("AdmCargoPerfil.findPerfilByCodCargo");
                q.SetInt64(0, codCargo);
                return q.List<AdmPerfil>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public void deleteByPerfil(long perfilSeq)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                using (ITransaction tx = session.BeginTransaction())
                {
                    try
                    {
                        IQuery q = session.GetNamedQuery("AdmCargoPerfil.deleteByPerfil");
                        q.SetInt64(0, perfilSeq);
                        q.ExecuteUpdate();
                        session.Flush();
                        session.Clear();
                        tx.Commit();
                    }
                    catch (Exception e)
                    {
                        tx.Rollback();
                        throw new TransacaoException(log, ERRO_DELETE + e.Message);
                    }
                }
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public void deleteByCodCargo(long codCargo)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                using (ITransaction tx = session.BeginTransaction())
                {
                    try
                    {
                        IQuery q = session.GetNamedQuery("AdmCargoPerfil.deleteCodCargo");
                        q.SetInt64(0, codCargo);
                        q.ExecuteUpdate();
                        session.Flush();
                        session.Clear();
                        tx.Commit();
                    }
                    catch (Exception e)
                    {
                        tx.Rollback();
                        throw new TransacaoException(log, ERRO_DELETE + e.Message);
                    }
                }
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }
    }
}
