package org.sergei.batch;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Dependent
public class MyBatchlet extends AbstractBatchlet {

    @Inject
    JobContext jc;

    @Override
    public String process() throws Exception {
        System.out.println(BatchRuntime.getJobOperator().getJobExecution(jc.getExecutionId()).getJobParameters());
        System.out.println("Hello, JBatch");
        return null;
    }
}
