package org.sergei;

import org.sergei.batch.LaunchService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Sergei_Doroshenko on 10/19/2016.
 */
public class App {
    public static void main(String[] args) throws InterruptedException {

        ApplicationContext context = new ClassPathXmlApplicationContext("legacy-batch-context.xml");

        LaunchService launchService = (LaunchService) context.getBean( "launchService" );

        launchService.processWorkerLaunchRequest( "John" );
        launchService.processWorkerLaunchRequest( "Sara" );
        launchService.processWorkerLaunchRequest( "Karl" );

        Thread.sleep( 5000 );

        launchService.cancelWorker( "Sara" );

        System.out.println("*****************************************************************************************");
        System.out.println("****                             App COMPLETED!                                      ****");
        System.out.println("*****************************************************************************************");
    }
}
