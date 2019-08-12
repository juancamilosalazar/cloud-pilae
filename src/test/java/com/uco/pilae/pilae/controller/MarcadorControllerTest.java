package com.uco.pilae.pilae.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.exceptions.ResourceNotFoundException;
import com.uco.pilae.pilae.model.Marcador;
import com.uco.pilae.pilae.repository.PartidoRepository;
import com.uco.pilae.pilae.service.MarcadorQueryService;
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
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MarcadorControllerTest {

    protected MarcadorController base;
    @Mock
    MarcadorQueryService queryService;
    @Mock
    PartidoRepository partidoRepository;

    @Before
    public void before() {
        base = new MarcadorController(queryService, new ModelMapper(), new DataConversionUtil(new ObjectMapper()), partidoRepository);
        Mockito.when(partidoRepository.findById(anyLong())).thenReturn(TestDataProviderController.buildPartidoOptionalEntity());

    }

    @Test
    public void methodFindByIdTorneo() throws Exception {
        Mockito.when(queryService.findByFkTorneo(anyLong())).thenReturn(TestDataProviderController.buildMarcadorEntityList());
        List<Marcador> responseEntity = base.findByTorneo(2L);
        assertNotNull(responseEntity);
    }

    @Test
    public void methodFindByIdPartido() throws Exception {
        Mockito.when(queryService.findByFkPartido(any())).thenReturn(TestDataProviderController.buildMarcadorEntity());
        ResponseEntity<String> responseEntity = base.findMarcadorByPartido(2L);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void methodJugarPartido() throws Exception {
        Mockito.when(queryService.jugarPartido(any(), anyInt(), anyInt()))
                .thenReturn(TestDataProviderController.buildMarcadorEntity());
        ResponseEntity<String> responseEntity = base.jugarPartido(TestDataProviderController.buildMarcador(), 2L);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void methodJugarPartidoException() throws Exception {
        Mockito.when(partidoRepository.findById(2L)).thenThrow(TestDataProviderController.buildNotFoundException());
        try {
            ResponseEntity<String> responseEntity = base.jugarPartido(TestDataProviderController.buildMarcador(), 2L);
        }catch (ResourceNotFoundException e){
            assertEquals(e.getColumna(),"Codigo");
            assertEquals(e.getValor(),2L);
            assertEquals(e.getTabla(),"Marcador");
        }

    }


}