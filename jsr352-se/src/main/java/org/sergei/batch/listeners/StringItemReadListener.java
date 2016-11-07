package org.sergei.batch.listeners;

import javax.batch.api.chunk.listener.AbstractItemReadListener;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class StringItemReadListener extends AbstractItemReadListener {

    @Override
    public void beforeRead() throws Exception {
        System.out.println("StringItemReadListener.beforeRead" + ", thread : " + Thread.currentThread().getName());
    }

    @Override
    public void afterRead(Object item) throws Exception {
        System.out.println("StringItemReadListener.afterRead: " + item + ", thread : " + Thread.currentThread().getName());
    }

    @Override
    public void onReadError(Exception ex) throws Exception {
        System.out.println("StringItemReadListener.onReadError: " + ex.getLocalizedMessage() + ", thread : " + Thread.currentThread().getName());
    }
}
