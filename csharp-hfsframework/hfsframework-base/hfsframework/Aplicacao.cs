using log4net;
using hfsframework.util;

namespace hfsframework
{
    public sealed class Aplicacao
    {
        private static ILog log = LogManager.GetLogger(typeof(Aplicacao));

        private AplicacaoConfig aplicacaoConfig;

        private NHibernateHelper helper;

        public Aplicacao()
        {
            aplicacaoConfig = new AplicacaoConfig();

            helper = new NHibernateHelper();

            log.Info("------------------------------------------------------------------------");
            log.Info("HFS Framework");
            log.Info("Desenvolvido por Henrique Figueiredo de Souza");
            log.Info("Versão 1.0 - 2017");
            log.Info("------------------------------------------------------------------------");
            log.Info("Iniciando HFS Framework...");

            log.Info(aplicacaoConfig.ToString());
        }

        public void destroy()
        {
            log.Info("Finalizado HFS Framework...");
            log.Info("------------------------------------------------------------------------");
            log.Info("HFS Framework");
            log.Info("Desenvolvido por Henrique Figueiredo de Souza");
            log.Info("Versão 1.0 - 2017");
            log.Info("------------------------------------------------------------------------");
        }

    }
}
