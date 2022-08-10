package dh.meli.projeto_integrador.dto.dtoOutput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BatchDto {
    private long idWarehouse;
    private long batchNumber;
    private int currentQuantity;
    private LocalDate dueDate;
}


