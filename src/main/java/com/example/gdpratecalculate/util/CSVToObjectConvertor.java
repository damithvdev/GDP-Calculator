package com.example.gdpratecalculate.util;

import com.example.gdpratecalculate.model.Record;
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

public class CSVToObjectConvertor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CSVToObjectConvertor.class);

    /**
     * loading csv records and pass the values
     *
     * @return records
     */
    public static List<Record> readDataFromCSV() {
        HeaderColumnNameMappingStrategy<Record> ms = new HeaderColumnNameMappingStrategy<>();
        ms.setType(Record.class);
        List<Record> recordList = new ArrayList<>();

        Reader reader;
        try {
            reader = Files.newBufferedReader(namedColumnCsvPath());
            CsvToBean cb = new CsvToBeanBuilder(reader)
                    .withType(Record.class)
                    .withMappingStrategy(ms)
                    .build();
            recordList = cb.parse();
            reader.close();
        } catch (IOException | URISyntaxException e) {
            LOGGER.error("Error occurred while processing data", e);
        }
        return recordList;
    }

    public static Path namedColumnCsvPath() throws URISyntaxException {
        URI uri = ClassLoader.getSystemResource("gdp-data").toURI();
        return Paths.get(uri);
    }
}
