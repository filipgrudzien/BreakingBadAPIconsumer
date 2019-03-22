package com.bbrestconsumer.services;

import com.bbrestconsumer.entities.BreakingBadQuote;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class BreakingBadService {

    private final String basicURL = "https://breaking-bad-quotes.herokuapp.com/v1/quotes/";

    private int pageSize = 0;

    private List<BreakingBadQuote> listForPagination;

    public String obtainDesiredURL(int quoteNumber) {
        return (basicURL + Integer.toString(quoteNumber));
    }

    public void retrieveRestData(RestTemplate restTemplate, int quoteNumber) {
        String urlForRest = obtainDesiredURL(quoteNumber);
        BreakingBadQuote[] response = restTemplate.getForObject(urlForRest, BreakingBadQuote[].class);
        ArrayList<BreakingBadQuote> arrayList = new ArrayList<BreakingBadQuote>(Arrays.asList(response));
        listForPagination = arrayList;
        //this.currentServiceStateLogger();
    }

    public Page<BreakingBadQuote> getPaginatedQuotes(int page) {

        PageRequest pageable = PageRequest.of(page, this.pageSize);
        int pageSize = this.pageSize;
        int currentPage = page;
        int startItem = currentPage * pageSize;
        List<BreakingBadQuote> list;

        if (listForPagination.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listForPagination.size());
            list = listForPagination.subList(startItem, toIndex);
        }

        Page<BreakingBadQuote> quotePage
                = new PageImpl<BreakingBadQuote>(list, PageRequest.of(currentPage, pageSize), listForPagination.size());

        return quotePage;
    }

    public boolean checkIfDataRetrieved() {
        if (listForPagination != null || !CollectionUtils.isEmpty(listForPagination)) {
            return true;
        } else {
            return false;
        }
    }

    public void resetPreviousSetup(){
        this.pageSize = 0;
        this.listForPagination = null;
    }

    public void setPageSize(int desiredPageSize){
        this.pageSize = desiredPageSize;
    }

    public boolean checkIfPageSizeIsSet(){
        if(this.pageSize == 0){
            return false;
        }else{
            return true;
        }
    }

    public void currentServiceStateLogger(){
        System.out.println("Current page size: " + this.pageSize);
        System.out.println("Current rest result size: " + this.listForPagination.size());

    }
}
