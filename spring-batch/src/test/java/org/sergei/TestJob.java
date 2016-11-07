package org.sergei;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/spring/context.config.xml",
		                         "classpath:META-INF/spring/job-config.xml"})
public class TestJob {
	
	@Autowired
	private JobLauncherTestUtils launcher;

	@Test
	public void testJob(){
		try {
			JobExecution execution = launcher.launchJob();
			Assert.assertEquals(BatchStatus.COMPLETED, execution.getStatus());
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	@Test
	public void testStep(){
		JobExecution execution = launcher.launchStep("batchStep");
		Assert.assertEquals(BatchStatus.COMPLETED, execution.getStatus());
	}

	@Test
	public void testJSR(){
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		jobOperator.start("simple-job", new Properties());
	}
}
