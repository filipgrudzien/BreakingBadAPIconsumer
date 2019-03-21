package com.bbrestconsumer.controllers;

import com.bbrestconsumer.entities.BreakingBadQuote;
import com.bbrestconsumer.entities.QuoteHelper;
import com.bbrestconsumer.services.BreakingBadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BreakingBadController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BreakingBadService breakingBadService;

    @RequestMapping(value = "/result/{page}")
    public ModelAndView showResultsFromAPI(@Valid QuoteHelper helper, Errors errors,
                                           @RequestParam(value="quoteNumber", required = false) Integer quoteNumber,
                                           @PathVariable int page,
                                           Model model){

        ModelAndView mav = new ModelAndView();

        int elemPerPage = 5; //hard-coded examle yet

        if(errors.hasErrors()){
            mav.setViewName("index");
            helper.setCorrectParameters(false);
            mav.addObject("quotehelper", helper);
            return mav;
        }

        if(!breakingBadService.checkIfDataRetrieved()){
            breakingBadService.retrieveRestData(restTemplate, quoteNumber);
        }

        PageRequest pageable = PageRequest.of(page - 1, elemPerPage);
        Page<BreakingBadQuote> quotePage = breakingBadService.getPaginatedQuotes(pageable, page-1, elemPerPage);

        int totalPages = quotePage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            mav.addObject("pageNumbers", pageNumbers);
        }

        mav.setViewName("results");
        mav.addObject("quotelist", quotePage.getContent());
        return mav;
    }

    /*@RequestMapping(value = "/result")
    public ModelAndView showResultsFromAPI(@Valid QuoteHelper helper, Errors errors,
                                           @RequestParam(value="quoteNumber") int quoteNumber,
                                           @RequestParam("page") int page, Model model){

        ModelAndView mav = new ModelAndView();

        if(errors.hasErrors()){
            mav.setViewName("index");
            helper.setCorrectParameters(false);
            mav.addObject("quotehelper", helper);
            return mav;
        }

        breakingBadService.retrieveRestData(restTemplate, quoteNumber);

        PageRequest pageable = PageRequest.of()

        int totalPages = articlePage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }

        mav.setViewName("results");
        mav.addObject("quotelist", articlePage.getContent());
        return mav;
    }*/


    /*@RequestMapping("/result")
    public ModelAndView listBooks(
            @Valid QuoteHelper helper, Errors errors, @RequestParam(value="quoteNumber") int quoteNumber,
            @RequestParam("page") int page, Model model) {

        ModelAndView mav = new ModelAndView();

        if(errors.hasErrors()){
            mav.setViewName("index");
            helper.setCheckIfCorrect(false);
            mav.addObject("quotehelper", helper);
            return mav;
        }

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        breakingBadService.retrieveRestData(restTemplate, quoteNumber);

        Page<BreakingBadQuote> quotePage = breakingBadService.findPaginatedPage(PageRequest.of(currentPage - 1, pageSize));

        mav.addObject("quotePage", quotePage.getContent());

        int totalPages = quotePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            mav.addObject("pageNumbers", pageNumbers);
        }

        mav.setViewName("results");
        return mav;
    }*/

}
