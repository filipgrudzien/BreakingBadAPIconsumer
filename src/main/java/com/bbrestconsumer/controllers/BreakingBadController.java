package com.bbrestconsumer.controllers;

import com.bbrestconsumer.services.BreakingBadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/quotes")
public class BreakingBadController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BreakingBadService breakingBadService;

    /*@RequestMapping(value = "/result/{quoteNumber}")
    public String showResultsFromAPI(@PathVariable int quoteNumber, Model model){
        breakingBadService.processRestResponse(restTemplate, quoteNumber);
        return "redirect:/";
    }*/

    @RequestMapping(value = "/result/")
    public String showResultsFromAPI(@RequestParam(value="quoteNumber") int quoteNumber, Model model){
        breakingBadService.processRestResponse(restTemplate, quoteNumber);
        return "redirect:/";
    }

}
