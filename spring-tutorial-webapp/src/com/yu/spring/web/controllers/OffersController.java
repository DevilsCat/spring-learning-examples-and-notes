package com.yu.spring.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        
        List<Offer> offers = offersService.getCurrent();
        
        model.addAttribute("offers", offers);
        return "offers";
    }
    
    @RequestMapping("/createoffer")
    public String createOffer(Model model) {
        return "createoffer";
    }
    
    @RequestMapping(value="/docreate", method=RequestMethod.POST)
    public String doCreate(Model model, Offer offer) {
        System.out.println(offer);
        return "offercreated";
    }
}
