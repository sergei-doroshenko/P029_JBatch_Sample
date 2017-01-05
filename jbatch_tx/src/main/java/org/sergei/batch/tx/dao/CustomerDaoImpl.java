package org.sergei.batch.tx.dao;

import org.sergei.batch.tx.domain.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Sergei_Doroshenko on 12/22/2016.
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Customer> findAll () {
//        Query query = em.createQuery("from Customer as c where p.category = :category");
        Query query = em.createQuery("from Customer as c");
//        query.setParameter("category", category);
        return query.getResultList();
    }
}
