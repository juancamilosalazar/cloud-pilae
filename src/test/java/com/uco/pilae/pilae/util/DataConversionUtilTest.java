package com.uco.pilae.pilae.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Logger;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class DataConversionUtilTest {

    private DataConversionUtil base;

    private org.slf4j.Logger logger;

    @Before
    public void before(){
        base= new DataConversionUtil(new ObjectMapper());
        logger = LoggerFactory.getLogger(DataConversionUtil.class);
    }

    @Test
    public void exception() throws Exception{
        try {
            base.dtoToJson("dfasdf");
        }catch (Exception ex){
            assertEquals(ex.getMessage(),"error");
        }

    }
}