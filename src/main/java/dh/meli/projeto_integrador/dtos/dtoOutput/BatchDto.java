package dh.meli.projeto_integrador.dtos.dtoOutput;

import dh.meli.projeto_integrador.model.Batch;
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

    private long batchNumber;
    private int currentQuantity;
    private LocalDate dueDate;

    public BatchDto(Batch batch) {
        this.batchNumber = batch.getId();
        this.currentQuantity = batch.getCurrentQuantity();
        this.dueDate = batch.getDueDate();
    }
}
