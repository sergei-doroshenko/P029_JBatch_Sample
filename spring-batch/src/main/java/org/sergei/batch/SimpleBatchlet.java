package org.sergei.batch;

import javax.batch.api.AbstractBatchlet;

public class SimpleBatchlet extends AbstractBatchlet {

    @Override
    public String process() throws Exception {

        System.out.println("Hello, JBatch");
        return null;
    }
}
