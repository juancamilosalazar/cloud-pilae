package com.uco.pilae.pilae.model;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.MarcadorEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class MarcadorTest {

    Marcador marcador;

    @Before
    public void before(){
        marcador = new Marcador(ThreadLocalRandom.current().nextLong(0,50)
                ,"asdfas",ThreadLocalRandom.current().nextInt(1,5)
                ,ThreadLocalRandom.current().nextInt(1,5)
                , TestDataProviderController.buildPartido());
    }

    @Test
    public void constructorAllArgs(){
        assertNotNull(marcador);
    }

}