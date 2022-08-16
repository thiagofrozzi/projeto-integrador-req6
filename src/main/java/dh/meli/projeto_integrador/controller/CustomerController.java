package dh.meli.projeto_integrador.controller;

import dh.meli.projeto_integrador.dto.dtoInput.CustomerDto;
import dh.meli.projeto_integrador.dto.dtoOutput.CustomerOutputDto;
import dh.meli.projeto_integrador.dto.dtoOutput.NewCustomerDto;
import dh.meli.projeto_integrador.model.Customer;
import dh.meli.projeto_integrador.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public ResponseEntity<NewCustomerDto> create(@RequestBody @Valid CustomerDto customerDto) {
        NewCustomerDto createdCustomer = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerOutputDto> create(@PathVariable long id) {
        CustomerOutputDto result = customerService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
