package org.sergei.batch;

import org.sergei.batch.util.BatchUtils;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.*;
import java.util.*;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class BatchApp {

    public static void main ( String[] args ) throws InterruptedException {
        if ( args.length == 0 ) {
            throw new IllegalStateException( "Define job name and parameters." );
        }
        runJob( args[0] );
    }

    public static void runJob ( String jobName ) throws InterruptedException {
        JobOperator jobOperator = BatchRuntime.getJobOperator();

        Properties properties = new Properties();
        properties.setProperty( "file.in", "batchFiles/in/input.txt" );
        properties.setProperty( "part.dir.out", "batchFiles/out" );
//        properties.setProperty( "file.out", "batchFiles/out/output.txt" );

//        properties.setProperty( "file.in", "test.csv" );
//        properties.setProperty( "file.out", "out.xml" );

//        printPreviousExecutions( jobOperator );

        long executionId = jobOperator.start( jobName, properties );
        JobExecution execution = jobOperator.getJobExecution( executionId );

        while ( execution.getBatchStatus().equals( BatchStatus.STARTED ) ) {
            Thread.sleep( 100 * 10 );
        }

        if ( execution.getBatchStatus().equals( BatchStatus.COMPLETED ) ) {
            System.out.println( BatchUtils.SEPARATOR );
            System.out.printf( BatchUtils.STR_FORMAT, "Job end time", BatchUtils.DATE_FORMAT.format( execution.getEndTime() ) );
            System.out.printf( BatchUtils.STR_FORMAT, "Job exit status", execution.getExitStatus() );
            System.out.println( BatchUtils.SEPARATOR );

            List<StepExecution> stepExecutions = jobOperator.getStepExecutions( executionId );

            stepExecutions.forEach( stepExecution -> printMetrics( stepExecution ) );

            System.exit( 0 );
        }
    }

    private static Map<Metric.MetricType, Long> getMetricsMap ( Metric[] metrics ) {
        Map<Metric.MetricType, Long> metricsMap = new HashMap<>();
        Arrays.stream( metrics ).forEach( m -> metricsMap.put( m.getType(), m.getValue() ) );

        return metricsMap;
    }

    private static void printMetrics ( StepExecution stepExecution ) {

        System.out.printf( BatchUtils.STR_FORMAT, "Step name", stepExecution.getStepName() );

        Map<Metric.MetricType, Long> metricsMap = getMetricsMap( stepExecution.getMetrics() );

        System.out.printf( BatchUtils.NUM_FORMAT, "Read count", metricsMap.get( Metric.MetricType.READ_COUNT ).longValue() );
        System.out.printf( BatchUtils.NUM_FORMAT, "Write count", metricsMap.get( Metric.MetricType.WRITE_COUNT ).longValue() );
        System.out.printf( BatchUtils.NUM_FORMAT, "Read skip count", metricsMap.get( Metric.MetricType.READ_SKIP_COUNT ).longValue() );
        System.out.printf( BatchUtils.NUM_FORMAT, "Write skip count", metricsMap.get( Metric.MetricType.WRITE_SKIP_COUNT ).longValue() );
        System.out.printf( BatchUtils.NUM_FORMAT, "Commit count", metricsMap.get( Metric.MetricType.COMMIT_COUNT ).longValue() );

        System.out.println( BatchUtils.SEPARATOR );
    }

    private static void printPreviousExecutions ( JobOperator jobOperator ) {
        List<JobInstance> jobInstances = jobOperator.getJobInstances( "sampleJob", 0, 1000 );
        jobInstances.forEach( i -> jobOperator.getJobExecutions( i )
                .forEach( ex -> System.out.println( "id: " + ex.getExecutionId() + ", status: " + ex.getExitStatus() ) ) );

        /*List<Long> runningExecutions = jobOperator.getRunningExecutions( "sampleJob" );
        runningExecutions.forEach( e -> System.out.println(e) );*/
    }
}
