package org.sergei.batch;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import java.util.Properties;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class BatchApp {
    public static void main ( String[] args ) {
        runJob( args[0] );
    }

    public static void runJob ( String jobName ) {
        JobOperator operator = BatchRuntime.getJobOperator();

        Properties properties = new Properties();
        properties.setProperty( "file.in", "batchFiles/in/words.txt" );
        properties.setProperty( "file.out", "batchFiles/out/output.txt" );

        long jobId = operator.start( jobName, properties );

        // Monitor the job
        JobExecution execution = operator.getJobExecution( jobId );

        System.out.println( "****************************************************************" );
        System.out.println( "**             Job name: " + execution.getJobName() );
        System.out.println( "**             Job create time:" + execution.getCreateTime() );
        System.out.println( "**             Job start time: " + execution.getStartTime() );
        System.out.println( "****************************************************************" );

        try {
            Thread.sleep( 1000 * 10 );
            if ( execution.getExitStatus().equals( "COMPLETED" ) ) {

                System.out.println( "****************************************************************" );
                System.out.println( "**             Job end time: " + execution.getEndTime() );
                System.out.println( "**             Job exit status: " + execution.getExitStatus() );
                System.out.println( "****************************************************************" );

                System.exit( 0 );
            }
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }
}
