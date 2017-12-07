using System;
using hfsframework.util;
using hfsframework.admin;
using hfsframework;
using log4net;

namespace ConsoleNHibernate
{
    class Program
    {
        static void Main(string[] args)
        {
            var log = LogManager.GetLogger(typeof(Program));

            log.Info("TESTE");

            Aplicacao app = new Aplicacao();

            //app.GetNHibernateHelper().ExportaEsquema(false);

            var cargos = AdmCargoFuncionarioBC.Instancia.findCargoByCodFuncionario(93203);
            foreach (var cargo in cargos)
            {
                Console.Out.WriteLine(cargo.NomeCargo);
            }
            //Console.Out.WriteLine(AdmCargoBC.Instancia.getDescricao(15387));

            app.destroy();

            Console.ReadLine();
        }
    }
}
