package org.sergei;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class App {
    public static void main(String[] args) {
        JobOperator operator = BatchRuntime.getJobOperator();

//        long jobId = operator.start( "sample", null );
//        long jobId = operator.start( "partitioned", null );
        long jobId = operator.start( "parallel", null );

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
