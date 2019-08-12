package com.uco.pilae.pilae.operaciones;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.repository.PartidoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FixtureWhithReturnTest {
    @InjectMocks
    protected FixtureWhithReturn base;

    @Mock
    PartidoRepository partidoRepository;

    @Before
    public void before(){

    }

    @Test
    public void generateFixtureEquiposPar(){
        HashMap<Integer, EquipoEntity> generateFixture = TestDataProviderController.buildHashMapFixtureEquiposPar();
        List<PartidoEntity> partidoEntities = base.mostrarPartidos(base.calcularLiga(generateFixture),generateFixture,TestDataProviderController.buildTorneoEntity());
        assertNotNull(partidoEntities);
    }
    @Test
    public void generateFixtureEquiposImpar(){
        HashMap<Integer, EquipoEntity> generateFixture = TestDataProviderController.buildHashMapFixtureEquiposImpar();
        List<PartidoEntity> partidoEntities = base.mostrarPartidos(base.calcularLiga(generateFixture),generateFixture,TestDataProviderController.buildTorneoEntity());
        assertNotNull(partidoEntities);
    }

}