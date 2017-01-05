package org.sergei.batch.tx.dao;

import org.sergei.batch.tx.domain.Customer;

import java.util.List;

/**
 * Created by Sergei_Doroshenko on 12/22/2016.
 */
public interface CustomerDao {

    List<Customer> findAll();
}
