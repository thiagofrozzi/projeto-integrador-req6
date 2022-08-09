package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.outputDto.ProductOutputDto;
import dh.meli.projeto_integrador.exception.NotFoundException;
import dh.meli.projeto_integrador.exception.ResourceNotFoundException;
import dh.meli.projeto_integrador.model.Product;
import dh.meli.projeto_integrador.repository.IProductRepository;
import dh.meli.projeto_integrador.util.GenerateProducts;
import dh.meli.projeto_integrador.util.Generators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    IProductRepository productRepository;

    @BeforeEach
    void setup() {
        BDDMockito.when(productRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(Generators.getProduct()));
    }

    @Test
    void findProductTest() {
        long id = 0;
        Product product = productService.findProduct(id);

        assertThat(product.getId()).isEqualTo(Generators.getProduct().getId());
        assertThat(product.getType()).isEqualTo(Generators.getProduct().getType());
        assertThat(product.getPrice()).isEqualTo(Generators.getProduct().getPrice());
        assertThat(product.getSection().getId()).isEqualTo(Generators.getProduct().getSection().getId());

        verify(productRepository, atLeastOnce()).findById(id);
    }

    @Test
    void findProduct_WhenProductDontExistsTest() throws Exception {
        BDDMockito.when(productRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        long id = 0;

        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            Product product = productService.findProduct(id);
        });

        assertThat(exception.getMessage()).isEqualTo(String.format("Could not find valid product for id %d", id));

        verify(productRepository, atLeastOnce()).findById(id);
    }

    @Test
    void getAllProducts_returnListProducts_whenProductsExists() {
        BDDMockito.when(productRepository.findAll())
                .thenReturn(GenerateProducts.productList());

        List<ProductOutputDto> products = productService.getAllProducts();


        assertThat(products).isNotNull();
        assertThat((products.size())).isEqualTo(2);
    }

    @Test
    void getAllProducts_returnListProducts_whenProductsDontExist() {
        BDDMockito.when(productRepository.findAll())
                .thenReturn(GenerateProducts.emptyProductDtoList());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            productService.getAllProducts();
        });

        assertThat(exception.getMessage()).isEqualTo(String.format("No Products Found"));
    }

    @Test
    void getProductsByCategory_returnListProducts_whenProductsExists() {
        BDDMockito.when(productRepository.findAllByType(anyString()))
                .thenReturn(GenerateProducts.productList());

        List<ProductOutputDto> products = productService.getProductsByCategory(GenerateProducts.validProduct1().getType());


        assertThat(products).isNotNull();
        assertThat((products.size())).isEqualTo(2);
    }

    @Test
    void getProductsByCategory_returnListProducts_whenProductsDontExist() {
        BDDMockito.when(productRepository.findAllByType(anyString()))
                .thenReturn(GenerateProducts.emptyProductDtoList());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            productService.getProductsByCategory(GenerateProducts.validProduct1().getType());
        });

        assertThat(exception.getMessage()).isEqualTo(String.format("No Products Found"));
    }
}
