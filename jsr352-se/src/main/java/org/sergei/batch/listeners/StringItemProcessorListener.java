package org.sergei.batch.listeners;

import javax.batch.api.chunk.listener.AbstractItemProcessListener;
import javax.inject.Named;

/**
 * Created by Sergei_Doroshenko on 10/7/2016.
 */
public class StringItemProcessorListener extends AbstractItemProcessListener {

//    @Override
//    public void beforeProcess(Object item) throws Exception {
//        System.out.println("StringItemProcessorListener.beforeProcess: "
//                + item
//                + ", thread : " + Thread.currentThread().getName());
//    }

    @Override
    public void afterProcess(Object item, Object result) throws Exception {
        System.out.println("StringItemProcessorListener.afterProcess: "
                + item + ", "
                + result
                + ", thread : " + Thread.currentThread().getName() + '\n');
    }

    @Override
    public void onProcessError(Object item, Exception ex) throws Exception {
        System.out.println("StringItemProcessorListener.onProcessError: "
                + item + ", "
                + ex.getLocalizedMessage()
                + ", thread : " + Thread.currentThread().getName() );
    }
}
