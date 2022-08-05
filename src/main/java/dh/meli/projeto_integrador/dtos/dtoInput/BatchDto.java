package dh.meli.projeto_integrador.dtos.dtoInput;

import dh.meli.projeto_integrador.model.Batch;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchDto {

    private long batchId;

    @NotNull(message = "You should define product id.")
    private long productId;

    @NotNull(message = "You should define current temperature.")
    private float currentTemperature;

    @NotNull(message = "You should define minimum temperature.")
    private float minimumTemperature;

    @NotNull(message = "You should define a valid quantity.")
    @Min(value = 1)
    private int initialQuantity;

    @NotNull(message = "You should define a valid quantity.")
    @Min(value = 1)
    private int currentQuantity;

    @NotNull(message = "You should define due Date. Format: (yyyy-mm-dd).")
    private LocalDate manufacturingDate;

    @NotNull(message = "You should define the manufacturing Time. Format: (hh:mm:ss).")
    private LocalTime manufacturingTime;

    @NotNull(message = "You should define due Date. Format: (yyyy-mm-dd).")
    private LocalDate dueDate;

    public BatchDto(Batch batch) {
        this.batchId = batch.getId();
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
