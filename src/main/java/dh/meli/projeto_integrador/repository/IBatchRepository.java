package dh.meli.projeto_integrador.repository;

import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.model.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface IBatchRepository will manage data persistence for Batch object instances.
 * Will read, save, update and delete data through the GET, POST, PUT and DELETE requests.
 * @author Diovana Valim
 * @version 0.0.1
 */
public interface IBatchRepository extends CrudRepository<Batch, Long> {
    /**
     * Method that find a product by id
     * @param product an object of type Product
     * @return an object of type Batch
     */
    Batch findByProduct(Product product);
}
