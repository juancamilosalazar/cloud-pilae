package com.uco.pilae.pilae.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.controller.DeporteController;
import com.uco.pilae.pilae.controller.JugadorController;
import com.uco.pilae.pilae.model.Deporte;
import com.uco.pilae.pilae.service.DeporteQueryService;
import com.uco.pilae.pilae.service.JugadorQueryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OktaOAuth2WebSecurityConfigurerAdapterTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private DeporteController base;
    @InjectMocks
    private JugadorController baseJugador;
    @Mock
    private JugadorQueryService serviceJugador;
    @Mock
    private DeporteQueryService service;

    @Before
    public void init() {

    }

    @Test
    public void methodGet() throws Exception {
        Mockito.when(service.findAll()).thenReturn(TestDataProviderController.buildDeporteEntityList());
        mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:9093/pilae/deporte")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    /*
    @Test
    public void methodGetJugador() throws Exception {
        Mockito.when(serviceJugador.findAll()).thenReturn(TestDataProviderController.buildJugadorEntityList());
        mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:9093/pilae/jugador")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }*/
    @Test
    public void methodPost() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/employees")
                .content(asJsonString(new Deporte(4L, "Basquet")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}