package org.sergei.batch;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.ItemWriter;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class BatchStringWriter implements ItemWriter {

    @Inject
    private JobContext jobContext;

    private BufferedWriter writer;
    private int lineNumber;
    private int retries;

    @Override
    public void open(Serializable serializable) throws Exception {
        String fileName = jobContext.getProperties().getProperty( "file.out" );
        writer = new BufferedWriter(new FileWriter(fileName, true));
    }

    @Override
    public void close() throws Exception {
        if(writer != null ) {
            writer.close();
        }
    }

    @Override
    public void writeItems(List<Object> list) throws Exception {

        for(Object line : list) {

            /*if ( line != null && retries < 1 && ((String) line).startsWith("n") ) {
                retries++;
                System.out.println("Throw UnsupportedOperationException in StringWriter");
                throw new UnsupportedOperationException();
            }*/
            lineNumber++;
            writer.write(line + "\n");
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return lineNumber;
    }
}
