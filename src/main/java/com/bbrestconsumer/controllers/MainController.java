package com.bbrestconsumer.controllers;

import com.bbrestconsumer.entities.BreakingBadQuote;
import com.bbrestconsumer.entities.QuoteHelper;
import com.bbrestconsumer.services.BreakingBadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private QuoteHelper quoteHelper;

    @Autowired
    private BreakingBadService breakingBadService;

    @RequestMapping("/")
    public String showMainPage(Model model){

        List<BreakingBadQuote> blankList = new ArrayList<>();
        model.addAttribute("quotelist", blankList);
        model.addAttribute("quotehelper", new QuoteHelper());
        breakingBadService.resetPreviousSetup();
        return "index";
    }
}
