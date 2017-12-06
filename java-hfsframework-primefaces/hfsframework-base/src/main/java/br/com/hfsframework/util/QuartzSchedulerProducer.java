/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.apache.deltaspike.scheduler.spi.Scheduler;
import org.quartz.Job;

/**
 * The Class QuartzSchedulerProducer.
 */
public class QuartzSchedulerProducer {

	/* The log. */
	//@Inject
	//private Logger log;

	/**
	 * Produce scheduler.
	 *
	 * @param scheduler
	 *            the scheduler
	 * @return the scheduler
	 */
	@Produces
	@ApplicationScoped
	protected Scheduler<Job> produceScheduler(Scheduler<Job> scheduler) {
		/*
		org.quartz.Scheduler sd = scheduler.unwrap(org.quartz.Scheduler.class);

		try {
			sd.getListenerManager().addJobListener(new AplicacaoJobListener(),
					GroupMatcher.jobGroupEquals("fg_jobgroup_01"));
		} catch (SchedulerException e) {
			ExcecaoUtil.getErros(log, e, e.getMessage(), true);
		}
		*/

		return scheduler;
	}

}
