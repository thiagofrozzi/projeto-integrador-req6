package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoOutput.ProductOutputDto;
import dh.meli.projeto_integrador.model.Product;

import java.util.List;

/**
 * Interface to specify service methods implemented on ProductService class.
 * @author Rafael Cavalcante
 * @version 0.0.1
 */
public interface IProductService {
    /**
     *
     * @return
     */
    List<ProductOutputDto> getAllProducts();

    /**
     *
     * @param category
     * @return
     */
    List<ProductOutputDto> getProductsByCategory(String category);

    /**
     *
     * @param id
     * @return
     */
    Product findProduct(long id);
}
