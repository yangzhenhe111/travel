package com.cy.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class TestController {

    @GetMapping("/hello")
    public String helle(){
        return "HelloWorld!";
    }
}
