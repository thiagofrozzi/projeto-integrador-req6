package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoInput.CustomerDto;
import dh.meli.projeto_integrador.dto.dtoOutput.NewCustomerDto;
import dh.meli.projeto_integrador.exception.CustomerExistException;
import dh.meli.projeto_integrador.model.Customer;
import dh.meli.projeto_integrador.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

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
}
