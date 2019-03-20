package com.bbrestconsumer.entities;

import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class QuoteHelper {

    @Min(value = 1, message = "Value is lower than 1!")
    @Max(value = 200, message = "Value is over 200!")
    public int quoteNumber = 1;

    public boolean checkIfCorrect = true;

    public QuoteHelper() {};
}
