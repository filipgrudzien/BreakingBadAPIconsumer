package com.bbrestconsumer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quotes")
public class BreakingBadController {

    @RequestMapping("result")
    public String showResultsFromAPI(Model model){
        return "api-result";
    }

}
