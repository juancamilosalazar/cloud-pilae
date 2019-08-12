package com.uco.pilae.pilae.model;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertNotNull;

public class TorneoTest {

    Torneo torneo;

    @Before
    public void before() {
        torneo = new Torneo(ThreadLocalRandom.current().nextLong(0, 50)
                , "dasfasdf", "fshshsb", TestDataProviderController.buildDeporte());
    }

    @Test
    public void constructorAllArgs() {
        assertNotNull(torneo);
    }

}