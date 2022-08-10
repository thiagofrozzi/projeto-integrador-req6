package dh.meli.projeto_integrador.dto.dtoOutput;

import dh.meli.projeto_integrador.enumClass.PurchaseOrderStatusEnum;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartOutputDto {
    private String customerName;
    private PurchaseOrderStatusEnum status;
    private LocalDate date;
    private List<CartProductsOutputDto> products;
    private Double total;
}
