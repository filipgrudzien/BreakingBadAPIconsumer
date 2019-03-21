package com.bbrestconsumer.services;

import com.bbrestconsumer.entities.BreakingBadQuote;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BreakingBadService {

    private final String basicURL = "https://breaking-bad-quotes.herokuapp.com/v1/quotes/";

    public String obtainDesiredURL(int quoteNumber){
        return (basicURL+Integer.toString(quoteNumber));
    }

    public List<BreakingBadQuote> processRestResponse(RestTemplate restTemplate, int quoteNumber){
        String urlForRest = obtainDesiredURL(quoteNumber);
        BreakingBadQuote[] response = restTemplate.getForObject(urlForRest, BreakingBadQuote[].class);
        ArrayList<BreakingBadQuote> arrayList = new ArrayList<BreakingBadQuote>(Arrays.asList(response));
        return arrayList;
    }

}
