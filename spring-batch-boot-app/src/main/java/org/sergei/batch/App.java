package org.sergei.batch;

import org.springframework.boot.SpringApplication;

/**
 * Created by Sergei_Doroshenko on 11/9/2016.
 */
public class App {
    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(
                BatchConfiguration.class, args)));
    }
}
