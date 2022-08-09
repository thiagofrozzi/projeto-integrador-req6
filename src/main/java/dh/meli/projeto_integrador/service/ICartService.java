package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoInput.CartDto;
import dh.meli.projeto_integrador.dto.dtoOutput.TotalPriceDto;

public interface ICartService {
    // método para criação de um carrinho de compras
    TotalPriceDto createCart(CartDto cartDto);
}
