package org.sergei.batch;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by Sergei_Doroshenko on 11/7/2016.
 */
public class BootstrapBatchlet extends AbstractBatchlet {

    @Inject
    JobContext jobContext;

    @Override
    public String process() throws Exception {
        long executionId = jobContext.getExecutionId();
        String outputDir = "batchFiles\\out\\";
        String jobOutputDir = outputDir + + jobContext.getExecutionId();
        String outputFile = jobOutputDir  + "\\results.txt";
        Path path = Paths.get(jobOutputDir);
        Files.createDirectories(path);

        jobContext.getProperties().setProperty( "out.dir", outputDir );
        jobContext.getProperties().setProperty( "job.out.dir", jobOutputDir );
        jobContext.getProperties().setProperty( "file.out", outputFile );

        Path file = Paths.get(outputFile);
        Files.write(file,
                Arrays.asList("execution id: " + executionId, "------------------------------------"),
                Charset.forName("UTF-8"));

        System.out.println(BatchRuntime.getJobOperator().getJobExecution(jobContext.getExecutionId()).getJobParameters());
        return "COMPLETED";
    }
}
