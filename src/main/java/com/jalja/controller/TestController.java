package com.jalja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value ="/test")
public class TestController {

    @GetMapping(value = "/test")
    public String practice(){
        return "test/practice";
    }
}
