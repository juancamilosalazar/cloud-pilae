package com.uco.pilae.pilae.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.DeporteEntity;
import com.uco.pilae.pilae.model.Deporte;
import com.uco.pilae.pilae.service.DeporteQueryService;
import com.uco.pilae.pilae.util.DataConversionUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
public class DeporteControllerTest {

    private DeporteController base;
    @Mock
    DeporteQueryService queryService;

    @Before
    public void before() {
        base = new DeporteController(queryService, new ModelMapper(), new DataConversionUtil(new ObjectMapper()));
    }

    @Test
    public void methodGet() throws Exception {
        Mockito.when(queryService.findAll()).thenReturn(TestDataProviderController.buildDeporteEntityList());
        List<Deporte> listaPrueba = base.findAll();
        assertNotNull(listaPrueba);
    }

    @Test
    public void methodPost() throws Exception {
        final Deporte deporte = TestDataProviderController.buildDeporte();
        final DeporteEntity deporteEntity = TestDataProviderController.buildDeporteEntity();
        Mockito.when(queryService.save(any(DeporteEntity.class))).thenReturn(deporteEntity);
        ResponseEntity<String> responseEntity = base.crear(deporte);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
    @Test
    public void methodPostException() throws Exception {
        final Deporte deporte = TestDataProviderController.buildDeporte();
        Mockito.when(queryService.save(any(DeporteEntity.class))).thenReturn(null);
        ResponseEntity<String> responseEntity = base.crear(deporte);
        assertEquals(HttpStatus.EXPECTATION_FAILED,responseEntity.getStatusCode());
    }
}