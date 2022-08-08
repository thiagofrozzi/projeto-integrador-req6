package dh.meli.projeto_integrador.utils;

import dh.meli.projeto_integrador.enumClass.PurchaseOrderStatusEnum;
import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.model.Cart;
import dh.meli.projeto_integrador.model.Product;

import java.time.LocalDate;

public class GenerateProduct {
    public static Product newProduct1() {
        return Product.builder()
                .id(1)
                .name("Morango")
                .type("Fresco")
                .price(4.99)
                .batches(GenerateBatch.newListBatch1())
                .build();
    }
}
