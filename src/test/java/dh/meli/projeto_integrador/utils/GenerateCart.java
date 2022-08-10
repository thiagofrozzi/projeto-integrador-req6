package dh.meli.projeto_integrador.utils;

import dh.meli.projeto_integrador.dto.dtoInput.CartDto;
import dh.meli.projeto_integrador.enumClass.PurchaseOrderStatusEnum;
import dh.meli.projeto_integrador.model.Cart;
import dh.meli.projeto_integrador.model.Customer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class GenerateCart {

    public static Cart newCart1() {
        return Cart.builder()
                .id(1L)
                .customer(GenerateCustomer.newCustomer1())
                .status(PurchaseOrderStatusEnum.OPEN)
                .build();
    }

    public static CartDto newCartDto1() {
        return CartDto.builder()
                .buyerId(1L)
                .date(LocalDate.now())
                .orderStatus(PurchaseOrderStatusEnum.OPEN)
                .products(GenerateProduct.newProductListDto())
                .build();
    }

//    public static Set<Cart> newListCart1() {
//        Set<Cart> listCart =  new HashSet<>();
//        listCart.add(GenerateCart.newCart1());
//
//        return listCart;
//    }
}
