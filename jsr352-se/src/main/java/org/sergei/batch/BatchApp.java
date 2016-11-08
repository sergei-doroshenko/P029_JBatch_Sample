package org.sergei.batch;

import org.sergei.batch.util.BatchUtils;

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
        if ( args.length == 0 ) {
            throw new IllegalStateException("Define job name and parameters.");
        }
        runJob( args[0] );
    }

    public static void runJob ( String jobName ) {
        JobOperator jobOperator = BatchRuntime.getJobOperator();

        Properties properties = new Properties();
        properties.setProperty( "file.in", "batchFiles/in/words.txt" );
//        properties.setProperty( "file.out", "batchFiles/out/output.txt" );

//        properties.setProperty( "file.in", "test.csv" );
//        properties.setProperty( "file.out", "out.xml" );

        long executionId = jobOperator.start( jobName, properties );
        JobExecution execution = jobOperator.getJobExecution( executionId );
        try {
            Thread.sleep( 1000 * 10 );

            if ( execution.getExitStatus() != null && execution.getExitStatus().equals( "COMPLETED" ) ) {

                System.out.println( BatchUtils.SEPARATOR );
                System.out.printf( BatchUtils.STR_FORMAT, "Job end time", BatchUtils.DATE_FORMAT.format(execution.getEndTime()) );
                System.out.printf( BatchUtils.STR_FORMAT, "Job exit status",  execution.getExitStatus() );
                System.out.println( BatchUtils.SEPARATOR );

                List<StepExecution> stepExecutions = jobOperator.getStepExecutions( executionId );

                for ( StepExecution stepExecution : stepExecutions ) {
                    printMetrics( stepExecution );
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

    private static void printMetrics(StepExecution stepExecution) {

        System.out.printf( BatchUtils.STR_FORMAT, "Step name", stepExecution.getStepName() );

        Map<Metric.MetricType, Long> metricsMap = getMetricsMap( stepExecution.getMetrics() );

        System.out.printf( BatchUtils.NUM_FORMAT, "Read count", metricsMap.get( Metric.MetricType.READ_COUNT ).longValue() );
        System.out.printf( BatchUtils.NUM_FORMAT, "Write count", metricsMap.get( Metric.MetricType.WRITE_COUNT ).longValue() );
        System.out.printf( BatchUtils.NUM_FORMAT, "Read skip count", metricsMap.get( Metric.MetricType.READ_SKIP_COUNT ).longValue() );
        System.out.printf( BatchUtils.NUM_FORMAT, "Write skip count", metricsMap.get( Metric.MetricType.WRITE_SKIP_COUNT ).longValue() );
        System.out.printf( BatchUtils.NUM_FORMAT, "Commit count", metricsMap.get( Metric.MetricType.COMMIT_COUNT ).longValue() );

        System.out.println( BatchUtils.SEPARATOR );
    }
}
