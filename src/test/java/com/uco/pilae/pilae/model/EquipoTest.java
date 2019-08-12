package com.uco.pilae.pilae.model;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.EquipoEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class EquipoTest {

    Equipo equipo;

    @Before
    public void before(){
        equipo= new Equipo(ThreadLocalRandom.current().nextLong(0,50)
                ,"asdfas","asdfafs","adsfasf"
                , TestDataProviderController.buildTorneo());
    }

    @Test
    public void constructorAllArgs(){
        assertNotNull(equipo);
    }
}