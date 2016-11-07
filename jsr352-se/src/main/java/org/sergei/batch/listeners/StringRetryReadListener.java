package org.sergei.batch.listeners;

import javax.batch.api.chunk.listener.RetryReadListener;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class StringRetryReadListener implements RetryReadListener {

    @Override
    public void onRetryReadException(Exception ex) throws Exception {
        System.out.println( "----------------------------------------------------------------" );
        System.out.println("StringRetryReadListener.onRetryReadException " + ex.getMessage());
        System.out.println( "----------------------------------------------------------------" );
    }
}
