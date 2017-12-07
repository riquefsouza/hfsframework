using hfsframework.util;
using hfsframework.util.exceptions;
using log4net;
using NHibernate;
using System;
using System.Collections.Generic;
using System.Linq;

namespace hfsframework.hfsbase
{
    public class EntityRepository<T, I> : IBaseCrud<T, I>
    {
        public const string ERRO_INSERT = "Erro de Transação ao Incluir: ";

        public const string ERRO_UPDATE = "Erro de Transação ao Alterar: ";

        public const string ERRO_DELETE = "Erro de Transação ao Excluir: ";

        protected ILog log;

        public EntityRepository()
        {
            log = LogManager.GetLogger(typeof(EntityRepository<T, I>));
        }

        public T load(I id)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                return session.Get<T>(id);
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public List<T> findAll()
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                return session.Query<T>().ToList();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

        public T insert(T bean)
        {
            T rbean;
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                using (ITransaction tx = session.BeginTransaction())
                {
                    try
                    {
                        rbean = (T) session.Save(bean);
                        session.Flush();
                        session.Clear();
                        tx.Commit();
                    }
                    catch (Exception e)
                    {
                        tx.Rollback();
                        throw new TransacaoException(log, ERRO_INSERT + e.Message);
                    }
                }
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
            return rbean;
        }

        public T update(T bean)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                using (ITransaction tx = session.BeginTransaction())
                {
                    try
                    {
                        session.Update(bean);
                        session.Flush();
                        session.Clear();
                        tx.Commit();
                    }
                    catch (Exception e)
                    {
                        tx.Rollback();
                        throw new TransacaoException(log, ERRO_UPDATE + e.Message);
                    }
                }
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
            return bean;
        }

        public void delete(T bean)
        {
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                using (ITransaction tx = session.BeginTransaction())
                {
                    try
                    {
                        session.Delete(bean);
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

        public virtual bool existeAntigo(I id, string novo)
        {
            return false;
        }

        public virtual bool existeNovo(string novo)
        {
            return false;
        }

        public virtual string getDescricao(I id)
        {
            return "";
        }

        public virtual List<T> listarPaginada(int numeroPagina, int tamanhoPagina)
        {
            //SQL_PAGINACAO = "select * from (select tabela.*, id linha from (select * from TABELA order by id) tabela where id < ((? * ?) + 1 )) where linha >= (((?-1) * ?) + 1)";
            return null;
        }

        public List<T> listarPorIntervalo(int intervaloInicial, int intervaloFinal)
        {
            //SQL_INTERVALO = "select * from (select tabela.*, id linha from (select * from TABELA order by id) tabela where id <= ?) where linha >= ?";
            ISession session = NHibernateHelper.GetCurrentSession();
            try
            {
                ICriteria cq = session.CreateCriteria(typeof(T));
                cq.SetMaxResults(intervaloFinal - intervaloInicial + 1);
                cq.SetFirstResult(intervaloInicial);
                return (List<T>) cq.List<T>();
            }
            finally
            {
                NHibernateHelper.CloseSession();
            }
        }

    }
}
