package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoInput.CustomerDto;
import dh.meli.projeto_integrador.dto.dtoOutput.CustomerOutputDto;
import dh.meli.projeto_integrador.dto.dtoOutput.NewCustomerDto;

/**
 * Interface ICustomerService will manage data persistence for Cart object instances.
 * Will read, save, update and delete data through the GET, POST, PUT and DELETE requests.
 * @author Thiago Frozzi
 * @version 0.0.1
 */
public interface ICustomerService {
    /**
     * Method that creates a Customer
     * @param customerDto an object of type CustomerDto coming from user request
     * @return an object of type NewCustomerDto
     */
    NewCustomerDto createCustomer(CustomerDto customerDto);

    /**
     * Method that find the customer
     * @param id Long
     * @return an object of type Customer
     */
    CustomerOutputDto findById(long id);
}
