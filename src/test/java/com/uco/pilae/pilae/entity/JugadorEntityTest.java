package com.uco.pilae.pilae.entity;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class JugadorEntityTest {

    JugadorEntity jugadorEntity;

    @Before
    public void before(){
        jugadorEntity = new JugadorEntity(ThreadLocalRandom.current().nextLong(0,50)
                ,"asdfas","asdfafs"
                ,TestDataProviderController.buildDateRandom(915166800000L)
                , TestDataProviderController.buildEquipoEntity());
    }

    @Test
    public void constructorAllArgs(){
        assertNotNull(jugadorEntity);
    }

}