package dh.meli.projeto_integrador.controller;

import dh.meli.projeto_integrador.dto.outputDto.ProductOutputDto;
import dh.meli.projeto_integrador.service.ProductService;
import dh.meli.projeto_integrador.util.GenerateProducts;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.anyString;


@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private ObjectMapper objectMapper; // Json para String

    @MockBean
    private ProductService service;


    @Test
    void listAllProducts_returnListOfProducts_whenSuccess() throws Exception {
        List<ProductOutputDto> list = GenerateProducts.productDtoList();
        BDDMockito.when(service.getAllProducts())
                .thenReturn(list);

        ResultActions response = mockMvc.perform(get("/api/v1/fresh-products/")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",
                        CoreMatchers.is(list.size())))
                .andExpect(jsonPath("$[0].name",
                        CoreMatchers.is(GenerateProducts.validProductDto1().getName())));
    }


    @Test
    void listProductByCategory() throws Exception{
        List<ProductOutputDto> list = GenerateProducts.productDtoList();
        BDDMockito.when(service.getProductsByCategory(anyString()))
                .thenReturn(list);

        ResultActions response = mockMvc.perform(get("/api/v1/fresh-products/{cayegory}", GenerateProducts.validProductDto1().getType())
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",
                        CoreMatchers.is(list.size())))
                .andExpect(jsonPath("$[0].name",
                        CoreMatchers.is(GenerateProducts.validProductDto1().getName())));
    }
}