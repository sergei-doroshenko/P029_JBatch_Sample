package org.sergei.batch;

import org.sergei.business.Worker;

/**
 * Created by Sergei_Doroshenko on 10/19/2016.
 */
public interface NotificationService {
    void register(Worker worker);

    void cancel(Worker worker);
}
