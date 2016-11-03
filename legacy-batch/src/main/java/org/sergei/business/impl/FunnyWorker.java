package org.sergei.business.impl;

import org.sergei.business.Worker;

/**
 * Created by Sergei_Doroshenko on 10/19/2016.
 */
public class FunnyWorker implements Worker {

    private final String name;

    public FunnyWorker(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void perform(String message) {
        System.out.println( message );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FunnyWorker that = (FunnyWorker) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
