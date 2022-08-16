package dh.meli.projeto_integrador.repository;

import dh.meli.projeto_integrador.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface ICustomerRepository will manage data persistence for Customer object instances.
 * Will read, save, update and delete data through the GET, POST, PUT and DELETE requests.
 * @author Thiago Frozzi
 * @version 0.0.1
 */
public interface ICustomerRepository extends CrudRepository<Customer, Long> {
    @Query(value = "SELECT * FROM customer WHERE cpf = ?1", nativeQuery = true)
    Customer findByCpf(String cpf);
}
