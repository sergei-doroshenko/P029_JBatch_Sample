package org.sergei.batch.listeners;

import javax.batch.api.chunk.listener.AbstractItemWriteListener;
import java.util.List;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class StringItemWriteListener extends AbstractItemWriteListener {

//    @Override
//    public void beforeWrite(List items) throws Exception {
//        System.out.println("StringItemWriteListener.beforeWrite: " + items + ", thread : " + Thread.currentThread().getName());
//    }

    @Override
    public void afterWrite(List items) throws Exception {
        System.out.println("StringItemWriteListener.afterWrite: " + items + ", thread : " + Thread.currentThread().getName() + '\n');
    }

    @Override
    public void onWriteError(List items, Exception ex) throws Exception {
        System.out.println("StringItemWriteListener.onError: " + items + ", " + ex.getLocalizedMessage() + ", thread : " + Thread.currentThread().getName());
    }
}
