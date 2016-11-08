package org.sergei.batch;

import javax.batch.api.chunk.ItemProcessor;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class StringProcessor implements ItemProcessor {

    private StringBuffer buffer = new StringBuffer();
    private String temp;

    @Override
    public Object processItem(Object o) throws Exception {
        if( o == null ) {
            return null;
        }

        buffer.setLength(0);
        buffer.append( o );

        return buffer.reverse().toString();
    }
}
