package org.sergei.batch.tx;

import javax.batch.api.chunk.ItemReader;
import java.io.Serializable;

/**
 * Created by Sergei_Doroshenko on 12/22/2016.
 */
public class MyReader implements ItemReader {
    @Override
    public void open ( Serializable checkpoint ) throws Exception {

    }

    @Override
    public void close () throws Exception {

    }

    @Override
    public Object readItem () throws Exception {
        return null;
    }

    @Override
    public Serializable checkpointInfo () throws Exception {
        return null;
    }
}
