package org.sergei.batch.listeners;

import org.sergei.batch.util.BatchUtils;

import javax.batch.api.listener.AbstractJobListener;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class InfoJobListener extends AbstractJobListener {

    @Inject
    private JobContext jobContext;

    @Override
    public void beforeJob() throws IOException {
        long executionId = jobContext.getExecutionId();
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        JobExecution jobExecution = jobOperator.getJobExecution( executionId );

        System.out.println( BatchUtils.SEPARATOR );
        System.out.printf( BatchUtils.STR_FORMAT, "Job create time", BatchUtils.DATE_FORMAT.format(jobExecution.getCreateTime()) );
        System.out.printf( BatchUtils.STR_FORMAT, "Job start time", BatchUtils.DATE_FORMAT.format(jobExecution.getStartTime()) );
        System.out.println( BatchUtils.SEPARATOR );
    }

    @Override
    public void afterJob() {
        System.out.println("SimpleJobListener.afterJob");
    }
}
