package org.sergei.batch.listeners;

import javax.batch.api.chunk.listener.SkipReadListener;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class StringSkipReadListener implements SkipReadListener {

    @Override
    public void onSkipReadItem(Exception e) throws Exception {
        System.out.println( "----------------------------------------------------------------" );
        System.out.println("StringSkipReadListener.onSkipReadItem: " + e.getMessage());
        System.out.println( "----------------------------------------------------------------" );
    }
}
