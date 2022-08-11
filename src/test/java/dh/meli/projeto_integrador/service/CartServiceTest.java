package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoInput.CartDto;
import dh.meli.projeto_integrador.dto.dtoOutput.TotalPriceDto;
import dh.meli.projeto_integrador.dto.dtoOutput.UpdateStatusDto;
import dh.meli.projeto_integrador.exception.ForbiddenException;
import dh.meli.projeto_integrador.exception.ResourceNotFoundException;
import dh.meli.projeto_integrador.model.*;
import dh.meli.projeto_integrador.repository.*;
import dh.meli.projeto_integrador.utils.*;
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
    void create_returnException_whenQuantityIsNotValid() {
        Cart newCartWithId = GenerateCart.newCartWithId1();
        Customer newCustomer = GenerateCustomer.newCustomer1();
        Product newProduct = GenerateProduct.newProduct1();
        Batch newBatch = GenerateBatch.newBatchInvalidQuantity();
        CartDto cartDto = GenerateCartDto.newCartDto();

        BDDMockito.when(customerRepository.findById(ArgumentMatchers.anyLong()))
            .thenReturn(Optional.of(newCustomer));
        BDDMockito.when(cartRepository.save(ArgumentMatchers.any(Cart.class)))
                .thenReturn(newCartWithId);
        BDDMockito.when(productRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(newProduct));
        BDDMockito.when(batchRepository.findByProduct(ArgumentMatchers.any(Product.class)))
                .thenReturn(newBatch);

        ForbiddenException exception = Assertions.assertThrows(ForbiddenException.class, () -> {
            cartService.createCart(cartDto);
        });

        assertThat(exception.getMessage()).isEqualTo("The product: [Morango] does not have enough quantity in stock.");
        verify(cartRepository, never()).save(GenerateCart.newCart1());
    }

    @Test
    @DisplayName("Test update with success")
    void updateStatusCartWithSuccess() {
        BDDMockito.when(cartRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(GenerateCart.newCartWithId1()));

        UpdateStatusDto result = cartService.updateStatusCart(GenerateCart.newCartWithId1().getId());

        assertThat(result.getMessage()).isEqualTo("Cart Finished successfully");
        verify(cartRepository, atLeastOnce()).findById(1L);
    }


    @Test
    @DisplayName("Tests function when not found cart")
    void updateStatusCartNotFound() {

        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            cartService.updateStatusCart(1L);
        });

        assertThat(exception.getMessage()).isEqualTo("Cart not found with this id");
        verify(cartRepository, never()).save(GenerateCart.newCart1());
    }

    @Test
    @DisplayName("Tests exception when cart status is already finished")
    void update_returnException_WhenCartStatusAlreadyFinished() {
        BDDMockito.when(cartRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(GenerateCart.newCartWithId2()));

            ForbiddenException exception = Assertions.assertThrows(ForbiddenException.class, () -> {
                cartService.updateStatusCart(1L);
        });

        assertThat(exception.getMessage()).isEqualTo("Cart already Finished");
        verify(cartRepository, never()).save(GenerateCart.newCart1());
    }
}