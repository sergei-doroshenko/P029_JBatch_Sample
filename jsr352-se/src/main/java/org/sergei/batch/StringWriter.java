package org.sergei.batch;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.ItemWriter;
import javax.inject.Inject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class StringWriter implements ItemWriter {

    private BufferedWriter writer;
    private int lineNumber;

    @Inject
    @BatchProperty
    private String fileName;

    @Override
    public void open(Serializable serializable) throws Exception {
        writer = new BufferedWriter(new FileWriter(new File(fileName)));
    }

    @Override
    public void close() throws Exception {
        writer.close();
    }

    @Override
    public void writeItems(List<Object> list) throws Exception {
        for(Object line : list) {
            System.out.println("Writer thread : " + Thread.currentThread().getName() + ", writes: " + line );
            writer.write(line + "\n");
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return lineNumber;
    }
}
