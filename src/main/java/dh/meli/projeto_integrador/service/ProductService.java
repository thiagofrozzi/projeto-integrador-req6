package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.exception.ResourceNotFoundException;
import dh.meli.projeto_integrador.model.Product;
import dh.meli.projeto_integrador.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Class responsible for business rules and communication with the Product Repository layer;
 * @author Diovana Valim;
 * @version 0.0.1
 */
@Service
public class ProductService {

    /**
     * Dependency Injection of the Product Repository.
     */
    @Autowired
    private IProductRepository productRepository;

    /**
     * Method to find a product by id;
     * @param id of type long. Product identifier;
     * @return an object of type Product;
     */
    public Product findProduct(long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Could not find valid product for id %d", id));
        }

        return product.get();
    }
}
