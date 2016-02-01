package com.yu.spring.web.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yu.spring.web.dao.Message;
import com.yu.spring.web.service.UsersService;

@Controller
public class MessageController {
    
    @Autowired
    UsersService usersService;
    
    @Autowired
    MailSender mailSender;
    
    /**
     * Create a message json server.
     * @param principal
     * @return
     */
    @RequestMapping(value="/getmessages", method=RequestMethod.GET, produces="application/json")
    @ResponseBody
    public Map<String, Object> getMessages(Principal principal) {
        List<Message> messages = null;
        if (principal == null) {
            messages = new ArrayList<Message>();
        } else {
            String username = principal.getName();
            messages = usersService.getMessages(username);
        }
        
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("messages", messages);
        data.put("number", messages.size());
        
        return data;
    }
    
    @RequestMapping(value="/sendmessage", method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> sendMessage(Principal principal, @RequestBody Map<String, Object> data) {
        
        String text = (String)data.get("text");
        String name = (String)data.get("name");
        String email = (String)data.get("email");
        
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("yu.sendemail@gmail.com");
        mail.setTo(email);
        mail.setSubject("Re: " + name + ", your message");
        mail.setText(text);
        
        Map<String, Object> rval = new HashMap<String, Object>();
        
        try {
            mailSender.send(mail);
            rval.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            rval.put("success", false);
        }
        
        return rval;
    }
    
    @RequestMapping("/messages")
    public String showMessages() {
        return "messages";
    }
}
