package dh.meli.projeto_integrador.controller;

import dh.meli.projeto_integrador.dtos.dtoInput.CartDto;
import dh.meli.projeto_integrador.dtos.dtoOutput.BatchPropertiesDto;
import dh.meli.projeto_integrador.dtos.dtoOutput.TotalPriceDto;
import dh.meli.projeto_integrador.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Method to get the product batche's properties by id through the endpoint "/api/v1/fresh-products/list?
     * querytype=[idProduct]".
     * @param  id (long type) received by url.
     * @return a list of properties for the specified product.
     */
    @GetMapping("/list")
    public ResponseEntity<BatchPropertiesDto> getProductBratches(@RequestParam long id) {
    BatchPropertiesDto batchPropertiesDto = productService.getProductBatchProps(id);
    return new ResponseEntity<>(batchPropertiesDto, HttpStatus.OK);
    }
}
