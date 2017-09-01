package com.yf.pet.controller;

import com.yf.pet.common.authentication.annotation.DisableAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @DisableAuth
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView  index() {
        return  new ModelAndView("index.html");
    }
}
