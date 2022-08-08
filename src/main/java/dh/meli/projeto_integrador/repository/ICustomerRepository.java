package dh.meli.projeto_integrador.repository;

import dh.meli.projeto_integrador.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {
}
