package com.bbrestconsumer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/quotes")
public class BreakingBadController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("result")
    public String showResultsFromAPI(Model model){
        return "api-result";
    }

}
