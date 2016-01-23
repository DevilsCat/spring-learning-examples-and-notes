package com.yu.spring.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yu.spring.web.dao.Offer;
import com.yu.spring.web.service.OffersService;

/**
 * Controller responses to offers request.
 * It asks offers service to retrieve the data and,
 * set offers to model which is used to be rendered.
 * At last it returns the corresponding view.
 * @author xiaoy
 *
 */
@Controller
public class OffersController {
    private OffersService offersService;
    
    @Autowired
    public void setOffersService(OffersService offersService) {
        this.offersService = offersService;
    }
    
    @RequestMapping("/offers")
    public String showOffers(Model model) {
        
        offersService.throwTestException();
        
        List<Offer> offers = offersService.getCurrent();
        
        model.addAttribute("offers", offers);
        return "offers";
    }
    
    @RequestMapping("/createoffer")
    public String createOffer(Model model) {
        
        model.addAttribute("offer", new Offer());
        
        return "createoffer";
    }
    
    @RequestMapping(value="/docreate", method=RequestMethod.POST)
    public String doCreate(Model model, @Valid Offer offer, BindingResult result) {
        if (result.hasErrors()) {
            return "createoffer";
        }
        
        offersService.create(offer);
        
        return "offercreated";
    }
    
    
    
    /**
     * Handles all {@link DataAccessException} exceptions.
     * @param e
     * @return
     */
    /*@ExceptionHandler(DataAccessException.class)
    public String handleDatabaseException(DataAccessException e) {
        return "error";
    }*/
}
