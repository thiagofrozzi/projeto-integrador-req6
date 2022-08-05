package dh.meli.projeto_integrador.controller;

import dh.meli.projeto_integrador.dto.outputDto.ProductOutputDto;
import dh.meli.projeto_integrador.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/fresh-products")
    public ResponseEntity<List<ProductOutputDto>> listAllProducts(){
    return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/fresh-products/{category}")
    public ResponseEntity<List<ProductOutputDto>> listProductByCategory(@PathVariable String category){
    return ResponseEntity.ok(productService.getProductsByCategory(category));
    }
}
