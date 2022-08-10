package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoInput.CartDto;
import dh.meli.projeto_integrador.dto.dtoOutput.CartOutputDto;
import dh.meli.projeto_integrador.dto.dtoOutput.UpdateStatusDto;
import dh.meli.projeto_integrador.exception.CartNotFoundException;
import dh.meli.projeto_integrador.exception.ResourceNotFoundException;
import dh.meli.projeto_integrador.model.Cart;
import dh.meli.projeto_integrador.model.Customer;
import dh.meli.projeto_integrador.repository.ICartRepository;
import dh.meli.projeto_integrador.repository.ICustomerRepository;
import dh.meli.projeto_integrador.repository.IProductRepository;
import dh.meli.projeto_integrador.util.Generators;
import dh.meli.projeto_integrador.utils.GenerateCart;
import dh.meli.projeto_integrador.utils.GenerateCustomer;
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

    @Test
    void createCart() {
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

    @Test
    void buildCart() {
        Cart newCart = GenerateCart.newCart1();
        Customer newCustomer = GenerateCustomer.newCustomer1();

        BDDMockito.when(customerRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(newCustomer));
        BDDMockito.when(cartRepository.save(ArgumentMatchers.any(Cart.class)))
                .thenReturn(newCart);

        CartDto newCartDto = GenerateCart.newCartDto1();

        Cart result = cartService.buildCart(newCartDto);

        assertThat(result.getCustomer().getId()).isEqualTo(1L);
    }

    @Test
    void buildProductCart() {
    }

    @Test
    void totalCartPrice() {
    }

    @Test
    void getCartById_WhenCartIsNotFound_ReturnException() {
        BDDMockito.when(cartRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        long id = 0;
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            cartService.getCartById(id);
        });

        assertThat(exception.getMessage()).isEqualTo(String.format("Could not find valid cart for id %d", id));

        verify(cartRepository, atLeastOnce()).findById(id);
    }

    @Test
    void getCartById_WhenCartIsFound_ReturnACartOutputDto() {
        BDDMockito.when(cartRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(Generators.validCart1()));

        BDDMockito.when(customerRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(Generators.validCustomer1()));

        BDDMockito.when(productRepository.findById(1L))
                .thenReturn(Optional.ofNullable(Generators.validProduct1()));

        BDDMockito.when(productRepository.findById(2L))
                .thenReturn(Optional.ofNullable(Generators.validProduct2()));

        long id = 1;
         CartOutputDto cart = cartService.getCartById(id);


        assertThat(cart.getCustomerName()).isEqualTo(Generators.validCustomer1().getName());
        assertThat(cart.getStatus()).isEqualTo(Generators.validCart1().getStatus());
        assertThat(cart.getDate()).isEqualTo(Generators.validCart1().getDate());
        assertThat(cart.getProducts().size()).isEqualTo(2);
        assertThat(cart.getProducts().get(0).getName()).isEqualTo(Generators.validProduct1().getName());
        assertThat(cart.getProducts().get(0).getQuantity()).isEqualTo(Generators.validProductCart1().getQuantity());
        assertThat(cart.getProducts().get(0).getType()).isEqualTo(Generators.validProduct1().getType());
        assertThat(cart.getProducts().get(0).getPrice()).isEqualTo(Generators.validProduct1().getPrice());
        assertThat(cart.getProducts().get(0).getSubtotal())
                .isEqualTo(Generators.validProduct1().getPrice() * Generators.validProductCart1().getQuantity());
        assertThat(cart.getTotal())
                .isEqualTo(Generators.validProduct1().getPrice() * Generators.validProductCart1().getQuantity()
                + Generators.validProduct2().getPrice() * Generators.validProductCart2().getQuantity());

        verify(cartRepository, atLeastOnce()).findById(id);
        verify(customerRepository, atLeastOnce()).findById(id);
        verify(productRepository, atLeastOnce()).findById(1L);
        verify(productRepository, atLeastOnce()).findById(2L);
    }
}
