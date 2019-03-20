package com.bbrestconsumer.controllers;

import com.bbrestconsumer.entities.BreakingBadQuote;
import com.bbrestconsumer.entities.QuoteHelper;
import com.bbrestconsumer.services.BreakingBadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BreakingBadController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BreakingBadService breakingBadService;

    @RequestMapping(value = "/result")
    public ModelAndView showResultsFromAPI(@Valid QuoteHelper helper, Errors errors, @RequestParam(value="quoteNumber") int quoteNumber, Model model){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");

        if(errors.hasErrors()){
            helper.setCheckIfCorrect(false);
            List<BreakingBadQuote> blankList = new ArrayList<>();
            model.addAttribute("quotelist", blankList);
            mav.addObject("quotehelper", helper);
            return mav;
        }

        mav.addObject("quotelist", breakingBadService.processRestResponse(restTemplate, quoteNumber));
        mav.addObject("quotehelper", new QuoteHelper());
        return mav;
    }

}
