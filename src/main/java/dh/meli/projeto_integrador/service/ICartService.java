package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dtos.dtoInput.CartDto;
import dh.meli.projeto_integrador.dtos.dtoOutput.TotalPriceDto;

public interface ICartService {
    // método para criação de um carrinho de compras
    TotalPriceDto createCart(CartDto cartDto);
}
