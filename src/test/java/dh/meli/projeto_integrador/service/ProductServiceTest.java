package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.outputDto.ProductOutputDto;
import dh.meli.projeto_integrador.exception.NotFoundException;
import dh.meli.projeto_integrador.repository.IProductRepository;
import dh.meli.projeto_integrador.util.GenerateProducts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private IProductRepository productRepository;

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