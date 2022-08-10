package dh.meli.projeto_integrador.dto.dtoOutput;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartProductsOutputDto {
    private String name;
    private String type;
    private Double price;
    private Integer quantity;
    private Double subtotal;
}
