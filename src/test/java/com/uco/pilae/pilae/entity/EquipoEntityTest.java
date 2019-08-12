package com.uco.pilae.pilae.entity;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class EquipoEntityTest {

    EquipoEntity equipoEntity;

    @Before
    public void before(){
        equipoEntity= new EquipoEntity(ThreadLocalRandom.current().nextLong(0,50)
                ,"asdfas","asdfafs","adsfasf"
                , TestDataProviderController.buildTorneoEntity());
    }

    @Test
    public void constructorAllArgs(){
        assertNotNull(equipoEntity);
    }

}