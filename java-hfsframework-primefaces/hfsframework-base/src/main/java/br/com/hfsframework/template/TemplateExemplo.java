/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.template;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

import freemarker.template.TemplateException;

/**
 * The Class TemplateUtil.
 */
public class TemplateExemplo {

	/** The Constant pacote. */
	private static final String pacote = "br.com.hfsframework.admin";
	
	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws TemplateException
	 *             the template exception
	 */
	public static void main(String[] args) throws IOException, TemplateException {
		final LoggerContext ctx = PrivateManager.getContext();
        ctx.reconfigure();

		/*
		String[][] classes = {
				{"AdmFuncionalidade", "Long"},
				{"AdmMenu", "Long"},
				{"AdmPagina", "Long"},
				{"AdmParametro", "Long"},
				{"AdmParametroCategoria", "Long"},
				{"AdmPerfil", "Long"},
				{"VwAdmCargo", "Long"},
				{"VwAdmFuncionario", "Long"},
				{"VwAdmLog", "Long"},
				{"VwAdmSetor", "String"},
				{"AdmLogColuna", "String"}
		};
		*/		
		String[][] classes = {
				{"AdmUsuario", "AdmUsuarioPK"}
		};
		
        String saida = "C:/Temp/blob";
        
        System.out.println(TemplateUtil.gerarMenu("/private/admin/", classes));
        
        System.out.println(TemplateUtil.gerarPages("/admin/", classes));
        
        String caminhoModelo = "";
        //String caminhoModelo = "C:/demoiselle3/workspace/hfssistema/src/main/java/br/com/hfsframework/template";
        
        TemplateUtil.gerarCodigoFonte(TemplateEnum.Repository, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.BC, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.MB, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.Converter, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.LazySorter, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.LazyDataModel, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.listar, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.editar, caminhoModelo, pacote, classes, saida);		
        TemplateUtil.gerarCodigoFonte(TemplateEnum.RelMB, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.reportar, caminhoModelo, pacote, classes, saida);
        
        TemplateUtil.gerarCodigoFonte(TemplateEnum.retrato, caminhoModelo, pacote, classes, saida);
        TemplateUtil.gerarCodigoFonte(TemplateEnum.paisagem, caminhoModelo, pacote, classes, saida);
	}
	
	private static class PrivateManager extends org.apache.logging.log4j.LogManager {
        private static final String FQCN = LogManager.class.getName();

        public static LoggerContext getContext() {
            return (LoggerContext) getContext(FQCN, false);
        }

        public static org.apache.logging.log4j.Logger getLogger(final String name) {
            return getLogger(FQCN, name);
        }
    }
	
}
