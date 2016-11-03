package org.sergei.batch.impl;

import org.sergei.business.Worker;
import org.sergei.business.impl.FunnyWorker;
import org.sergei.batch.LaunchService;
import org.sergei.batch.NotificationService;

/**
 * Created by Sergei_Doroshenko on 10/19/2016.
 */
public class LaunchServiceImpl implements LaunchService {

    private NotificationService notificationService;

    @Override
    public void processWorkerLaunchRequest( String workerName ) {
        Worker worker = new FunnyWorker( workerName );
        notificationService.register( worker );
    }

    @Override
    public void cancelWorker( String workerName ) {
        Worker worker = new FunnyWorker( workerName );
        notificationService.cancel( worker );
    }

    public void setNotificationService( NotificationService notificationService ) {
        this.notificationService = notificationService;
    }
}
