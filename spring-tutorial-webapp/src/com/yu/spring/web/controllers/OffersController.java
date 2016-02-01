package com.yu.spring.web.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yu.spring.web.dao.FormValidationGroup;
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
    
    @RequestMapping("/createoffer")
    public String createOffer(Model model, Principal principal) {
        
        Offer offer = null;
        
        if (principal != null) {
            String username = principal.getName();
            offer = offersService.getOffer(username);
        }
        
        if (offer == null) {
            offer = new Offer();
        }
        
        model.addAttribute("offer", offer);
        
        return "createoffer";
    }
    
    @RequestMapping(value="/docreate", method=RequestMethod.POST)
    public String doCreate(Model model, @Validated(value=FormValidationGroup.class) Offer offer, BindingResult result, 
            Principal principal, @RequestParam(value="delete", required=false)String delete) {
        if (result.hasErrors()) {
            return "createoffer";
        }
        
        if (delete == null) {
            String username = principal.getName();
            offer.getUser().setUsername(username);
            offersService.saveOrUpdate(offer);
            return "offercreated";
        } else {
            offersService.delete(offer.getId());
            return "offerdeleted";
        }
           
    }
    
}
