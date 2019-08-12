package com.uco.pilae.pilae.entity;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class MarcadorEntityTest {

    MarcadorEntity marcadorEntity;

    @Before
    public void before(){
        marcadorEntity = new MarcadorEntity(ThreadLocalRandom.current().nextLong(0,50)
                ,"asdfas",ThreadLocalRandom.current().nextInt(1,5)
                ,ThreadLocalRandom.current().nextInt(1,5)
                ,TestDataProviderController.buildPartidoEntity());
    }

    @Test
    public void constructorAllArgs(){
        assertNotNull(marcadorEntity);
    }

}