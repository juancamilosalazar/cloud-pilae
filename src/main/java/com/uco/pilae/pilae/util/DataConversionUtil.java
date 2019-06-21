package com.uco.pilae.pilae.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataConversionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataConversionUtil.class);

    private final ObjectMapper objectMapper;

    @Autowired
    public DataConversionUtil(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> String dtoToJson(final T dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}
