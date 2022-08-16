package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoInput.CustomerDto;
import dh.meli.projeto_integrador.dto.dtoOutput.NewCustomerDto;
import dh.meli.projeto_integrador.model.Customer;

public interface ICustomerService {
    NewCustomerDto createCustomer(CustomerDto customerDto);

    Customer findById(long id);
}
