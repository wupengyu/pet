package com.yf.pet.web.controller;

import com.yf.pet.web.common.annotation.DisableAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    @DisableAuth
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String  hello() {
        return "hello";
    }
}
