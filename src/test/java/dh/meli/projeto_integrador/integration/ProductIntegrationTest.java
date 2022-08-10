package dh.meli.projeto_integrador.integration;

import dh.meli.projeto_integrador.model.*;
import dh.meli.projeto_integrador.repository.*;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductIntegrationTest {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IBatchRepository batchRepository;

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private ISectionRepository sectionRepository;

    @Autowired
    private IWarehouseRepository warehouseRepository;

    @Autowired
    private  IAgentRepository agentRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        productRepository.deleteAll();
        batchRepository.deleteAll();
    }

    @Test
    public void findAllProducts_ReturnListOfAllProducts_whenFindAllSuccess() throws Exception {
        List<Product> list = new ArrayList<>();
        list.add(Generators.validProduct1());
        list.add(Generators.validProduct2());

        productRepository.saveAll(list);

        ResultActions response = mockMvc.perform(get("/api/v1/fresh-products/")
                .contentType(MediaType.APPLICATION_JSON));


        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",
                        CoreMatchers.is(list.size())))
                .andExpect(jsonPath("$[0].name",
                        CoreMatchers.is(Generators.validProduct1().getName())));;
    }

    @Test
    public void findAllProducts_ReturnNotFound_whenProductsDoesntExists() throws Exception {
        ResultActions response = mockMvc.perform(get("/api/v1/fresh-products/")
                .contentType(MediaType.APPLICATION_JSON));


        response.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message",
                        CoreMatchers.is("No Products Found")))
                .andExpect(jsonPath("$.status",
                        CoreMatchers.is(404)))
                .andExpect(jsonPath("$.title",
                        CoreMatchers.is("Resource Not Found")));
    }

    @Test
    public void findProductsByCategory_ReturnListOfProducts_whenFindAllSuccess() throws Exception {
        List<Product> list = new ArrayList<>();
        list.add(Generators.validProduct1());
        list.add(Generators.validProduct2());

        productRepository.saveAll(list);

        ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products/{category}", Generators.validProduct2().getType())
                .contentType(MediaType.APPLICATION_JSON));


        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",
                        CoreMatchers.is(list.size() -1)))
                .andExpect(jsonPath("$[0].name",
                        CoreMatchers.is(Generators.validProduct2().getName())))
                .andExpect(jsonPath("$[0].type",
                        CoreMatchers.is(Generators.validProduct2().getType())));
    }

    @Test
    public void findAll_ReturnEmptylist_whenProductsDoesntExists() throws Exception {
        Product newProduct1 = Generators.validProduct1();
        Product newProduct2 = Generators.validProduct2();

        productRepository.save(newProduct1);

        ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products/{category}", newProduct2.getType())
                        .contentType(MediaType.APPLICATION_JSON));


        response.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message",
                        CoreMatchers.is("No Products Found")))
                .andExpect(jsonPath("$.status",
                        CoreMatchers.is(404)))
                .andExpect(jsonPath("$.title",
                        CoreMatchers.is("Resource Not Found")));
    }

    @Test
    public void listProductByWarehouse_whenFindBatchIsSuccessfull() throws Exception {

        Batch batch = Generators.getBatch();
        Product product = Generators.getProduct();
        Warehouse warehouse = Generators.getWarehouse();
        Agent agent = Generators.getAgent();

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(1);
        warehouse1.setName("Armazém 01");
        warehouse1.setAddress("Rua Almeida 259");

        Section section = new Section();

        section.setId(1);
        section.setCurrentProductLoad(130);
        section.setMaxProductLoad(2000);
        section.setProductType("Fresco");
        section.setWarehouse(warehouse);

        OrderEntry orderEntry = new OrderEntry();

        orderEntry.setId(1);
        orderEntry.setOrderDate(LocalDate.now());
        orderEntry.setSection(section);

        Warehouse warehouse11 = warehouseRepository.save(warehouse1);

        System.out.println(warehouse1.getId());

        sectionRepository.save(section);

        orderRepository.save(orderEntry);

        productRepository.save(product);

        batchRepository.save(batch);

        batch.setId(1);

        ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products/warehouse/product/{productId}", batch.getId())
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

    @Test
    public void listProductByWarehouse_whenFindBatchFailed() throws Exception {
        long id = 20;

        ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products/warehouse/product/{productId}", id)
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message",
                        CoreMatchers.is("Could not find valid batch stock for product 20")))
                .andExpect(jsonPath("$.status",
                        CoreMatchers.is(404)))
                .andExpect(jsonPath("$.title",
                        CoreMatchers.is("Resource Not Found")));
    }
}
