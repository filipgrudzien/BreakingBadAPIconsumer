package com.bbrestconsumer.services;

import com.bbrestconsumer.entities.BreakingBadQuote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class BreakingBadService {

    private final String basicURL = "https://breaking-bad-quotes.herokuapp.com/v1/quotes/";

    private List<BreakingBadQuote> listForPagination;

    public String obtainDesiredURL(int quoteNumber) {
        return (basicURL + Integer.toString(quoteNumber));
    }

    public void retrieveRestData(RestTemplate restTemplate, int quoteNumber) {
        String urlForRest = obtainDesiredURL(quoteNumber);
        BreakingBadQuote[] response = restTemplate.getForObject(urlForRest, BreakingBadQuote[].class);
        ArrayList<BreakingBadQuote> arrayList = new ArrayList<BreakingBadQuote>(Arrays.asList(response));
        listForPagination = arrayList;
    }

    public Page<BreakingBadQuote> getPaginatedQuotes(Pageable pageable){
        return new PageImpl<BreakingBadQuote>(listForPagination, pageable, listForPagination.size());
    }

    public boolean checkIfDataRetrieved(){
        if(listForPagination != null ){
            return true;
        }else{
            return false;
        }
    }

}
