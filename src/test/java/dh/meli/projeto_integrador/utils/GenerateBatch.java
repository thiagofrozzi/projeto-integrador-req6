package dh.meli.projeto_integrador.utils;

import dh.meli.projeto_integrador.enumClass.PurchaseOrderStatusEnum;
import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.model.Cart;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class GenerateBatch {
    public static Batch newBatch1() {
        return Batch.builder()
                .id(1)
                .currentTemperature(1.0f)
                .minimumTemperature(0.0f)
                .initialQuantity(20)
                .currentQuantity(20)
                .manufacturingDate(LocalDate.of(2022, 3, 15))
                .manufacturingTime(LocalTime.of(12,30))
                .dueDate(LocalDate.of(2023, 3, 15))
                .product(GenerateProduct.newProduct1())
                .build();
    }
//
//    public static Set<Batch> newListBatch1() {
//         Set<Batch> listBatches =  new HashSet<>();
//
//         listBatches.add(newBatch1());
//
//         return listBatches;
//    }
}
