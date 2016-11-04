package org.sergei.batch;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.Metric;
import javax.batch.runtime.StepExecution;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class BatchApp {
    public static void main ( String[] args ) {
        runJob( args[0] );
    }

    public static void runJob ( String jobName ) {
        JobOperator jobOperator = BatchRuntime.getJobOperator();

        Properties properties = new Properties();
        properties.setProperty( "file.in", "batchFiles/in/words.txt" );
        properties.setProperty( "file.out", "batchFiles/out/output.txt" );

        long executionId = jobOperator.start( jobName, properties );

        // Monitor the job
        JobExecution execution = jobOperator.getJobExecution( executionId );

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

                List<StepExecution> stepExecutions = jobOperator.getStepExecutions( executionId );

                for ( StepExecution stepExecution : stepExecutions ) {

                    System.out.println( "**             Step name: " + stepExecution.getStepName() );

                    Map<Metric.MetricType, Long> metricsMap = getMetricsMap( stepExecution.getMetrics() );

                    System.out.println( "**             Read count: " + metricsMap.get( Metric.MetricType.READ_COUNT ).longValue() );
                    System.out.println( "**             Write count: " + metricsMap.get( Metric.MetricType.WRITE_COUNT ).longValue() );
                    System.out.println( "**             Commit count:" + metricsMap.get( Metric.MetricType.COMMIT_COUNT ).longValue() );
                }


                System.exit( 0 );
            }
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    private static Map<Metric.MetricType, Long> getMetricsMap ( Metric[] metrics ) {
        Map<Metric.MetricType, Long> metricsMap = new HashMap<>();
        for ( Metric metric : metrics ) {
            metricsMap.put( metric.getType(), metric.getValue() );
        }
        return metricsMap;
    }
}
