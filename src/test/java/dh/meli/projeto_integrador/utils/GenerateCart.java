package dh.meli.projeto_integrador.utils;

import dh.meli.projeto_integrador.enumClass.PurchaseOrderStatusEnum;
import dh.meli.projeto_integrador.model.BatchCart;
import dh.meli.projeto_integrador.model.Cart;
import dh.meli.projeto_integrador.model.Customer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class GenerateCart {

    public static Cart newCart1() {
        return Cart.builder()
                .id(1L)
                .status(PurchaseOrderStatusEnum.OPEN)
                .build();
    }

//    public static Set<Cart> newListCart1() {
//        Set<Cart> listCart =  new HashSet<>();
//        listCart.add(GenerateCart.newCart1());
//
//        return listCart;
//    }
}
