package com.bbrestconsumer.controllers;

import com.bbrestconsumer.entities.QuoteHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    /*@Autowired
    private QuoteHelper quoteHelper;*/

    @RequestMapping("/")
    public String showMainPage(Model model){
        model.addAttribute("quotehelper", new QuoteHelper());
        return "index";
    }
}
