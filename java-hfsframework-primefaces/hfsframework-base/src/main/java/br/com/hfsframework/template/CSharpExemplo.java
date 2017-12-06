package br.com.hfsframework.template;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

import br.com.hfsframework.util.metadados.MetadadosConfig;
import br.com.hfsframework.util.metadados.MetadadosUtil;
import freemarker.template.TemplateException;

public class CSharpExemplo {

	public static void main(String[] args) throws IOException, TemplateException {
		final LoggerContext ctx = PrivateManager.getContext();
		ctx.reconfigure();
		
		MetadadosUtil mu = new MetadadosUtil();
		mu.configurar(MetadadosConfig.hfsbancoDS());
		TemplateUtil.gerarCSharp(mu, "public", "c:/Temp");
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
