package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.repository.ICartRepository;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CartServiceTest {

    @InjectMocks
    CartService cartService;

    @Mock
    ICartRepository cartRepository;

    @Test
    void createCart() {
    }

    @Test
    @DisplayName("Test update with success")
    void updateStatusCartWithSuccess() {
        BDDMockito.when(cartRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsGenerator.generateNewProperty());
    }
}