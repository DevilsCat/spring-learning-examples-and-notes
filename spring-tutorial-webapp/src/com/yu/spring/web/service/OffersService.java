package com.yu.spring.web.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.yu.spring.web.dao.Offer;
import com.yu.spring.web.dao.OffersDAO;

/**
 * A class provide service coordinating DAO
 * It eases the logic of controller.
 * @author xiaoy
 *
 */
@Service("offerService")
public class OffersService {
    
    private OffersDAO offersDao;
    
    @Autowired
    public void setOffersDao(OffersDAO offersDao) {
        this.offersDao = offersDao;
    }

    public List<Offer> getCurrent() {
        return offersDao.getOffers();
    }
    
    public Offer getOfferById(int id) {
        return offersDao.getOffer(id);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void create(Offer offer) {
        offersDao.create(offer);
    }

    /**
     * Make Database access half percentage possibility down.
     * Used to test Exception handling.
     */
    public void throwTestException() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            offersDao.getOffer(-1);
        }
    }

    public boolean hasOffer(String name) {
        if (name == null) return false;
        
        List<Offer> offers = offersDao.getOffers(name);
        
        if (offers.size() == 0) return false;
        
        return true;
    }

    public Offer getOffer(String username) {
        if (username == null) {
            return null;
        }
        
        List<Offer> offers = offersDao.getOffers(username);
        
        if (offers.size() == 0) {
            return null;
        }
        
        return offers.get(0);
    }

    public void saveOrUpdate(Offer offer) {
        if (offer.getId() != 0) {
            offersDao.update(offer);
        } else {
            offersDao.create(offer);
        }
    }

    public void delete(int id) {
        offersDao.delete(id);
    }
}
