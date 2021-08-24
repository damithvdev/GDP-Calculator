package com.example.gdpratecalculate.util;

import com.example.gdpratecalculate.model.Alpha;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Alpha2ToAlpha3Convertor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Alpha2ToAlpha3Convertor.class);

    public static List<Alpha> convert(String code) {
        HeaderColumnNameMappingStrategy<Alpha> ms = new HeaderColumnNameMappingStrategy<>();
        ms.setType(Alpha.class);
        List<Alpha> recordList = new ArrayList<>();

        Reader reader = null;
        try {
            reader = Files.newBufferedReader(namedColumnCsvPath());
            CsvToBean cb = new CsvToBeanBuilder(reader)
                    .withType(Alpha.class)
                    .withMappingStrategy(ms)
                    .build();
            recordList = cb.parse();
            reader.close();
            return recordList.stream().filter(alpha -> code.equals(alpha.getAlphaTwo())).collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            LOGGER.error("Error occurred while processing data", e);
        }
        return recordList;
    }

    public static Path namedColumnCsvPath() throws URISyntaxException {
        URI uri = ClassLoader.getSystemResource("alfa2").toURI();
        return Paths.get(uri);
    }
}
