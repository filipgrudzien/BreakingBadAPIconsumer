package com.bbrestconsumer.entities;

import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class QuoteHelper {

    @Min(value = 1, message = "Value is lower than 1!")
    @Max(value = 200, message = "Value is over 200!")
    public int quoteNumber = 1;

    @Min(value = 5, message = "Value is lower than 5!")
    @Max(value = 15, message = "Value is over 15!")
    public int elemPerPage = 5;

    public int initPage = 1;

    public boolean correctParameters = true;

    public QuoteHelper() {};
}
