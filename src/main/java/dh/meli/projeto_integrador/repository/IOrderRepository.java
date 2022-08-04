package dh.meli.projeto_integrador.repository;

import dh.meli.projeto_integrador.model.OrderEntry;
import org.springframework.data.repository.CrudRepository;

public interface IOrderRepository extends CrudRepository<OrderEntry, Long> {}
