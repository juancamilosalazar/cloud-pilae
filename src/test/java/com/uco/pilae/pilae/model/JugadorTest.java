package com.uco.pilae.pilae.model;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.JugadorEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class JugadorTest {

    Jugador jugador;

    @Before
    public void before(){
        jugador = new Jugador(ThreadLocalRandom.current().nextLong(0,50)
                ,"asdfas","asdfafs"
                , TestDataProviderController.buildLongDateRandom(915166800000L)
                , TestDataProviderController.buildEquipo());
    }

    @Test
    public void constructorAllArgs(){
        assertNotNull(jugador);
    }

}