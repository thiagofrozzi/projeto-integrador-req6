package dh.meli.projeto_integrador.utils;

import dh.meli.projeto_integrador.model.BatchCart;
import dh.meli.projeto_integrador.model.Product;

public class GenerateBatchCart {
    public static BatchCart newProduct1() {
        return BatchCart.builder()
                .id(1)
                .quantity(50)
                .batch(GenerateBatch.newBatch1())
                .cart(GenerateCart.newCart1())
                .build();
    }
}
