package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.outputDto.ProductOutputDto;
import dh.meli.projeto_integrador.exception.NotFoundException;
import dh.meli.projeto_integrador.model.Product;
import dh.meli.projeto_integrador.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that implements the methods of the IPropertyService interface.
 * @author Rafael Cavalcante
 * @version 0.0.1
 */
@Service
public class ProductService implements IProductService{

    /**
     * Dependency Injection of the Product Repository.
     */
    @Autowired
    private IProductRepository productRepo;


    /**
     * Method to find a list of products and return a ProductDto.
     * @return a list of objects of type ProductDto.
     */
    @Override
    public List<ProductOutputDto> getAllProducts(){
        List<Product> products = (List<Product>) productRepo.findAll();

        if(products.size() == 0) throw new NotFoundException("No Products Found");

        return products.stream().map(product-> new ProductOutputDto(product)).collect(Collectors.toList());
    }

    /**
     * Method to find a list of products of a specified category and return a ProductDto.
     * @param category of type String.
     * @return a list of objects of type ProductDto.
     */
    @Override
    public List<ProductOutputDto> getProductsByCategory(String category){
        List<Product> products = productRepo.findAllByType(category);

        if(products.size() == 0) throw new NotFoundException("No Products Found");

        return products.stream().map(product-> new ProductOutputDto(product)).collect(Collectors.toList());
    }
}
