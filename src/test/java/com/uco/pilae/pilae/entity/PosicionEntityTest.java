package com.uco.pilae.pilae.entity;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.model.Posicion;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class PosicionEntityTest {

    PosicionEntity posicionEntity;

    @Before
    public void before(){
        posicionEntity = new PosicionEntity(ThreadLocalRandom.current().nextLong(0,50)
                ,ThreadLocalRandom.current().nextInt(0,5)
                ,ThreadLocalRandom.current().nextInt(0,5)
                ,ThreadLocalRandom.current().nextInt(0,5)
                ,ThreadLocalRandom.current().nextInt(0,5)
                ,ThreadLocalRandom.current().nextInt(0,5)
                ,ThreadLocalRandom.current().nextInt(0,5)
                ,ThreadLocalRandom.current().nextInt(0,5)
                ,ThreadLocalRandom.current().nextInt(0,5)
                ,TestDataProviderController.buildEquipoEntity()
                ,TestDataProviderController.buildTorneoEntity());
    }

    @Test
    public void constructorAllArgs(){
        assertNotNull(posicionEntity);
    }

}