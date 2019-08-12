package com.uco.pilae.pilae.entity;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class PartidoEntityTest {

    PartidoEntity partidoEntity;

    @Before
    public void before(){
        partidoEntity = new PartidoEntity(ThreadLocalRandom.current().nextLong(0,50)
                ,TestDataProviderController.buildDateRandom(915166800000L)
                ,"fasdfas","swfag","fdsfadf"
                ,TestDataProviderController.buildEquipoEntity()
                ,TestDataProviderController.buildEquipoEntity()
                ,TestDataProviderController.buildTorneoEntity());
    }

    @Test
    public void constructorAllArgs(){
        assertNotNull(partidoEntity);
    }

}