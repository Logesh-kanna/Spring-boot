package com.practice.myfirstproject.WebApp.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

    @GetMapping("index")
    @ResponseBody
    public String index() {
        return "index";
    }

}
