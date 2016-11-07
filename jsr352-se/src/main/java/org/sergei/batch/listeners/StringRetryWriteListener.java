package org.sergei.batch.listeners;

import javax.batch.api.chunk.listener.RetryWriteListener;
import java.util.List;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class StringRetryWriteListener implements RetryWriteListener {

    @Override
    public void onRetryWriteException(List<Object> items, Exception ex) throws Exception {
        System.out.println( "----------------------------------------------------------------" );
        System.out.println("StringRetryWriteListener.onRetryWriteException");
        System.out.println( "----------------------------------------------------------------" );
    }
}
