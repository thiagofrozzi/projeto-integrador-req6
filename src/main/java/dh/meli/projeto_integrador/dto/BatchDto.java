package dh.meli.projeto_integrador.dto;

import dh.meli.projeto_integrador.model.Batch;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchDto {
    private long id;
    private long productId;
    private float currentTemperature;
    private float minimumTemperature;
    private int initialQuantity;
    private int currentQuantity;
    private LocalDate manufacturingDate;
    private LocalTime manufacturingTime;
    private LocalDate dueDate;

    public BatchDto(Batch batch) {
        this.id = batch.getId();
        this.productId = batch.getProduct().getId();
        this.currentTemperature = batch.getCurrentTemperature();
        this.minimumTemperature = batch.getMinimumTemperature();
        this.initialQuantity = batch.getInitialQuantity();
        this.currentQuantity = batch.getCurrentQuantity();
        this.manufacturingDate = batch.getManufacturingDate();
        this.manufacturingTime = batch.getManufacturingTime();
        this.dueDate = batch.getDueDate();
    }
}
