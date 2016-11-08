package org.sergei.batch;

import javax.batch.api.BatchProperty;
import javax.batch.api.partition.PartitionMapper;
import javax.batch.api.partition.PartitionPlan;
import javax.batch.api.partition.PartitionPlanImpl;
import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class FilesInFolderPartitionMapper implements PartitionMapper {

    @Inject
    @BatchProperty
    private String filePath;

    @Inject
    @BatchProperty
    private String outputPath;

    @Override
    public PartitionPlan mapPartitions() throws Exception {

        File path = new File(filePath);

        List<String> entryPaths = new ArrayList<>();
        for ( String entry : path.list() ) {
            String entryPath = filePath + File.separator + entry;
            if ( new File(entryPath).isFile() ) {
                entryPaths.add( entryPath );
            }
        }

        int i = 0;
        PartitionPlan plan = new PartitionPlanImpl();
        Properties[] properties = new Properties[ entryPaths.size() ];

        for ( String entry : entryPaths ) {
            properties[i] = new Properties();
            properties[i].setProperty("input.fileName", entry);
            properties[i].setProperty("output.fileName", outputPath + File.separator + "/partition_" + i + ".txt");

            i++;
        }

        plan.setPartitions( properties.length );
        plan.setPartitionProperties( properties );

        return plan;
    }
}
