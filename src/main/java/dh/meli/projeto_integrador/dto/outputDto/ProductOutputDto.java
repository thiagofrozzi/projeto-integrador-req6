package dh.meli.projeto_integrador.dto.outputDto;

import dh.meli.projeto_integrador.model.Product;
import lombok.*;

@Getter @Setter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOutputDto {
    private String name;
    private String type;
    private Double price;

    public ProductOutputDto(Product product){
        this.name = product.getName();
        this.type = product.getType();
        this.price = product.getPrice();
    }
}
