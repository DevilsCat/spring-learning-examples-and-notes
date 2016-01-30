package com.yu.spring.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("offersDao")
@Transactional
@SuppressWarnings("unchecked")
public class OffersDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * This method create a anonymous {@link RowMapper} specify the routine of
     * generating Offer Object given retrieved database query result.
     * 
     * @return
     */
    public List<Offer> getOffers() {
        Criteria crit = session().createCriteria(Offer.class);
        crit.createAlias("user", "u");
        crit.add(Restrictions.eq("u.enabled", true));
        return crit.list();
    }
   
    public List<Offer> getOffers(String username) {
        Criteria crit = session().createCriteria(Offer.class);
        crit.createAlias("user", "u");
        crit.add(Restrictions.eq("u.enabled", true));
        crit.add(Restrictions.eq("u.username", username));
        return crit.list();
    }

    /**
     * Save or update an {@link Offer} entry to database.
     * 
     * @param offer
     * @return
     */
    public void saveOrUpdate(Offer offer) {
        session().saveOrUpdate(offer);
    }

    /**
     * Delete a given id from database.
     * 
     * @param id
     * @return Number of row affected
     */
    public boolean delete(int id) {
        Query query = session().createQuery("delete from Offer where id=:id");
        query.setLong("id", id);
        return query.executeUpdate() == 1;
    }

    public Offer getOffer(int id) {
        Criteria crit = session().createCriteria(Offer.class);
        
        crit.createAlias("user", "u");
        
        crit.add(Restrictions.eq("u.enabled", true));
        crit.add(Restrictions.idEq(id));
        
        return (Offer) crit.uniqueResult();
    }
}
