using System;
using hfsframework.hfsbase;
using hfsframework.admin.model;
using hfsframework.util;
using hfsframework.util.exceptions;
using NHibernate;

namespace hfsframework.admin.data
{
    public class AdmFuncionarioPerfilRepository: EntityRepository<AdmFuncionarioPerfil, long>
    {
        public void deleteByPerfil(long perfilSeq)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                using (ITransaction tx = session.BeginTransaction())
                {
                    try
                    {
                        IQuery q = session.GetNamedQuery("AdmFuncionarioPerfil.deleteByPerfil");
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

        public void deleteByCodFuncionario(long codFuncionario)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                using (ITransaction tx = session.BeginTransaction())
                {
                    try
                    {
                        IQuery q = session.GetNamedQuery("AdmFuncionarioPerfil.deleteByCodFuncionario");
                        q.SetInt64(0, codFuncionario);
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
