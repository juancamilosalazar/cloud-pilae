package com.uco.pilae.pilae.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
public class DataConversionUtilTest {

    private DataConversionUtil base;

    private org.slf4j.Logger logger;
    @Mock
    ObjectMapper objectMapper;


    @Before
    public void before() throws JsonProcessingException {
        base = new DataConversionUtil(objectMapper);
        logger = LoggerFactory.getLogger(DataConversionUtil.class);
    }

    @Test
    public void exception() throws Exception {
        Mockito.when(objectMapper.writeValueAsString(any())).thenThrow(JsonProcessingException.class);
        String asd = base.dtoToJson("safdas");
        assertNull(asd);
    }
}