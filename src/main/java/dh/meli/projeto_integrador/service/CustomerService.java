package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoInput.CustomerDto;
import dh.meli.projeto_integrador.dto.dtoOutput.CustomerOutputDto;
import dh.meli.projeto_integrador.dto.dtoOutput.NewCustomerDto;
import dh.meli.projeto_integrador.exception.CustomerExistException;
import dh.meli.projeto_integrador.exception.ResourceNotFoundException;
import dh.meli.projeto_integrador.model.Customer;
import dh.meli.projeto_integrador.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    /**
     * Dependency Injection of the Customer Repository.
     */
    @Autowired
    private ICustomerRepository customerRepository;

    /**
     * Method create customer on the database and returns the customer created.
     * @param customerDto an object of type CustomerDto
     * @return an object of type NewCustomerDto.
     */
    public NewCustomerDto createCustomer(CustomerDto customerDto){
        Customer findCustomer = customerRepository.findByCpf(customerDto.getCpf());

        if(findCustomer != null){
            throw new CustomerExistException("This customer already exists with this CPF");
        }
        Customer customer = Customer.builder()
                .name(customerDto.getName())
                .emailAddress(customerDto.getEmail())
                .phoneNumber(customerDto.getNumber())
                .cpf(customerDto.getCpf())
            .build();

        Customer savedCustomer = customerRepository.save(customer);

        return new NewCustomerDto(savedCustomer);
    }

    /**
     * Method that handles the request to fetch a customer in the database and return an Dto with the relevant information.
     * @param id a Long with the id of the customer requested
     * @return an object of type CustomerOutputDto with all the information regarding the customer requested.
     */
    public CustomerOutputDto findById(long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        return new CustomerOutputDto(customer);
    }
}
