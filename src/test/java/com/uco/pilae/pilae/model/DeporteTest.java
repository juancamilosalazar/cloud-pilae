package com.uco.pilae.pilae.model;

import com.uco.pilae.pilae.entity.DeporteEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class DeporteTest {

    Deporte deporte;

    @Before
    public void before(){
        deporte= new Deporte(ThreadLocalRandom.current().nextLong(0,50),"fsdfaf");
    }

    @Test
    public void constructorAllArgs(){
        assertNotNull(deporte);
    }

}