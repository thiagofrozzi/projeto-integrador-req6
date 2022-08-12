package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoOutput.ProductStockDto;
import dh.meli.projeto_integrador.dto.dtoOutput.ProductOutputDto;
import dh.meli.projeto_integrador.exception.ResourceNotFoundException;
import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.model.Product;
import dh.meli.projeto_integrador.repository.IBatchRepository;
import dh.meli.projeto_integrador.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Class responsible for business rules and communication with the Product Repository layer;
 *
 * @author Diovana Valim, Rafael Cavalcante, Amanda Marinelli e Thiago Almeida.
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
     * Dependency Injection of the Batch Repository.
     */
    @Autowired
    private IBatchRepository batchRepository;

    /**
     * Method to find a list of products and return a ProductDto.
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
<<<<<<< HEAD
     *
     * @param id    of type long. Product identifier;
=======
     * @param id of type long. Product identifier;
>>>>>>> b3f029e (chore: adiciona os m)
     * @param order of type character that identifies the specified order to list the result.
     * @return a DTO with informations of the product and his batches;
     */
    @Override
    public ProductStockDto getProductBatchProps(Long id, Character order) {
<<<<<<< HEAD

        Product product = findProduct(id);

        List<Batch> batchesInput = batchRepository.findBatchByProductId(id);

        if (batchesInput.isEmpty()) {
            throw new ResourceNotFoundException("No available batch found for this product.");
        }

=======
        //TODO pegar batch direto do ID -> projeto Dio

        Product product = findProduct(id);
        Set<Batch> batches = product.getBatches();
        if (batches.isEmpty()) {
            throw new ResourceNotFoundException("No available batch found for this product.");
        }

        List<Batch> batchesInput = new ArrayList<Batch>(batches);
>>>>>>> b3f029e (chore: adiciona os m)
        List<Batch> sortedFilteredList = sortByOrder(filterByDueDate(batchesInput), order);

        if (sortedFilteredList.isEmpty()) {
            throw new ResourceNotFoundException("Product found, but no Batch of given product has 3 or more weeks until due date");
        }

        return new ProductStockDto(product, sortedFilteredList);
    }

    /**
     * Method to filter a list of batches to contain only batches that have 3 or more weeks until their due date;
<<<<<<< HEAD
     *
=======
>>>>>>> b3f029e (chore: adiciona os m)
     * @param batchList a  List of Batch to be filtered.
     * @return a filtered list of batches;
     */
    private static List<Batch> filterByDueDate(List<Batch> batchList) {
        return batchList.stream()
                .filter(batch -> DAYS.between(LocalDate.now(), batch.getDueDate()) > 21)
                .collect(Collectors.toList());
    }

    /**
     * Method to sorted a list of batches
<<<<<<< HEAD
     *
     * @param batchList a  List of Batch to be sorted.
     * @param order     a  List of Batch to be sorted.
=======
     * @param batchList a  List of Batch to be sorted.
     * @param order a  List of Batch to be sorted.
>>>>>>> b3f029e (chore: adiciona os m)
     * @return a filtered list of batches;
     */
    private static List<Batch> sortByOrder(List<Batch> batchList, Character order) {
        switch (order) {
            case 'L':
                return batchList.stream()
<<<<<<< HEAD
                        .sorted(Comparator.comparingLong(Batch::getId))
                        .collect(Collectors.toList());
            case 'Q':
                return batchList.stream()
                        .sorted(Comparator.comparingInt(Batch::getCurrentQuantity))
=======
                        .sorted((p1, p2) -> Long.valueOf(p1.getId()).compareTo(p2.getId()))
                        .collect(Collectors.toList());
            case 'Q':
                return batchList.stream()
                        .sorted((p1, p2) -> p1.getCurrentQuantity() - p2.getCurrentQuantity())
>>>>>>> b3f029e (chore: adiciona os m)
                        .collect(Collectors.toList());
            default:
                return batchList.stream()
<<<<<<< HEAD
                        .sorted(Comparator.comparing(Batch::getDueDate))
=======
                        .sorted((p1, p2) -> p1.getDueDate().compareTo(p2.getDueDate()))
>>>>>>> b3f029e (chore: adiciona os m)
                        .collect(Collectors.toList());
        }
    }
}