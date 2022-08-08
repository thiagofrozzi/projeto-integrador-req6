package dh.meli.projeto_integrador.controller;

import dh.meli.projeto_integrador.dtos.dtoInput.CartDto;
import dh.meli.projeto_integrador.dtos.dtoOutput.TotalPriceDto;
import dh.meli.projeto_integrador.dtos.dtoOutput.UpdateStatusDto;
import dh.meli.projeto_integrador.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/orders")
    public ResponseEntity<TotalPriceDto> createNewPurchaseOrder(@RequestBody @Valid CartDto cartDto) {
        TotalPriceDto createdCart = cartService.createCart(cartDto);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateStatusDto> updatePurchaseOrder(@PathVariable Long id) {
        UpdateStatusDto result = cartService.updateStatusCart(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
















