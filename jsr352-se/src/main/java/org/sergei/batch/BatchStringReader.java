package org.sergei.batch;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.ItemReader;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class BatchStringReader implements ItemReader {
    private BufferedReader lineReader;
    private int lineNumber;
    private boolean alreadyFailed;

    @Inject
    @BatchProperty
    private String fileName;

    /*
    Checkpoint/Restart data provided as Serializable argument to “open” and from “checkpointInfo” methods.
     */
    @Override
    public void open(Serializable serializable) throws Exception {
        lineReader = new BufferedReader(new FileReader(new File(fileName)));

        if (serializable != null) {
            lineNumber = (Integer) serializable;
            for (int i = 0; i < lineNumber; i++) {
                lineReader.readLine();
            }
        }
    }

    @Override
    public void close() throws Exception {
        if (lineReader != null) {
            lineReader.close();
        }
    }

    @Override
    public Object readItem() throws Exception {
        ++lineNumber;
        String line = lineReader.readLine();

        // Simulate a read exception when the lineNumber is equal to 5. Only once.
        /*if (lineNumber == 1 && !alreadyFailed) {
            alreadyFailed = true;
            throw new IllegalArgumentException("Could not read record: " + line );
        }*/

        return line;
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return lineNumber;
    }

}
