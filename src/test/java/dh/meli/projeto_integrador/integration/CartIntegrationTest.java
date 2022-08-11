package dh.meli.projeto_integrador.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import dh.meli.projeto_integrador.dto.dtoInput.CartDto;
import dh.meli.projeto_integrador.dto.dtoOutput.TotalPriceDto;
import dh.meli.projeto_integrador.dto.dtoOutput.UpdateStatusDto;
import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.model.Cart;
import dh.meli.projeto_integrador.model.Customer;
import dh.meli.projeto_integrador.model.Product;
import dh.meli.projeto_integrador.repository.IBatchRepository;
import dh.meli.projeto_integrador.repository.ICartRepository;
import dh.meli.projeto_integrador.repository.ICustomerRepository;
import dh.meli.projeto_integrador.repository.IProductRepository;
import dh.meli.projeto_integrador.utils.*;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private IProductRepository productRepository;

    @Autowired
    private IBatchRepository batchRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() { cartRepository.deleteAll(); }

    @Test
    public void create_returnTotalPrice_whenPurchaseOrder() throws Exception {
        CartDto cartDto = GenerateCartDto.newCartDto();
        TotalPriceDto totalPriceDto = GenerateTotalPrice.newTotalPrice();
        Customer customer = GenerateCustomer.newCustomer1();
        customerRepository.save(customer);
        Product product = GenerateProduct.newProduct1();
        productRepository.save(product);
        Batch batch = GenerateBatch.newBatch1();
        batchRepository.save(batch);

        ResultActions response = mockMvc.perform(post("/api/v1/fresh-products/orders")
                .content(objectMapper.writeValueAsString(cartDto))
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.totalPrice", CoreMatchers.is(totalPriceDto.getTotalPrice())));
    }

    @Test
    public void update_returnCartStatus_whenCartAlreadyExists() throws Exception{
        Customer customer = GenerateCustomer.newCustomer1();
        customerRepository.save(customer);
        Cart cart = GenerateCart.newCartWithId1();
        cartRepository.save(cart);
        UpdateStatusDto updateStatusDto = GenerateUpdateStatusDto.newUpdateStatusDto();

        ResultActions response = mockMvc.perform(put("/api/v1/fresh-products/{id}", GenerateCart.newCartWithId1().getId())
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.message",
                        CoreMatchers.is(updateStatusDto.getMessage())));
    }

}






















