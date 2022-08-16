package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoInput.CustomerDto;
import dh.meli.projeto_integrador.dto.dtoOutput.CustomerOutputDto;
import dh.meli.projeto_integrador.dto.dtoOutput.NewCustomerDto;
import dh.meli.projeto_integrador.exception.CustomerExistException;
import dh.meli.projeto_integrador.exception.ResourceNotFoundException;
import dh.meli.projeto_integrador.model.Customer;
import dh.meli.projeto_integrador.repository.ICustomerRepository;
import dh.meli.projeto_integrador.utils.GenerateCustomer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    ICustomerRepository customerRepository;

    @Test
    void createCustomerWithSuccess() {
        NewCustomerDto customerOutputDto = GenerateCustomer.newCustomerOutputDto();
        Customer customerWithId = GenerateCustomer.newCustomer1();
        CustomerDto customerDto = GenerateCustomer.newCustomerDto1();

        BDDMockito.when(customerRepository.findByCpf(ArgumentMatchers.anyString()))
                .thenReturn(null);
        BDDMockito.when(customerRepository.save(ArgumentMatchers.any(Customer.class)))
                .thenReturn(customerWithId);

       NewCustomerDto result = customerService.createCustomer(customerDto);

        assertThat(result.getName()).isEqualTo(customerOutputDto.getName());
        assertThat(result.getEmail()).isEqualTo(customerOutputDto.getEmail());
        verify(customerRepository, atLeastOnce()).findByCpf(customerDto.getCpf());
    }

    @Test
    void createCustomerThrowExceptionAlreadyExists() {
        CustomerDto customerDto = GenerateCustomer.newCustomerDto1();
        Customer customerWithId = GenerateCustomer.newCustomer1();

        BDDMockito.when(customerRepository.findByCpf(ArgumentMatchers.anyString()))
                .thenReturn(customerWithId);

        CustomerExistException exception = assertThrows(CustomerExistException.class, () -> {
            customerService.createCustomer(customerDto);
        });

        assertThat(exception.getMessage()).isEqualTo("This customer already exists with this CPF");
        verify(customerRepository, never()).save(ArgumentMatchers.any(Customer.class));

    }

    @Test
    void findByIdWithSuccess() {
        Customer customerWithCart = GenerateCustomer.customerWithCart();
        NewCustomerDto customerOutputDto = GenerateCustomer.newCustomerOutputDto();

        BDDMockito.when(customerRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(customerWithCart));

        CustomerOutputDto result = customerService.findById(1L);

        assertThat(result.getEmail()).isEqualTo(customerOutputDto.getEmail());
        assertThat(result.getCart().size() == 1).isTrue();
        verify(customerRepository, atLeastOnce()).findById(1L);
    }

    @Test
    void findByIdThrowExceptionNotFound() {

        BDDMockito.when(customerRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            customerService.findById(1L);
        });

        assertThat(exception.getMessage()).isEqualTo("Customer not found");
    }
}