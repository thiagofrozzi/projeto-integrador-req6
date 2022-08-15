package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoInput.CustomerDto;
import dh.meli.projeto_integrador.dto.dtoOutput.NewCustomerDto;

public interface ICustomerService {
    NewCustomerDto createCustomer(CustomerDto customerDto);
}
