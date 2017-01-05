package org.sergei.batch.tx;

import javax.batch.api.chunk.ItemWriter;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Sergei_Doroshenko on 12/22/2016.
 */
public class MyWriter implements ItemWriter {
    @Override
    public void open ( Serializable checkpoint ) throws Exception {

    }

    @Override
    public void close () throws Exception {

    }

    @Override
    public void writeItems ( List<Object> items ) throws Exception {

    }

    @Override
    public Serializable checkpointInfo () throws Exception {
        return null;
    }
}
