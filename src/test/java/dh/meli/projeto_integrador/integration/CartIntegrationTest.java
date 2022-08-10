package dh.meli.projeto_integrador.integration;

import dh.meli.projeto_integrador.model.Cart;
import dh.meli.projeto_integrador.model.Customer;
import dh.meli.projeto_integrador.model.Product;
import dh.meli.projeto_integrador.model.ProductCart;
import dh.meli.projeto_integrador.repository.ICartRepository;
import dh.meli.projeto_integrador.repository.ICustomerRepository;
import dh.meli.projeto_integrador.repository.IProductCartRepository;
import dh.meli.projeto_integrador.repository.IProductRepository;
import dh.meli.projeto_integrador.util.Generators;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CartIntegrationTest {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IProductCartRepository productCartRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        cartRepository.deleteAll();
    }

    @Test
    public void findCartById_ReturnListOfProducts_whenFindAllSuccess() throws Exception {
        Cart cart = Generators.validCartWhitoutProductCart();
        Customer customer = Generators.validCustomer1();
        ProductCart productCart1 = Generators.validProductCart1();
        ProductCart productCart2 = Generators.validProductCart2();
        Product product1 = Generators.validProduct1();
        Product product2 = Generators.validProduct2();

        customerRepository.save(customer);
        cartRepository.save(cart);
        productRepository.save(product1);
        productRepository.save(product2);
        productCartRepository.save(productCart1);
        productCartRepository.save(productCart2);

        ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products//orders/{id}", Generators.validCart1().getId())
                        .contentType(MediaType.APPLICATION_JSON));


        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.total",
                        CoreMatchers.is(Generators.validCartDto().getTotal())))
                .andExpect(jsonPath("$.customerName",
                        CoreMatchers.is(Generators.validCartDto().getCustomerName())));
    }

    @Test
    public void findAll_ReturnEmptylist_whenProductsDoesntExists() throws Exception {

        ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products//orders/{id}", Generators.validCart1().getId())
                        .contentType(MediaType.APPLICATION_JSON));


        response.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message",
                        CoreMatchers.is(String.format("Could not find valid cart for id %d", Generators.validCart1().getId()))))
                .andExpect(jsonPath("$.status",
                        CoreMatchers.is(404)))
                .andExpect(jsonPath("$.title",
                        CoreMatchers.is("Resource Not Found")));
    }
}
