package org.sergei.batch;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import java.util.Properties;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class App {
    public static void main(String[] args) {
        runJob();
    }

    public static void runJob() {
        JobOperator operator = BatchRuntime.getJobOperator();

        Properties properties = new Properties();
        properties.setProperty("file.in", "batchFiles/in/words.txt");

        long jobId = operator.start( "sample", properties);

//        long jobId = operator.start( "sample", null );
//        long jobId = operator.start( "partitioned", null );
//        long jobId = operator.start( "parallel", null );

        // Monitor the job
        JobExecution execution = operator.getJobExecution(jobId);

        System.out.println( "Job name: " + execution.getJobName() );
        System.out.println( "Job create time:" + execution.getCreateTime() );
        System.out.println( "Job start time: " + execution.getStartTime() );

        try {
            Thread.sleep(1000 * 10);
            if ( execution.getExitStatus().equals( "COMPLETED" ) ) {

                System.out.println( "Job end time: " + execution.getEndTime() );
                System.out.println( "Job exit status: " + execution.getExitStatus() );

                System.exit(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
