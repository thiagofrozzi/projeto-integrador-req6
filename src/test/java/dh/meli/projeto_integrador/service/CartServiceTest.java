package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoInput.CartDto;
import dh.meli.projeto_integrador.dto.dtoOutput.TotalPriceDto;
import dh.meli.projeto_integrador.dto.dtoOutput.UpdateStatusDto;
import dh.meli.projeto_integrador.exception.CartNotFoundException;
import dh.meli.projeto_integrador.model.*;
import dh.meli.projeto_integrador.repository.*;
import dh.meli.projeto_integrador.util.Generators;
import dh.meli.projeto_integrador.utils.*;
import org.hibernate.sql.Update;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CartServiceTest {

    @InjectMocks
    CartService cartService;

    @Mock
    ICartRepository cartRepository;

    @Mock
    ICustomerRepository customerRepository;

    @Mock
    IProductRepository productRepository;

    @Mock
    IProductCartRepository productCartRepository;

    @Mock
    IBatchRepository batchRepository;

    @Test
    void createCart() {
        Cart newCartWithId = GenerateCart.newCartWithId1();
        Customer newCustomer = GenerateCustomer.newCustomer1();
        Product newProduct = GenerateProduct.newProduct1();
        Batch newBatch = GenerateBatch.newBatch1();
        CartDto newCartDto = GenerateCart.newCartDto1();
        ProductCart newProductCart = GenerateProductCart.newProductCart1();

        BDDMockito.when(customerRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(newCustomer));
        BDDMockito.when(cartRepository.save(ArgumentMatchers.any(Cart.class)))
                .thenReturn(newCartWithId);
        BDDMockito.when(productRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(newProduct));
        BDDMockito.when(batchRepository.findByProduct(ArgumentMatchers.any(Product.class)))
                .thenReturn(newBatch);
        BDDMockito.when(productCartRepository.save(ArgumentMatchers.any(ProductCart.class)))
                .thenReturn(newProductCart);


        TotalPriceDto result = cartService.createCart(newCartDto);

        assertThat(result.getTotalPrice()).isEqualTo(newProduct.getPrice());
        verify(customerRepository, atLeastOnce()).findById(1L);
        verify(cartRepository, atLeastOnce()).save(ArgumentMatchers.any(Cart.class));
        verify(productCartRepository, atLeastOnce()).save(ArgumentMatchers.any(ProductCart.class));
    }

    @Test
    @DisplayName("Test update with success")
    void updateStatusCartWithSuccess() {
        BDDMockito.when(cartRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(GenerateCart.newCart1()));

        UpdateStatusDto result = cartService.updateStatusCart(GenerateCart.newCart1().getId());

        assertThat(result.getMessage()).isEqualTo("Cart Finished successfully");
        verify(cartRepository, atLeastOnce()).findById(1L);

    }


    @Test
    @DisplayName("Tests function when not found cart ")
    void updateStatusCartNotFound() {

        CartNotFoundException exception = Assertions.assertThrows(CartNotFoundException.class, () -> {
            cartService.updateStatusCart(1L);
        });

        assertThat(exception.getMessage()).isEqualTo("Cart not found with this id");
        verify(cartRepository, never()).save(GenerateCart.newCart1());
    }
}