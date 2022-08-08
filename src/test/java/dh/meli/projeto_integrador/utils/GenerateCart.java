package dh.meli.projeto_integrador.utils;

import dh.meli.projeto_integrador.enumClass.PurchaseOrderStatusEnum;
import dh.meli.projeto_integrador.model.Cart;
import dh.meli.projeto_integrador.model.Customer;

import java.time.LocalDate;

public class GenerateCart {

    public static Cart newCart1() {
        return Cart.builder()
                .id(1)
                .customer(GenerateCustomer.newCustomer1())
                .date(LocalDate.of(1990, 3, 15))
                .status(PurchaseOrderStatusEnum.OPEN)
                .batchCarts()
                .build();
    }


}
