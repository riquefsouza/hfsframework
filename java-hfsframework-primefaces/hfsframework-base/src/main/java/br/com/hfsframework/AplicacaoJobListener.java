/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * The listener interface for receiving aplicacaoJob events. The class that is
 * interested in processing a aplicacaoJob event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's addAplicacaoJobListener method. When the aplicacaoJob
 * event occurs, that object's appropriate method is invoked.
 * 
 */
public class AplicacaoJobListener implements JobListener {

	/** The Constant LISTENER_NAME. */
	public static final String LISTENER_NAME = "APLICACAO JOB LISTENER";
	
	/** The Constant log. */
	private static final Logger log = LogManager.getLogger(AplicacaoJobListener.class);

	/* (non-Javadoc)
	 * @see org.quartz.JobListener#getName()
	 */
	@Override
	public String getName() {
		return LISTENER_NAME;
	}

	/* (non-Javadoc)
	 * @see org.quartz.JobListener#jobToBeExecuted(org.quartz.JobExecutionContext)
	 */
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		String jobName = context.getJobDetail().getKey().toString();
		log.info("JOB iniciando: " + jobName);

	}

	/* (non-Javadoc)
	 * @see org.quartz.JobListener#jobExecutionVetoed(org.quartz.JobExecutionContext)
	 */
	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		String jobName = context.getJobDetail().getKey().toString();
		log.info("JOB execução vetada: " + jobName);
	}

	/* (non-Javadoc)
	 * @see org.quartz.JobListener#jobWasExecuted(org.quartz.JobExecutionContext, org.quartz.JobExecutionException)
	 */
	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		String jobName = context.getJobDetail().getKey().toString();
		log.info("JOB foi executado: " + jobName);

		if (jobException != null) {
			if (!jobException.getMessage().equals("")) {
				log.info("Exception thrown by: " + jobName);
				log.info("Exception: " + jobException.getMessage());
			}
		}

	}

}
