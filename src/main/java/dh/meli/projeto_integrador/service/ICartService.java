package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoInput.CartDto;
import dh.meli.projeto_integrador.dto.dtoOutput.TotalPriceDto;

/**
 * Interface ICartService will manage data persistence for Cart object instances.
 * Will read, save, update and delete data through the GET, POST, PUT and DELETE requests.
 * @author Gabriela Azevedo
 * @version 0.0.1
 */
public interface ICartService {
    /**
     * Method for to create a Cart
     * @param cartDto an object of type CartDto coming from user request
     * @return an object of type TotalPriceDto
     */
    TotalPriceDto createCart(CartDto cartDto);
}
