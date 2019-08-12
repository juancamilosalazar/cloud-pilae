package com.uco.pilae.pilae.model;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.PartidoEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class PartidoTest {


    Partido partido;

    @Before
    public void before(){
        partido = new Partido(ThreadLocalRandom.current().nextLong(0,50)
                ,TestDataProviderController.buildEquipo()
                ,TestDataProviderController.buildEquipo()
                ,"fasdfas"
                , TestDataProviderController.buildLongDateRandom(915166800000L)
                ,"swfag","fdsfadf"
                ,TestDataProviderController.buildTorneo());
    }

    @Test
    public void constructorAllArgs(){
        assertNotNull(partido);
    }

}