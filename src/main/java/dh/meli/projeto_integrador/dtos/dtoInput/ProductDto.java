package dh.meli.projeto_integrador.dtos.dtoInput;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
@AllArgsConstructor
@Builder
public class ProductDto {

    @NotNull(message = "The product must have an ID number")
    private Long batchId;

    @NotEmpty(message = "The product must have a quantity")
    private int quantity;
}
