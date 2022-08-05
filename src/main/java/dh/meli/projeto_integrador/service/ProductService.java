package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.outputDto.ProductOutputDto;
import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.model.Product;
import dh.meli.projeto_integrador.repository.IBatchRepository;
import dh.meli.projeto_integrador.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepo;

    public List<ProductOutputDto> getAllProducts(){
        List<Product> products = (List<Product>) productRepo.findAll();
        return products.stream().map(product-> new ProductOutputDto(product)).collect(Collectors.toList());
    }

    public List<ProductOutputDto> getProductsByCategory(String category){
        List<Product> products =(List<Product>) productRepo.findAllByType(category);
        return products.stream().map(product-> new ProductOutputDto(product)).collect(Collectors.toList());
    }
}
