package com.uco.pilae.pilae.model;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.PosicionEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class PosicionTest {

    Posicion posicion;

    @Before
    public void before(){
        posicion = new Posicion(ThreadLocalRandom.current().nextLong(0,50)
                ,ThreadLocalRandom.current().nextInt(0,5)
                ,ThreadLocalRandom.current().nextInt(0,5)
                ,ThreadLocalRandom.current().nextInt(0,5)
                ,ThreadLocalRandom.current().nextInt(0,5)
                ,ThreadLocalRandom.current().nextInt(0,5)
                ,ThreadLocalRandom.current().nextInt(0,5)
                ,ThreadLocalRandom.current().nextInt(0,5)
                ,ThreadLocalRandom.current().nextInt(0,5)
                , TestDataProviderController.buildEquipo()
                ,TestDataProviderController.buildTorneo());
    }

    @Test
    public void constructorAllArgs(){
        assertNotNull(posicion);
    }

    @Test
    public void init(){
        Posicion posicionInit = TestDataProviderController.builposicion();
        assertNotNull(posicionInit);
    }
}