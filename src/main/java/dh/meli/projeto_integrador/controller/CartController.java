package dh.meli.projeto_integrador.controller;

import dh.meli.projeto_integrador.dto.dtoInput.CartDto;
import dh.meli.projeto_integrador.dto.dtoOutput.TotalPriceDto;
import dh.meli.projeto_integrador.dto.dtoOutput.UpdateStatusDto;
import dh.meli.projeto_integrador.enumClass.PurchaseOrderStatusEnum;
import dh.meli.projeto_integrador.exception.ForbiddenException;
import dh.meli.projeto_integrador.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Class responsible for processing user's requests and generating appropriated HTTP responses;
 * @author Gabriela Azevedo, Thiago Frozzi e Amanda Marinelli
 * @version 0.0.1;
 */
@RestController
@RequestMapping("/api/v1/fresh-products")
public class CartController {

    /**
     * Dependency Injection of the Cart Service.
     */
    @Autowired
    private CartService cartService;

    /**
     * A POST method responsible for saving the infos of a new cart at application's database.
     * @param cartDto a valid CartDto instance received by the request body.
     * @return Response Entity of type TotalPriceDto, with the cart total price and the corresponding HttpStatus.
     */
    @PostMapping("/orders")
    public ResponseEntity<TotalPriceDto> createNewPurchaseOrder(@RequestBody @Valid CartDto cartDto) {
        if (cartDto.getOrderStatus().equals(PurchaseOrderStatusEnum.FINISHED)) {
            throw new ForbiddenException("The new cart cannot be created with order status 'FINISHED'");
        }
        TotalPriceDto createdCart = cartService.createCart(cartDto);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    /**
     * A PUT method responsible for update the cart status.
     * @param id Long
     * @return Response Entity of type UpdateStatusSto, with a message.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UpdateStatusDto> updatePurchaseOrder(@PathVariable Long id) {
        UpdateStatusDto result = cartService.updateStatusCart(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
















