package org.sergei.batch.tx;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import java.util.Properties;

/**
 * Created by Sergei_Doroshenko on 12/22/2016.
 */
public class App {
    public static void main ( String[] args ) {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        Properties properties = new Properties();
        long executionId = jobOperator.start( "db_sample", properties );
    }
}
