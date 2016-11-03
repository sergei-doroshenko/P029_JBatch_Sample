package org.sergei.batch;

/**
 * Created by Sergei_Doroshenko on 10/19/2016.
 */
public interface LaunchService {
    void processWorkerLaunchRequest( String workerName );

    void cancelWorker( String workerName );
}
