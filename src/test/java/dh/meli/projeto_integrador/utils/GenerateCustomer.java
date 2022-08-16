package dh.meli.projeto_integrador.utils;

import dh.meli.projeto_integrador.dto.dtoInput.CustomerDto;
import dh.meli.projeto_integrador.dto.dtoOutput.CustomerOutputDto;
import dh.meli.projeto_integrador.dto.dtoOutput.NewCustomerDto;
import dh.meli.projeto_integrador.model.Cart;
import dh.meli.projeto_integrador.model.Customer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenerateCustomer {
    public static Customer newCustomer1() {
        return Customer.builder()
                .id(1L)
                .name("Alberto")
                .phoneNumber("21-966668888")
                .cpf("111.111.111-11")
                .emailAddress("Alberto@email.com")
                .build();
    }

    public static CustomerDto newCustomerDto1() {
        return CustomerDto.builder()
                .name("Alberto")
                .email("Alberto@email.com")
                .number("21-966668888")
                .cpf("111.111.111-11")
                .build();
    }

    public static NewCustomerDto newCustomerOutputDto() {
        return NewCustomerDto.builder()
                .name("Alberto")
                .email("Alberto@email.com")
                .build();
    }

    public static CustomerOutputDto customerOutputDtoWithCart() {
        List<Long> cartList = new ArrayList<>();
        cartList.add(1L);

        return CustomerOutputDto.builder()
                .id(1L)
                .name("Alberto")
                .email("Alberto@email.com")
                .number("21-966668888")
                .cpf("111.111.111-11")
                .cart(cartList)
                .build();
    }
    public static Customer customerWithCart() {
        Set<Cart> cartSet = new HashSet<>();
        cartSet.add(GenerateCart.newCartWithId1());

        return Customer.builder()
                .id(1L)
                .name("Alberto")
                .phoneNumber("21-966668888")
                .cpf("111.111.111-11")
                .emailAddress("Alberto@email.com")
                .carts(cartSet)
                .build();
    }
}
