package org.sergei.batch.partition;

import javax.batch.api.BatchProperty;
import javax.batch.api.partition.PartitionMapper;
import javax.batch.api.partition.PartitionPlan;
import javax.batch.api.partition.PartitionPlanImpl;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class FileSplitPartitionMapper implements PartitionMapper {

    @Inject
    @BatchProperty
    private String inputFile;

    @Inject
    @BatchProperty
    private String inputDir;

    @Inject
    @BatchProperty
    private String outputPath;

    @Inject
    private JobContext jobContext;

    @Override
    public PartitionPlan mapPartitions() throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = null;
        String line = null;
        int count = 0;
        List<String> inputFiles = new ArrayList<>();
        int lines = 0;
        while ( (line = reader.readLine()) != null ) {
            if ( lines++ % 4 == 0 ) {
                if (writer != null) writer.close();
                String fileName = "batchFiles/in/input" + count + ".txt";
                inputFiles.add(fileName);
                writer = new BufferedWriter(new FileWriter(new File(fileName)));
                count++;
            }

            writer.write(line + "\n");
        }

        if (writer != null) writer.close();

        // File.separator

        int i = 0;
        PartitionPlan plan = new PartitionPlanImpl();
        Properties[] properties = new Properties[ inputFiles.size() ];

        for ( String file : inputFiles ) {
            properties[i] = new Properties(); // properties for each partition
            properties[i].setProperty("input.fileName", file);
            properties[i].setProperty("output.fileName", outputPath + File.separator + "/partition_" + i + ".txt");

            i++;
        }

        plan.setPartitions( properties.length );
        plan.setPartitionProperties( properties );

        jobContext.getProperties().setProperty( "out.dir", outputPath );

        return plan;
    }
}
