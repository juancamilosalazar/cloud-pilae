package com.uco.pilae.pilae.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class DeporteEntityTest {

    DeporteEntity deporteEntity;

    @Before
    public void before(){
        deporteEntity= new DeporteEntity(ThreadLocalRandom.current().nextLong(0,50),"fsdfaf");
    }

    @Test
    public void constructorAllArgs(){
        assertNotNull(deporteEntity);
    }
}