package dh.meli.projeto_integrador.utils;

import dh.meli.projeto_integrador.enumClass.PurchaseOrderStatusEnum;
import dh.meli.projeto_integrador.model.Cart;
import dh.meli.projeto_integrador.model.OrderEntry;

import java.time.LocalDate;

public class GenerateOrderEntry {
    public static OrderEntry newOrderEntry1() {
        return OrderEntry.builder()
                .id(1)
                .orderDate(LocalDate.of(2022, 8,8))
                .section()
                .build();
    }
}
