package com.yu.spring.web.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView showHome() {
        // Put stuff in the model, and then tomcat render the jsp can
        // use these data.
        ModelAndView mv = new ModelAndView("home");
        Map<String, Object> model = mv.getModel();

        model.put("name", "<b>Anqi</b>");  // This value will be put into Request.

        return mv;
    }
}
