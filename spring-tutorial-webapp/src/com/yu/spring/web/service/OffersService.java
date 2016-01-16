package com.yu.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}