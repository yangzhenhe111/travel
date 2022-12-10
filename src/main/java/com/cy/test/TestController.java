package com.cy.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@Controller
public class TestController {

    @GetMapping("/hello")
    public String helle(){
        return "HelloWorld!";
    }
}
