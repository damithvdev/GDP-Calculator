package com.example.gdpratecalculate.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Alpha {
    //Country,Alpha-2 code,Alpha-3 code,Numeric
    @CsvBindByName(column = "Country")
    String country;
    @CsvBindByName(column = "Alpha-2 code")
    String alphaTwo;
    @CsvBindByName(column = "Alpha-3 code")
    String alphaThree;
    @CsvBindByName(column = "Numeric")
    int numeric;
}
