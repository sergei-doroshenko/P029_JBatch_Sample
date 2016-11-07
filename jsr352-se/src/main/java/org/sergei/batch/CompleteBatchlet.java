package org.sergei.batch;

import org.zeroturnaround.zip.ZipUtil;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import java.io.File;

/**
 * Created by Sergei_Doroshenko on 11/7/2016.
 */
public class CompleteBatchlet extends AbstractBatchlet {

    @Inject
    private JobContext jobContext;

    @Override
    public String process() throws Exception {
        String fileName = jobContext.getProperties().getProperty( "job.out.dir" );
        String zipFileName = jobContext.getProperties().getProperty( "out.dir" ) +  "\\results.zip";
        ZipUtil.pack(new File(fileName), new File(zipFileName));
        return null;
    }
}
