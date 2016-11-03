package org.sergei.batch.impl;

import org.sergei.business.Worker;
import org.sergei.batch.NotificationService;
import org.springframework.beans.factory.InitializingBean;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sergei_Doroshenko on 10/19/2016.
 */
public class NotificationServiceImpl implements NotificationService, InitializingBean {

    private Set<Worker> workers = new HashSet<>();
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool( 1 );

    @Override
    public void afterPropertiesSet() throws Exception {
        Runnable myRunnable = new Runnable() {
            public void run() {
                performNotification();
                schedule( this );
            }
        };

        schedule( myRunnable );
    }

    public void register(Worker worker) {
        synchronized ( workers ) {
            if ( workers.contains( worker ) ) {
                throw new RuntimeException( "Worker is already registered." );
            }

            workers.add( worker );
            System.out.println( createMassage( worker.getName(), " REGISTERED!") );
        }
    }

    @Override
    public void cancel(Worker worker) {
        synchronized ( workers ) {
            workers.remove( worker );
            System.out.println( createMassage( worker.getName(), "'s work CANCELED!") );
        }
    }

    private void performNotification() {
        synchronized ( workers ) {
            for ( Worker worker : workers ) {
                worker.perform( createMassage( worker.getName(), " is working...") );
            }

            System.out.println();
        }
    }

    private void schedule( Runnable runnable ) {
        executorService.schedule( runnable, 3000L, TimeUnit.MILLISECONDS );
    }

    private String createMassage( String workerName, String payload ) {
        return "Service notification: " +
                LocalTime.now().format( DateTimeFormatter.ofPattern("HH:mm:ss") ) +
                ", " +
                workerName +
                payload;
    }
}
