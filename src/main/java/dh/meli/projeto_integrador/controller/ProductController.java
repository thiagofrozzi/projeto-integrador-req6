package dh.meli.projeto_integrador.controller;

import dh.meli.projeto_integrador.dto.dtoOutput.ProductStockDto;
import dh.meli.projeto_integrador.dto.dtoOutput.ProductOutputDto;
import dh.meli.projeto_integrador.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Class responsible for intermediating the requests sent by the user with the responses provided by the Service;
 * @author Rafael Cavalcante, Amanda Marinelli
 * @version 0.0.1
 */
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * A get method that when called will return in the body request a list of products present in the Database
     * @return Response Entity of type List of productDto and the corresponding HttpStatus ;
     */
    @GetMapping("/fresh-products")
    public ResponseEntity<List<ProductOutputDto>> listAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * A get method that when called will return in the body request a list of products of a specified category, present in the Database
     * @param category a String received by the URL request to determine the type of product returned
     * @return Response Entity of type List of productDto and the corresponding HttpStatus ;
     */
    @GetMapping("/fresh-products/{category}")
    public ResponseEntity<List<ProductOutputDto>> listProductByCategory(@PathVariable String category){
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    /**
     * Method to get the product batche's properties by id through the endpoint "/api/v1/fresh-products/fresh-products/list/{id}?order={order}
     * @param  id (long type) received by url.
     * @param order (character type) may be received by url, to order the API output.
     * @return a list of properties for the specified product.
     */
    @GetMapping("/fresh-products/list/{id}")
    public ResponseEntity<ProductStockDto> getProductBratches(@PathVariable long id,
                                                              @RequestParam (required = false, defaultValue = "V") Character order) {
        return ResponseEntity.ok(productService.getProductBatchProps(id, order));
    }

}
