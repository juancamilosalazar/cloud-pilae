package com.uco.pilae.pilae.entity;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class TorneoEntityTest {

    TorneoEntity torneoEntity;

    @Before
    public void before(){
        torneoEntity = new TorneoEntity(ThreadLocalRandom.current().nextLong(0,50)
                ,"dasfasdf","fshshsb",TestDataProviderController.buildDeporteEntity());
    }

    @Test
    public void constructorAllArgs(){
        assertNotNull(torneoEntity);
    }

}