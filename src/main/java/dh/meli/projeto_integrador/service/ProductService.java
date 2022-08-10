package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoOutput.BatchDto;
import dh.meli.projeto_integrador.dto.dtoOutput.ProductStockDto;
import dh.meli.projeto_integrador.dto.dtoOutput.ProductOutputDto;
import dh.meli.projeto_integrador.exception.ResourceNotFoundException;
import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.model.Product;
import dh.meli.projeto_integrador.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class responsible for business rules and communication with the Product Repository layer;
 *
 * @author Diovana Valim, Rafael Cavalcante e Amanda Marinelli
 * @version 0.0.1
 */
@Service
public class ProductService implements IProductService {

    /**
     * Dependency Injection of the Product Repository.
     */
    @Autowired
    private IProductRepository productRepository;


    /**
     * Method to find a list of products and return a ProductDto.
     *
     * @return a list of objects of type ProductDto.
     */
    @Override
    public List<ProductOutputDto> getAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();

        if (products.size() == 0) throw new ResourceNotFoundException("No Products Found");

        return products.stream().map(ProductOutputDto::new).collect(Collectors.toList());
    }

    /**
     * Method to find a list of products of a specified category and return a ProductDto.
     *
     * @param category of type String.
     * @return a list of objects of type ProductDto.
     */
    @Override
    public List<ProductOutputDto> getProductsByCategory(String category) {
        List<Product> products = productRepository.findAllByType(category);

        if (products.size() == 0) throw new ResourceNotFoundException("No Products Found");

        return products.stream().map(ProductOutputDto::new).collect(Collectors.toList());
    }

    /**
     * Method to find a product by id;
     *
     * @param id of type long. Product identifier;
     * @return an object of type Product;
     */
    @Override
    public Product findProduct(long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Could not find valid product for id %d", id));
        }

        return product.get();
    }

    /**
     * Method to find a product by id and return some properties about the batches;
     *
     * @param id of type long. Product identifier;
     * @return a DTO with informations of the product and his batches;
     */
    @Override
    public ProductStockDto getProductBatchProps(Long id) {
       //TODO pegar batch direto do ID -> projeto Dio
        Product product = findProduct(id);
        Set<Batch> batches = product.getBatches();

        if (batches.isEmpty()) {
            throw new ResourceNotFoundException("No available batch found for this product.");
        }

        ProductStockDto productStockDto = new ProductStockDto(product, batches);
        return productStockDto;
    }

    private static List<BatchDto> sortByOrder(List<BatchDto> batchList, Character order) {
        switch (order) {
            case 'L':
                return batchList.stream()
                        .sorted(Comparator.comparing(BatchDto::getBatchNumber))
                        .collect(Collectors.toList());
            case 'Q':
                return batchList.stream()
                        .sorted(Comparator.comparing(BatchDto::getCurrentQuantity))
                        .collect(Collectors.toList());
            case 'V':
                return batchList.stream()
                        .sorted(Comparator.comparing(BatchDto::getDueDate))
                        .collect(Collectors.toList());
            default:
                return batchList;
        }
    }
}