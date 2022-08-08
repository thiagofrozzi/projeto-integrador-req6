package dh.meli.projeto_integrador.util;

import dh.meli.projeto_integrador.dto.outputDto.ProductOutputDto;
import dh.meli.projeto_integrador.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GenerateProducts {

    public static ProductOutputDto validProductDto1() {
        return ProductOutputDto.builder()
                .name("Maçã")
                .type("Fresco")
                .price(20.1)
                .build();
    }

    public static ProductOutputDto validProductDto2() {
        return ProductOutputDto.builder()
                .name("Uva")
                .type("Fresco")
                .price(20.1)
                .build();
    }

    public static Product validProduct1() {
        return Product.builder()
                .name("Maçã")
                .type("Fresco")
                .price(20.1)
                .build();
    }

    public static Product validProduct2() {
        return Product.builder()
                .name("sorvete")
                .type("Congelado")
                .price(20.1)
                .build();
    }

    public static List<ProductOutputDto> productDtoList() {
        List<ProductOutputDto> productList = new ArrayList<>();
         productList.add(validProductDto1());
        productList.add(validProductDto2());

        return productList;
    }

    public static List<Product> productList() {
        List<Product> productList = new ArrayList<>();
        productList.add(validProduct1());
        productList.add(validProduct2());

        return productList;
    }

    public static List<Product> emptyProductDtoList() {
         List<Product> productList = new ArrayList<>();
         return productList;
    }
}
