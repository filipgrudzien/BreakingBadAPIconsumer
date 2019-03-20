package com.bbrestconsumer.entities;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class QuoteHelper {

    @Min(1)
    @Max(200)
    @NotEmpty(message = "Number of quotes cannot be blank!")
    public int quoteNumber = 1;

    public QuoteHelper() {};
}
