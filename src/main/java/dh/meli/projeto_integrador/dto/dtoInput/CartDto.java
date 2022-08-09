package dh.meli.projeto_integrador.dto.dtoInput;

import dh.meli.projeto_integrador.enumClass.PurchaseOrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class CartDto {

    @NotNull(message = "The date of the creation cart has to be a date. Format: (yyyy-mm-dd)")
    private LocalDate date;

    private Long buyer_id;

    @NotEmpty(message = "The status of the cart has to be OPENED of FINISHED")
    private PurchaseOrderStatusEnum orderStatus;

    @Size(min = 1, message = "The products list not to be empty")
    private List< @Valid ProductDto> productList;
}
