package com.bbrestconsumer.controllers;

import com.bbrestconsumer.entities.QuoteHelper;
import com.bbrestconsumer.services.BreakingBadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Controller
public class BreakingBadController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BreakingBadService breakingBadService;

    @RequestMapping(value = "/result")
    public String showResultsFromAPI(@Valid QuoteHelper helper, Errors errors, @RequestParam(value="quoteNumber") int quoteNumber, Model model){

        if(errors.hasErrors()){
            return "redirect:/";
        }

        breakingBadService.processRestResponse(restTemplate, quoteNumber);
        return "redirect:/";
    }

}
