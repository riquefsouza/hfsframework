using System.Collections.Generic;
using NHibernate;
using NHibernate.Cfg;
using NHibernate.Tool.hbm2ddl;

namespace hfsframework.util
{
    public sealed class NHibernateHelper
    {
        //private const string CurrentSessionKey = "nhibernate.current_session";
        private static ISessionFactory _sessionFactory;
        private static Configuration _configuration;
        private static ISession currentSession;

        public NHibernateHelper()
        {
            _configuration = new Configuration();
            _configuration.Configure();
            _sessionFactory = _configuration.BuildSessionFactory();
        }

        public static ISession GetCurrentSession()
        {
            //var context = HttpContext.Current;
            //var currentSession = context[CurrentSessionKey] as ISession;
            
            currentSession = _sessionFactory.OpenSession();
            return currentSession;
        }

        public static void CloseSession()
        {
            //var context = HttpContext.Current;
            //var currentSession = context[CurrentSessionKey] as ISession;
            currentSession.Close();
            //context.Items.Remove(CurrentSessionKey);
        }

        public static void CloseSessionFactory()
        {
            if (_sessionFactory != null)
            {
                _sessionFactory.Close();
            }
        }

        public void ExportaEsquema(bool executa) {
            if (_configuration != null)
            {
                new SchemaExport(_configuration).Execute(true, executa, false);
            }
        }
            
    }
}
