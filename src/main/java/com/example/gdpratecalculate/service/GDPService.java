package com.example.gdpratecalculate.service;

import com.example.gdpratecalculate.model.Alpha;
import com.example.gdpratecalculate.model.Message;
import com.example.gdpratecalculate.model.Record;
import com.example.gdpratecalculate.util.Alpha2ToAlpha3Convertor;
import com.example.gdpratecalculate.util.CSVToObjectConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GDPService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GDPService.class);


    /**
     * calculate gdp rate based on inputs
     */
    public Message calculateGDPRate(String code, int year) {
        if (code.length() == 2) {
            List<Alpha> convertList = Alpha2ToAlpha3Convertor.convert(code);
            if (!convertList.isEmpty()) {
                code = convertList.get(0).getAlphaThree();
            }
        }
        int previousYear = year - 1;
        List<Record> records = CSVToObjectConvertor.readDataFromCSV();
        String finalCode = code;
        List<Record> filterRecords = records.stream()
                .filter(record -> finalCode.equals(record.getCountryCode()))
                .filter(record -> year == record.getYear() || previousYear == record.getYear())
                .collect(Collectors.toList());

        Message message = new Message();
        //real GDP rate calculation
        if (!filterRecords.isEmpty()) {
            Optional<Record> thisYearOpValue = filterRecords.stream().filter(record -> year == record.getYear()).findFirst();
            Optional<Record> lastYearOpValue = filterRecords.stream().filter(record -> previousYear == record.getYear()).findFirst();
            double thisYearValue = thisYearOpValue.get().getValue();
            double lastYearValue = lastYearOpValue.get().getValue();
            double gdpRate = ((thisYearValue / lastYearValue) - 1) * 100;

            message.setGdpRate(gdpRate);
            message.setMessage("GDP rate of year : " + year);
            return message;
        }
        return message;
    }
}
