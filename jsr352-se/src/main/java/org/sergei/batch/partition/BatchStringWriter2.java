package org.sergei.batch.partition;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.io.Files;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.batch.api.chunk.ItemWriter;
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
public class BatchStringWriter2 extends AbstractItemWriter {

    @Inject
    @BatchProperty
    private String fileName;

    @Override
    public void writeItems(List<Object> list) throws Exception {

        Files.append( Joiner.on(" ").join(list),
                      new File(fileName)       ,
                      Charsets.UTF_8            );
    }
}
