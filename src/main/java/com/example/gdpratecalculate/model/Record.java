package com.example.gdpratecalculate.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Record {
    @CsvBindByName(column = "Country Name")
    String countryName;
    @CsvBindByName(column = "Country Code")
    String countryCode;
    @CsvBindByName(column = "Year")
    int year;
    @CsvBindByName(column = "Value")
    double value;
}
