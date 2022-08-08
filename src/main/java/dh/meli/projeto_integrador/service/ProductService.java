package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.outputDto.ProductOutputDto;
import dh.meli.projeto_integrador.exception.NotFoundException;
import dh.meli.projeto_integrador.model.Product;
import dh.meli.projeto_integrador.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepo;

    @Override
    public List<ProductOutputDto> getAllProducts(){
        List<Product> products = (List<Product>) productRepo.findAll();
        if(products.size() == 0) throw new NotFoundException("não existem produtos");
        return products.stream().map(product-> new ProductOutputDto(product)).collect(Collectors.toList());
    }

    @Override
    public List<ProductOutputDto> getProductsByCategory(String category){
        List<Product> products = productRepo.findAllByType(category);
        if(products.size() == 0) throw new NotFoundException("não existem produtos");
        return products.stream().map(product-> new ProductOutputDto(product)).collect(Collectors.toList());
    }
}
