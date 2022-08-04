package dh.meli.projeto_integrador.repository;

import dh.meli.projeto_integrador.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product, Long> {}
