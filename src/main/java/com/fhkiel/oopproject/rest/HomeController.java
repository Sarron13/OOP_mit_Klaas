package com.fhkiel.oopproject.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Controller for serving index.html
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

}
