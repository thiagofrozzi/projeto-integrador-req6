package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dtos.dtoInput.CartDto;
import dh.meli.projeto_integrador.dtos.dtoOutput.TotalPriceDto;

/**
 * Interface ICartService will manage data persistence for Cart object instances.
 * Will read, save, update and delete data through the GET, POST, PUT and DELETE requests.
 * @author Gabriela Azevedo
 * @version 0.0.1
 */
public interface ICartService {
    // método para criação de um carrinho de compras
    TotalPriceDto createCart(CartDto cartDto);
}
