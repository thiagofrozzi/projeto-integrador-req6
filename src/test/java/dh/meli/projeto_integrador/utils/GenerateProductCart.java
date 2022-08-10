package dh.meli.projeto_integrador.utils;

import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.model.Cart;
import dh.meli.projeto_integrador.model.Product;
import dh.meli.projeto_integrador.model.ProductCart;

import java.util.HashSet;
import java.util.Set;

public class GenerateProductCart {
    public static ProductCart newProductCart1() {
        return ProductCart.builder()
                .id(1L)
                .quantity(50)
                .build();
    }

//    public static Set<BatchCart> newListBatchCart1() {
//        Set<BatchCart> listBatchsCarts =  new HashSet<>();
//
//        listBatchsCarts.add(newBatchCart1());
//
//        return listBatchsCarts;
//    }
}
