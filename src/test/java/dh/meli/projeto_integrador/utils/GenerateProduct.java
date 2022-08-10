package dh.meli.projeto_integrador.utils;

import dh.meli.projeto_integrador.dto.dtoInput.ProductDto;
import dh.meli.projeto_integrador.enumClass.PurchaseOrderStatusEnum;
import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.model.Cart;
import dh.meli.projeto_integrador.model.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GenerateProduct {
    public static Product newProduct1() {
        return Product.builder()
                .id(1)
                .name("Morango")
                .type("Fresco")
                .price(4.99)
                .build();
    }

public static ProductDto newProductDto() {
    return ProductDto.builder()
            .productId(1L)
            .quantity(10)
            .build();
}

    public static List<Product> newProductList(){
        List<Product> products = new ArrayList<>();
        products.add(newProduct1());

        return products;
    }

    public static List<ProductDto> newProductListDto(){
        List<ProductDto> products = new ArrayList<>();
        products.add(newProductDto());

        return products;
    }
}
