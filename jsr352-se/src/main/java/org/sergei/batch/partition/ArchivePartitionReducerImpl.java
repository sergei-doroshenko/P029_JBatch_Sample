package org.sergei.batch.partition;

import org.zeroturnaround.zip.ZipUtil;

import javax.batch.api.BatchProperty;
import javax.batch.api.partition.AbstractPartitionReducer;
import javax.batch.api.partition.PartitionReducer;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import java.io.File;

/**
 * Created by Sergei_Doroshenko on 11/9/2016.
 */
public class ArchivePartitionReducerImpl extends AbstractPartitionReducer {

    @Inject
    private JobContext jobContext;

    @Override
    public void afterPartitionedStepCompletion(PartitionStatus status) throws Exception {
        super.afterPartitionedStepCompletion(status);

        String fileName = jobContext.getProperties().getProperty( "out.dir" );
        if (fileName != null) {
            String zipFileName = "batchFiles\\partition.zip";
            ZipUtil.pack(new File(fileName), new File(zipFileName));
        }

        System.out.println("PartitionStatus: " + status);
    }
}
