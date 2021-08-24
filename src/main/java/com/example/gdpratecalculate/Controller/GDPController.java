package com.example.gdpratecalculate.Controller;

import com.example.gdpratecalculate.model.Message;
import com.example.gdpratecalculate.service.GDPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GDPController {
    @Autowired
    GDPService service;

    //write a public method which returns gdp rate of a given year of country
    @GetMapping("/gdp-rate/{code}/{year}")
    public Message getGDPRate(@PathVariable(value = "code") String countryCode, @PathVariable(value = "year") int year) {

        return service.calculateGDPRate(countryCode, year);

    }

}
