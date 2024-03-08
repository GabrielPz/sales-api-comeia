package com.gabrielhenrique.salesapicomeia.modules.itens.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielhenrique.salesapicomeia.modules.itens.entity.ItensEntity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ItensControllerIntegrationTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void createItem_success() throws Exception {
        ItensEntity newItem = new ItensEntity();
        newItem.setName("Test Item");
        newItem.setPrice(new BigDecimal("100.00"));

        ObjectMapper objectMapper = new ObjectMapper();
        String newItemJson = objectMapper.writeValueAsString(newItem);

        mvc.perform(post("/item/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newItemJson))
                .andExpect(status().isOk());
    }
}
