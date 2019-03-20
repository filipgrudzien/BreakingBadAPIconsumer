package com.bbrestconsumer.entities;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class QuoteHelper {

    @Min(1)
    @Max(200)
    public int quoteNumber = 1;

    public QuoteHelper() {};
}
