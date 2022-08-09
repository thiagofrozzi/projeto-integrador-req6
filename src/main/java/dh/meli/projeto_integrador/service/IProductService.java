package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.outputDto.ProductOutputDto;
import dh.meli.projeto_integrador.model.Product;

import java.util.List;

/**
 * Interface to deal with the methods implemented on the ProductService class.
 */
public interface IProductService {
    List<ProductOutputDto> getAllProducts();
    List<ProductOutputDto> getProductsByCategory(String category);
    Product findProduct(long id);
}
