package dh.meli.projeto_integrador.repository;

import dh.meli.projeto_integrador.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByType(String type);
}
