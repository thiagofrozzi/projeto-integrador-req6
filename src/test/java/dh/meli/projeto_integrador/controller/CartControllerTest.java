package dh.meli.projeto_integrador.controller;

import dh.meli.projeto_integrador.dto.dtoOutput.ProductOutputDto;
import dh.meli.projeto_integrador.service.CartService;
import dh.meli.projeto_integrador.service.ProductService;
import dh.meli.projeto_integrador.util.Generators;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService service;

    @Test
    void getCartById_returnCartDto_whenSuccess() throws Exception {

        BDDMockito.when(service.getCartById(anyLong()))
                .thenReturn(Generators.validCartDto());

        ResultActions response = mockMvc.perform(get("/api/v1/fresh-products//orders/{id}", Generators.validCart1().getId())
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.total",
                        CoreMatchers.is(Generators.validCartDto().getTotal())))
                .andExpect(jsonPath("$.customerName",
                        CoreMatchers.is(Generators.validCartDto().getCustomerName())));

        verify(service, atLeastOnce()).getCartById(Generators.validCart1().getId());
    }
}